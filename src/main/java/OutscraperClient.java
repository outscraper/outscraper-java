import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class OutscraperClient {
    private String privateApiKey;
    private String privateApiURL = "https://api.app.outscraper.com";

    private HttpClient client = HttpClient.newHttpClient();

    public OutscraperClient(String apiKey) {
        privateApiKey = apiKey;
    }

    private JSONArray getData(JSONObject json) {
        try {
            return (JSONArray) json.get("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject getAPIRequest(String path, HashMap<String, Object> parameters) {
        List<NameValuePair> parametersList = new ArrayList<NameValuePair>(parameters.size());
        for (HashMap.Entry<String, Object> entry : parameters.entrySet()) {
            parametersList.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        String url = privateApiURL + path + "?" + URLEncodedUtils.format(parametersList, "utf-8");

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .header("accept", "application/json")
            .header("X-API-KEY", privateApiKey)
            .header("client", "Java SDK")
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            try {
                return new JSONObject(body);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject getRequestArchive(String requestId) {
        return getAPIRequest("/requests/" + requestId, new HashMap<String, Object>() {{}});
    }

    public JSONArray googleSearch(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/google-search-v3", parameters);

        return getData(response);
    }

    public JSONArray googleSearchNews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/google-search-news", parameters);

        return getData(response);
    }

    public JSONArray googleMapsSearch(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/maps/search-v2", parameters);

        return getData(response);
    }

    public JSONArray googleMapsSearchV3(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/maps/search-v3", parameters);

        return getData(response);
    }

    public JSONArray googleMapsDirections(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/maps/directions", parameters);

        return getData(response);
    }

    public JSONArray googleMapsReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/maps/reviews-v3", parameters);

        return getData(response);
    }

    public JSONArray getGoogleMapsPhotos(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/maps/photos-v3", parameters);

        return getData(response);
    }

    public JSONArray googlePlayReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/google-play/reviews", parameters);

        return getData(response);
    }

    public JSONArray emailsAndContacts(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/emails-and-contacts", parameters);
        return getData(response);
    }

    public JSONArray phonesEnricher(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/phones-enricher", parameters);

        return getData(response);
    }

    public JSONArray amazonProducts(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/amazon/products-v2", parameters);

        return getData(response);
    }

    public JSONArray amazonReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/amazon/reviews", parameters);

        return getData(response);
    }

    public JSONArray yelpSearch(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/yelp-search", parameters);

        return getData(response);
    }

    public JSONArray yelpReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/yelp/review", parameters);

        return getData(response);
    }

    public JSONArray tripadvisorReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/tripadvisor-reviews", parameters);

        return getData(response);
    }

    public JSONArray appStoreReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/appstore/reviews", parameters);

        return getData(response);
    }

    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }

    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }

    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }
    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }

    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }
    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }

    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }
    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }

    public JSONArray youtubeComments(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/youtube-comments", parameters);

        return getData(response);
    }

    public JSONArray g2Reviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/g2/reviews", parameters);

        return getData(response);
    }

    public JSONArray trustpilotReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/trustpilot/reviews", parameters);

        return getData(response);
    }

    public JSONArray getGlassdoorReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/glassdoor/reviews", parameters);

        return getData(response);
    }

    public JSONArray capterraReviews(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/capterra-reviews", parameters);

        return getData(response);
    }

    public JSONArray geocoding(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/geocoding", parameters);

        return getData(response);
    }

    public JSONArray reverseGeocoding(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/reverse-geocoding", parameters);

        return getData(response);
    }

    public JSONArray phoneIdentityFinder(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/whitepages-phones", parameters);

        return getData(response);
    }

    public JSONArray addressScraper(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/whitepages-addresses", parameters);

        return getData(response);
    }

    public JSONArray companyInsights(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/company-insights", parameters);

        return getData(response);
    }

    public JSONArray validateEmails(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/email-validator", parameters);

        return getData(response);
    }

    public JSONArray trustpilot(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/trustpilot", parameters);

        return getData(response);
    }

    public JSONArray trustpilotSearch(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/trustpilot", parameters);

        return getData(response);
    }
}
