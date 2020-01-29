package com.example.fragments.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {


    public static String getResponse(String requestedURL){
        DataOutputStream wr = null;
        BufferedReader in = null;

        try {

            URL url = new URL(requestedURL);
            HttpURLConnection connection  = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


            connection.setConnectTimeout(5000);
            connection.setReadTimeout(8000);


            connection.setDoOutput(true);

            wr = new DataOutputStream(connection.getOutputStream());
            wr.flush();
            wr.close();
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();

            JSONObject object = new JSONObject(response.toString());


            return object.get("responseMessage").toString();


        } catch (MalformedURLException e) {

        } catch (IOException e) {

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
