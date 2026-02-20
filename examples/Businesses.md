# Businesses Search With Java

Search businesses using the `/businesses` endpoint.

This endpoint supports:
- **Structured JSON parameters** (`filters`, `fields`, etc.)
- **AI-powered plain text query** via the `query` parameter
- **Both together** (server merges both inputs)

When both `parameters` and `query` are provided:
- `filters` and `fields` are merged
- for `limit`, `cursor`, and `include_total`, plain text values have priority (if explicitly specified)
- if not specified anywhere, default API values are used

## Installation

Java 11 or later.

### Gradle

Edit your `build.gradle` file:

```sh
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.outscraper:outscraper-java:v2.1.0'
}
```

### Maven

Add the JitPack repository to your build file:

```sh
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency:

```sh
<dependency>
    <groupId>com.github.outscraper</groupId>
    <artifactId>outscraper-java</artifactId>
    <version>v2.1.0</version>
</dependency>
```

### Others

You'll need to manually install the following JARs:
- [The Outscraper JAR](https://jitpack.io/com/github/outscraper/outscraper-java/v2.1.0/outscraper-java-v2.1.0.jar)
- [Json](https://repo1.maven.org/maven2/org/json/json/20090211/json-20090211.jar)
- [Httpcomponents](https://repo1.maven.org/maven2/org/apache/httpcomponents/httpclient/4.5.13/httpclient-4.5.13.jar)
- [Guava](https://repo1.maven.org/maven2/com/google/guava/guava/30.1.1-jre/guava-30.1.1-jre.jar)

## Initialization

```java
OutscraperClient client = new OutscraperClient("SECRET_API_KEY");
```

[Link to the profile page to create the API key](https://app.outscraper.com/profile)

## Usage

### 1) Search with structured parameters (JSON)

```java
HashMap<String, Object> params = new HashMap<>();

HashMap<String, Object> filters = new HashMap<>();
filters.put("country_code", "US");
filters.put("states", new String[] {"NY"});
filters.put("cities", new String[] {"New York", "Buffalo"});
filters.put("types", new String[] {"restaurant", "cafe"});
filters.put("has_website", true);
filters.put("has_phone", true);
filters.put("business_statuses", new String[] {"operational"});

params.put("filters", filters);
params.put("limit", 50);
params.put("include_total", false);
params.put("fields", new String[] {"name", "phone", "website", "address", "rating", "reviews"});

JSONObject page = client.businessesSearch(params);
System.out.println(page);
```

### 2) AI-powered search (plain text)

```java
String query =
    "Find restaurants and cafes in California and Illinois with rating 4.2+ and status operational. " +
    "Return fields name, address, rating and reviews. " +
    "Limit results to 15.";

JSONObject page = client.businessesSearch(new HashMap<>(), query);
System.out.println(page);
```

### 3) Combine JSON + plain text (merge rules)

```java
HashMap<String, Object> params = new HashMap<>();

HashMap<String, Object> filters = new HashMap<>();
filters.put("country_code", "US");
filters.put("states", new String[] {"CA"});
filters.put("types", new String[] {"restaurant"});

params.put("filters", filters);
params.put("fields", new String[] {"name", "phone"});
params.put("limit", 15);

String query =
    "Add cafes too. " +
    "Return address and reviews. " +
    "Limit 20. " +
    "Include total.";

JSONObject page = client.businessesSearch(params, query);
System.out.println(page);

// Result behavior:
// - filters merged (restaurant + cafe, plus JSON filters)
// - fields merged (name, phone, address, reviews, ...)
// - limit/include_total taken from plain text when present
```

### 4) Iterate over all results (auto-pagination)

```java
HashMap<String, Object> params = new HashMap<>();
HashMap<String, Object> filters = new HashMap<>();
filters.put("country_code", "US");
filters.put("states", new String[] {"NY"});
filters.put("business_statuses", new String[] {"operational"});

params.put("filters", filters);
params.put("limit", 100);

JSONArray all = client.businessesIterSearch(params);
System.out.println(all.length());
```