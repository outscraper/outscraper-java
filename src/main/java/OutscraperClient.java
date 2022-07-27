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

        JSONObject response = getAPIRequest("/google-search-v2", parameters);

        return getData(response);
    }

    public JSONArray googleSearchV3(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/google-search-v3", parameters);

        return getData(response);
    }

    public JSONArray googleMapsSearchV2(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/maps/search-v2", parameters);

        return getData(response);
    }

    public JSONArray googleMapsReviewsV3(HashMap<String, Object> parameters) {
        parameters.put("async", false);

        JSONObject response = getAPIRequest("/maps/reviews-v3", parameters);

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
}
