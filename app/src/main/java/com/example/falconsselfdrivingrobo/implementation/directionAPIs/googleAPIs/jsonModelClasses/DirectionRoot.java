package com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs.jsonModelClasses;

import java.util.List;

public class DirectionRoot {
    public List<GeocodedWaypoint> geocoded_waypoints;
    public List<Route> routes;
    public String status;
}
