package http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    public String sendGETRequest(String url) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            //add request header
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // get request
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            String finalResult = result.toString();

            return finalResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
