package com.example.falconsselfdrivingrobo.interfaces.directionAPIs;

public interface DirectionsFetcher {
    public void setStartAddress(String startAddress);
    public void setEndAddress(String endAddress);
    public DirectionsHandler fetchDirections() throws DirectionFetchException;
    public void cancelGetDirections();
}
