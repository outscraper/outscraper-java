# Outscraper Java Library

The library provides convenient access to the [Outscraper API](https://app.outscraper.com/api-docs) from applications written in the Java language. Allows using [Outscraper's services](https://outscraper.com/services/) from your code.

## Installation

Java 11 or later

### Gradle users

Add this dependency to your project's build file:
``` sh
implementation "com.outscraper:outscraper-java:0.0.1"
```

### Others

You'll need to manually install the following JARs:

- [The Outscraper JAR](https://search.maven.org/remote_content?g=com.outscraper&a=outscraper-java&v=LATEST)
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
// Search for businesses in specific locations:
JSONArray results = client.googleMapsSearchV2(new HashMap<String, Object>() {{
    put("query", "bars ny usa");
    put("limit", 10);
}});
System.out.println(results);

// Get data of the specific place by id
JSONArray results = client.googleMapsSearchV2(new HashMap<String, Object>() {{
    put("query", "rChIJrc9T9fpYwokRdvjYRHT8nI4");
    put("language", "en");
}});
System.out.println(results);

// Get reviews of the specific place by id
JSONArray results = client.googleMapsReviewsV3(new HashMap<String, Object>() {{
    put("query", "rChIJrc9T9fpYwokRdvjYRHT8nI4");
    put("reviewsLimit", 20);
    put("language", "en");
}});
System.out.println(results);

// Search contacts from website
JSONArray results = client.emailsAndContacts(new HashMap<String, Object>() {{
    put("query", "outscraper.com");
}});
System.out.println(results);
```

[More examples](https://github.com/outscraper/outscraper-java/tree/master/examples)

## Contributing
Bug reports and pull requests are welcome on GitHub at https://github.com/outscraper/outscraper-java.
