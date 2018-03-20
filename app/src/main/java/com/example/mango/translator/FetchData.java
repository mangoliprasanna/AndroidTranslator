package com.example.mango.translator;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * Created by mango on 09-03-2018.
 */

public class FetchData {
    public String getData(String[] input_string)
    {
        StringBuilder ouputString = new StringBuilder();
        for(int i = 0; i < input_string.length; i++)
            ouputString.append(makeHttpRequest(createUrl(input_string[i])));
        return ouputString.toString();
    }
    public String  getData(String input_string)
    {

        return makeHttpRequest(createUrl(input_string));
    }

    private URL createUrl(String input_url) {
        URL url = null;
        try{
            url = new URL(input_url);
        } catch (MalformedURLException e) {
            Log.w("Error : ", "");
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private String makeHttpRequest(URL url) {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.w("JSON", jsonResponse);
        return jsonResponse;
    }
}
