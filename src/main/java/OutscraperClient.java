import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
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

    public JSONObject postAPIRequest(String path, HashMap<String, Object> parameters) {
        String url = privateApiURL + path;

        JSONObject jsonPayload = new JSONObject(parameters != null ? parameters : new HashMap<>());

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload.toString()))
            .header("accept", "application/json")
            .header("content-type", "application/json")
            .header("X-API-KEY", privateApiKey)
            .header("client", "Java SDK")
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return new JSONObject(body);
        } catch (IOException | InterruptedException | JSONException e) {
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

    public JSONArray contactsAndLeads(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/contacts-and-leads", parameters);

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

    public JSONArray similarweb(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/similarweb", parameters);

        return getData(response);
    }

    public JSONArray companyWebsiteFinder(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/company-website-finder", parameters);

        return getData(response);
    }

    public JSONArray yellowpagesSearch(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/yellowpages-search", parameters);

        return getData(response);
    }

    public JSONObject businessesSearch(HashMap<String, Object> parameters) {
        if (parameters == null) parameters = new HashMap<>();
        parameters.putIfAbsent("async", false);

        JSONObject response = postAPIRequest("/businesses", parameters);
        if (response == null) return null;

        Object data = response.opt("data");
        if (data instanceof JSONObject) {
            return (JSONObject) data;
        }
        if (data instanceof JSONArray) {
            return new JSONObject().put("items", (JSONArray) data).put("has_more", false).put("next_cursor", JSONObject.NULL);
        }
        return response;
    }

    public JSONArray businessesIterSearch(HashMap<String, Object> parameters) {
        if (parameters == null) parameters = new HashMap<>();

        JSONArray all = new JSONArray();
        Object cursorObj = parameters.get("cursor");
        String cursor = cursorObj != null ? String.valueOf(cursorObj) : null;

        while (true) {
            if (cursor != null) {
                parameters.put("cursor", cursor);
            } else {
                parameters.remove("cursor");
            }

            JSONObject page = businessesSearch(parameters);
            if (page == null) break;

            JSONArray items = page.optJSONArray("items");
            if (items == null) {
                Object data = page.opt("data");
                if (data instanceof JSONArray) items = (JSONArray) data;
            }

            if (items != null) {
                for (int i = 0; i < items.length(); i++) {
                    all.put(items.get(i));
                }
            }

            boolean hasMore = page.optBoolean("has_more", false);

            String nextCursor = null;
            Object nextCursorObj = page.opt("next_cursor");
            if (nextCursorObj != null && nextCursorObj != JSONObject.NULL) {
                nextCursor = String.valueOf(nextCursorObj);
                if (nextCursor.isBlank()) nextCursor = null;
            }

            if (!hasMore || nextCursor == null || items == null || items.length() == 0) {
                break;
            }

            cursor = nextCursor;
        }

        return all;
    }

    public JSONObject businessesGetDetails(String businessId, HashMap<String, Object> parameters) {
        if (businessId == null || businessId.isBlank()) {
            throw new IllegalArgumentException("businessId is required");
        }
        if (parameters == null) parameters = new HashMap<>();

        Object fieldsObj = parameters.get("fields");
        if (fieldsObj instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> fields = (List<Object>) fieldsObj;
            List<String> strFields = new ArrayList<>();
            for (Object f : fields) {
                if (f != null) strFields.add(String.valueOf(f));
            }
            parameters.put("fields", String.join(",", strFields));
        }

        String encodedId = URLEncoder.encode(String.valueOf(businessId), StandardCharsets.UTF_8);
        JSONObject response = getAPIRequest("/businesses/" + encodedId, parameters);
        if (response == null) return null;

        Object data = response.opt("data");
        if (data instanceof JSONObject) return (JSONObject) data;
        if (data instanceof JSONArray) {
            JSONArray arr = (JSONArray) data;
            if (arr.length() > 0 && arr.get(0) instanceof JSONObject) {
                return arr.getJSONObject(0);
            }
        }
        return response;
    }
}
