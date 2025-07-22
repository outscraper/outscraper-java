# SimilarWeb Scraper With Java

Returns website analytics data including traffic, rankings, audience insights, and competitive intelligence from SimilarWeb [Outscraper API](https://app.outscraper.cloud/api-docs#tag/Domain-Related/paths/~1similarweb/get).

## Installation

Java 11 or later

### Gradle

Edit your build.gradle file
``` sh
repositories {
    maven { url "https://jitpack.io" }
}
2
dependencies {
    implementation 'com.github.outscraper:outscraper-java:v2.1.0'
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

```java
# Get data from Similarweb businesses:
JSONArray results = client.similarweb(new HashMap<String, Object>() {{
    put("query", "apple.com");
}});
System.out.println(results);
```
