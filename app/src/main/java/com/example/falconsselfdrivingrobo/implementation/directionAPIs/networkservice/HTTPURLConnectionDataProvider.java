package com.example.falconsselfdrivingrobo.implementation.directionAPIs.networkservice;

import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionFetchException;
import com.example.falconsselfdrivingrobo.interfaces.networkservice.HTTPDataProvider;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class HTTPURLConnectionDataProvider implements HTTPDataProvider {
    private String URL="";
    private Map<String,String> headers;
    private Map<String,String> parameters;

    private HttpURLConnection currentHTTPConnection;

    @Override
    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
       this.headers = headers;
    }

    @Override
    public void setRequestParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String fetchURL() throws Exception {
        try {
            //create full URL from parameters
            String fullURL = getFullURL();
            URL url = new URL(fullURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() != 200) {
                throw new DirectionFetchException("Returned non 200 status code "+httpURLConnection.getResponseCode());
            }
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream())
            );
            int c = 0;
            StringBuffer responseBuilder = new StringBuffer();
            while ((c=bufferedReader.read()) != -1) {
                responseBuilder.append((char)c);
            }
            return responseBuilder.toString();
        } finally {
            cancel();
        }
    }

    @Override
    public void cancel() {
        try {
           if (currentHTTPConnection != null)  {
               currentHTTPConnection.disconnect();
           }
        } catch (Exception e) {

        } finally {
            currentHTTPConnection = null;
        }
    }

    private String getFullURL() throws Exception {
        StringBuffer urlStringBuffer = new StringBuffer(URL);
        if (parameters.size() > 0) {
            urlStringBuffer.append("?");
        }
        boolean isFirst = true;
        for (String name : parameters.keySet()) {
            if(!isFirst) {
                urlStringBuffer.append("&");
                isFirst = false;
            }
            String paramName = URLEncoder.encode(name);
            String paramValue = URLEncoder.encode(parameters.get(name));
            urlStringBuffer.append(paramName+"="+paramValue);
        }

        return urlStringBuffer.toString();
    }
}
