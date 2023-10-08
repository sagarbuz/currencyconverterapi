import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class CurrencyConverter {
    private final String apiKey;
    private final String apiUrl;

    public CurrencyConverter(String apiKey) {
        this.apiKey = "7467cce9449f12402bb7a0ed";
        this.apiUrl = "https://v6.exchangerate-api.com/v6/7467cce9449f12402bb7a0ed/latest/USD";
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        try {
            URL url = new URL(apiUrl + "?from=" + fromCurrency + "&to=" + toCurrency);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response and extract the exchange rate
                double exchangeRate = parseExchangeRate(response.toString(),toCurrency);
                return amount * exchangeRate;
            } else {
                System.out.println("Error: Unable to fetch exchange rate data.");
                return -1.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }

    private double parseExchangeRate(String jsonResponse,String toCurrency) {
    	    try {
    	        // Print the JSON response for debugging purposes
    	        System.out.println("JSON Response:\n" + jsonResponse);

    	        // Parse the JSON response
    	        JSONObject json = new JSONObject(jsonResponse);

    	        // Extract the exchange rate from the JSON object
    	        if (json.has("conversion_rates")) {
    	            JSONObject conversionRates = json.getJSONObject("conversion_rates");

    	            if (conversionRates.has(toCurrency)) {
    	                double exchangeRate = conversionRates.getDouble(toCurrency);
    	                return exchangeRate;
    	            } else {
    	                System.out.println("Error: Exchange rate for USD not found in the JSON response.");
    	                return -1.0;
    	            }
    	        } else {
    	            System.out.println("Error: 'conversion_rates' key not found in the JSON response.");
    	            return -1.0;
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return -1.0;
    	    }
    }
}
