# Google Maps Photos Scraper With Java

Returns Google Maps photos from places when using search queries (e.g., restaurants, Manhattan, NY, USA) or from a single place when using IDs or names (e.g., NoMad Restaurant, NY, USA, 0x886916e8bc273979:0x5141fcb11460b226, ChIJu7bMNFV-54gR-lrHScvPRX4).
In case no photos were found by your search criteria, your search request will consume the usage of one photo.[Outscraper API](https://app.outscraper.cloud/api-docs#tag/Google/paths/~1maps~1photos-v3/get).

## Installation

Java 11 or later

### Gradle

Edit your build.gradle file
``` sh
repositories {
    maven { url "https://jitpack.io" }
}

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
# Get information about the places photos:
JSONArray results = client.getGoogleMapsPhotos(new HashMap<String, Object>() {{
    put("query", "The NoMad Restaurant, NY, USA");
}});
System.out.println(results);
```
