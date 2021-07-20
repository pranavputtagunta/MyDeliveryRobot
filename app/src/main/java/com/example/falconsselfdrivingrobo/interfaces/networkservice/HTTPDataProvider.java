package com.example.falconsselfdrivingrobo.interfaces.networkservice;

import java.util.Map;

public interface HTTPDataProvider {
    public void setURL(String URL);
    public void setHeaders(Map<String,String> headers);
    public void setRequestParameters(Map<String,String> parameters);

    public String fetchURL() throws Exception;
    public void cancel();
}
