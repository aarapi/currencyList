package com.example.fragments.utilities;

import com.example.fragments.data.RatesEntity;
import com.example.fragments.data.CurrencyList;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static void main(String[] args) {
        getResponse("http://data.fixer.io/api/latest?access_key=2156c20147807a01dbad5bfbc1190771");
    }


    public static String getResponse(String requestedURL){
        DataOutputStream wr = null;
        BufferedReader in = null;


        try {
            System.getProperties().put("https.proxyHost", "192.168.211.122");
            System.getProperties().put("https.proxyPort", "3128");
            System.getProperties().put("https.proxySet", "true");
            System.getProperties().put("http.proxyHost", "192.168.211.122");
            System.getProperties().put("http.proxyPort", "3128");
            System.getProperties().put("http.proxySet", "true");

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

            return response.toString();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

        return null;
    }
}
