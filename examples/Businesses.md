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
OutscraperClient client = new OutscraperClient("SECRET_API_KEY");

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

Just put `query` into the request payload.

```java
HashMap<String, Object> params = new HashMap<>();

params.put(
    "query",
    "Find restaurants and cafes in California and Illinois with rating 4.2+ and status operational. " +
    "Return fields name, address, rating and reviews. " +
    "Limit results to 15."
);

JSONObject page = client.businessesSearch(params);
System.out.println(page);
```

### 3) Combine JSON + plain text (merge rules)

When both `filters/fields` and `query` are provided, the server merges them:
- `filters` and `fields` are merged
- for `limit`, `cursor`, `include_total` the plain text values have priority (if explicitly specified)

```java
HashMap<String, Object> params = new HashMap<>();

HashMap<String, Object> filters = new HashMap<>();
filters.put("country_code", "US");
filters.put("states", new String[] {"CA"});
filters.put("types", new String[] {"restaurant"});

params.put("filters", filters);
params.put("fields", new String[] {"name", "phone"});
params.put("limit", 15);

params.put(
    "query",
    "Add cafes too. " +
    "Return address and reviews. " +
    "Limit 20. " +
    "Include total."
);

JSONObject page = client.businessesSearch(params);
System.out.println(page);
```

### 4) Search with enrichments

`enrichments` can be provided as:
- **Object** (recommended): `{ "contacts_n_leads": {...}, "company_insights": {} }`
- **List**: `["contacts_n_leads", "company_insights"]`
- **String**: `"contacts_n_leads"`

#### 4.1) Enrichments as object (recommended)

```java
HashMap<String, Object> params = new HashMap<>();

HashMap<String, Object> filters = new HashMap<>();
filters.put("country_code", "US");
filters.put("states", new String[] {"NY"});
filters.put("types", new String[] {"restaurant", "cafe"});

params.put("filters", filters);
params.put("limit", 10);
params.put("fields", new String[] {"name", "phone"});

// enrichments object
HashMap<String, Object> enrichments = new HashMap<>();

HashMap<String, Object> contacts = new HashMap<>();
contacts.put("contacts_per_company", 4);
contacts.put("emails_per_contact", 2);

enrichments.put("contacts_n_leads", contacts);
enrichments.put("company_insights", new HashMap<>());

params.put("enrichments", enrichments);

JSONObject page = client.businessesSearch(params);
System.out.println(page);
```

#### 4.2) Enrichments as list

```java
HashMap<String, Object> params = new HashMap<>();
params.put("filters", new HashMap<String, Object>() {{
    put("country_code", "US");
    put("states", new String[] {"NY"});
}});
params.put("limit", 10);
params.put("enrichments", new String[] {"contacts_n_leads", "company_insights"});

JSONObject page = client.businessesSearch(params);
System.out.println(page);
```

#### 4.3) Enrichments as single string

```java
HashMap<String, Object> params = new HashMap<>();
params.put("filters", new HashMap<String, Object>() {{
    put("country_code", "US");
    put("states", new String[] {"NY"});
}});
params.put("limit", 10);
params.put("enrichments", "contacts_n_leads");

JSONObject page = client.businessesSearch(params);
System.out.println(page);
```

#### 4.4) Full JSON-style payload (filters + fields + enrichments + query)

This is equivalent to the JSON payload you described:

```java
HashMap<String, Object> params = new HashMap<>();

params.put("filters", new HashMap<String, Object>() {{
    put("country_code", "US");
    put("states", new String[] {"CA", "NY"});
    put("types", new String[] {"restaurant", "cafe"});
}});
params.put("limit", 900);
params.put("cursor", null);
params.put("include_total", false);
params.put("fields", new String[] {
    "name", "types", "address", "country", "website", "phone", "rating", "reviews"
});

// enrichments dict
params.put("enrichments", new HashMap<String, Object>() {{
    put("contacts_n_leads", new HashMap<String, Object>() {{
        put("contacts_per_company", 4);
        put("emails_per_contact", 2);
    }});
    put("company_insights", new HashMap<String, Object>());
}});

params.put(
    "query",
    "Find hotels in California and Illinois with rating 4.2+ and status operational. " +
    "Return fields: name, address, rating and reviews. " +
    "Limit results to 6. " +
    "Enrich data with contacts_n_leads. Contact per company set to 8"
);

JSONObject page = client.businessesSearch(params);
System.out.println(page);
```

### 5) Iterate over all results (auto-pagination)

```java
HashMap<String, Object> filters = new HashMap<>();
filters.put("country_code", "US");
filters.put("states", new String[] {"NY"});
filters.put("business_statuses", new String[] {"operational"});

// Using the convenience overload (query/enrichments are optional)
JSONArray all = client.businessesIterSearch(filters, 100, new String[] {"name", "phone"}, false, null, null);
System.out.println(all.length());
```

