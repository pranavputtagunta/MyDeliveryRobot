package com.example.falconsselfdrivingrobo.navigation;

import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionFetchException;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionsFetcher;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionsHandler;

public class GoogleDirectionFetcher implements DirectionsFetcher {
    private String apiKey;
    private static final String APIBASEURL = "https://maps.googleapis.com/maps/api/directions/json";

    GoogleDirectionFetcher(String apiKey) {

    }

    @Override
    public void setStartAddress(String startAddress) {

    }

    @Override
    public void setEndAddress(String endAddress) {

    }

    @Override
    public DirectionsHandler fetchDirections() throws DirectionFetchException {
        return null;
    }

    @Override
    public void cancelGetDirections() {

    }
}
