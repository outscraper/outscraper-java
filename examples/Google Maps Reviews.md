# Google Maps Reviews Scraper With Java

The library provides real-time access to the reviews from Google Maps via [Outscraper API](https://app.outscraper.com/api-docs#tag/Google-Reviews).

## Installation

Java 11 or later

### Gradle

Edit your build.gradle file
``` sh
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.outscraper:outscraper-java:v2.0.0'
}
```

### Maven

Add the JitPack repository to your build file
``` sh
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

Add the dependency
``` sh
	<dependency>
	    <groupId>com.github.outscraper</groupId>
	    <artifactId>outscraper-java</artifactId>
	    <version>v2.0.0</version>
	</dependency>
```

### Others

You'll need to manually install the following JARs:
- [The Outscraper JAR](https://jitpack.io/com/github/outscraper/outscraper-java/v2.0.0/outscraper-java-v2.0.0.jar)
- [Json](https://repo1.maven.org/maven2/org/json/json/20090211/json-20090211.jar)
- [Httpcomponents](https://repo1.maven.org/maven2/org/apache/httpcomponents/httpclient/4.5.13/httpclient-4.5.13.jar)
- [Guava](https://repo1.maven.org/maven2/com/google/guava/guava/30.1.1-jre/guava-30.1.1-jre.jar)

## Initialization
```java
OutscraperClient client = new OutscraperClient("SECRET_API_KEY");
```
[Link to the profile page to create the API key](https://app.outscraper.com/profile)

## Usage

```java
// Get reviews of the specific place by id
JSONArray results = client.googleMapsReviews(new HashMap<String, Object>() {{
  put("query", "ChIJrc9T9fpYwokRdvjYRHT8nI4");
  put("reviewsLimit", 20);
  put("language", "en");
}});
System.out.println(results);

// Get reviews for places found by search query
JSONArray results = client.googleMapsReviews(new HashMap<String, Object>() {{
  put("query", "Memphis Seoul brooklyn usa");
  put("reviewsLimit", 20);
  put("limit", 20);
  put("language", "en");
}});
System.out.println(results);

// Get only new reviews during last 24 hours
Integer yesterdayTimestamp = 1657980986;
JSONArray results = client.googleMapsReviews(new HashMap<String, Object>() {{
  put("query", "ChIJrc9T9fpYwokRdvjYRHT8nI4");
  put("sort", "newest");
  put("cutoff", yesterdayTimestamp);
  put("reviewsLimit", 100);
  put("language", "en");
}});
System.out.println(results);
```
