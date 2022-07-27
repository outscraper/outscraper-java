# Emails And Contacts Scraper With Java

Allows finding email addresses, social links, and phones from domains via [Outscraper API](https://app.outscraper.com/api-docs#tag/Emails-and-Contacts).

## Installation

Java 11 or later

### Gradle

Edit your build.gradle file
``` sh
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.outscraper:outscraper-java:v1.0.4'
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
	    <version>v1.0.4</version>
	</dependency>
```

### Others

You'll need to manually install the following JARs:
- [The Outscraper JAR](https://jitpack.io/com/github/outscraper/outscraper-java/v1.0.4/outscraper-java-v1.0.4.jar)
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
# Search contacts from website:
JSONArray results = client.emailsAndContacts(new HashMap<String, Object>() {{
    put("query", "outscraper.com");
}});
System.out.println(results);
```