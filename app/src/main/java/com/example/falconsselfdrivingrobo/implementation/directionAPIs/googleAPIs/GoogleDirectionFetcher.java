package com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs;

import com.example.falconsselfdrivingrobo.implementation.directionAPIs.DefaultDirectionHandler;
import com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs.jsonModelClasses.Leg;
import com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs.jsonModelClasses.Route;
import com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs.jsonModelClasses.Step;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.Direction;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionFetchException;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionType;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionsFetcher;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionsHandler;
import com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs.jsonModelClasses.DirectionRoot;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DistanceMetrics;
import com.example.falconsselfdrivingrobo.interfaces.networkservice.HTTPDataProvider;
import com.example.falconsselfdrivingrobo.implementation.directionAPIs.networkservice.HTTPURLConnectionDataProvider;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GoogleDirectionFetcher implements DirectionsFetcher {
    public static final String BASEAPIURL = "https://maps.googleapis.com/maps/api/directions/json";
    private String apiKey = "" ;
    private HTTPDataProvider httpDataProvider;

    private String startAddress;
    private String endAddress;

    public GoogleDirectionFetcher(String apiKey) {
        this.apiKey = apiKey;
        httpDataProvider = new HTTPURLConnectionDataProvider();
    }

    public GoogleDirectionFetcher(String apiKey, HTTPDataProvider httpDataProvider) {
        this.apiKey = apiKey;
        this.httpDataProvider = httpDataProvider;
    }

    @Override
    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    @Override
    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    @Override
    public DirectionsHandler fetchDirections() throws DirectionFetchException {
        try {
            httpDataProvider.setURL(BASEAPIURL);
            httpDataProvider.setRequestParameters(getRequestParameters());
            String responsejson = httpDataProvider.fetchURL();
           //gson= new GSon();
            Gson gson = new Gson();
            DirectionRoot directionsData = gson.fromJson(responsejson, DirectionRoot.class);
            return convertResponseObjects(directionsData);
        }
        //catch parse exception
        catch (Exception e) {
            throw new DirectionFetchException(e.getMessage());
        }
    }

    @Override
    public void cancelGetDirections() {
        httpDataProvider.cancel();
    }

    //convenience private methods
   private Map<String,String> getRequestParameters() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("origin",startAddress);
        parameters.put("destination",endAddress);
        parameters.put("key",apiKey);

        return  parameters;

   }

    private DirectionsHandler convertResponseObjects(DirectionRoot directionData) throws Exception
    {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        if (directionData == null || directionData.routes == null || directionData.routes.size() == 0) {
            throw new Exception("No routes present in response from google API");
        }

        Route firstRoute = directionData.routes.get(0);
        if (firstRoute.legs == null || firstRoute.legs.size() == 0  ) {
            throw new Exception("No legs in first route given by google api");
        }

        Leg firstLeg = firstRoute.legs.get(0);
        if (firstLeg.steps == null || firstLeg.steps.size() == 0) {
            throw new Exception("No steps provided in the route given by google");
        }

        //go through each step, if step has maneuver add a direction step for turn left/right followed by distance step
        for (Step step : firstLeg.steps){
            //add one direction step alone for right/left if found
            Direction maneuverStep = getManeuverDirection(step);
            if (maneuverStep != null) {
                directions.add(maneuverStep);
            }

            //add one direction step for distance
            /*Direction distanceDirection = getDistanceDirection(step);
            if (distanceDirection != null) {
                directions.add(distanceDirection);
            }
             */
            //use value which is always in meters
            if (step.distance != null) {
                directions.add(new Direction(DirectionType.straight,step.distance.value,DistanceMetrics.meters));
            }
        }

        DefaultDirectionHandler directionHandler = new DefaultDirectionHandler(directions)    ;
        return directionHandler;

    }

    //Extract distance from the step entry along with unit
    private Direction getDistanceDirection(Step step) throws Exception {
       if (step.distance == null || step.distance.text == null || step.distance.text.trim().length() == 0)  {
           return  null;
       }

       String distanceStr = step.distance.text.trim();
       String[] splits = distanceStr.split(" ",2);

       if (splits.length < 2) {
          throw new Exception("Could not parse the distance string in step from google api respose");
       }
       String distance = splits[0];
       String unit = splits[1];

       DistanceMetrics metric = DistanceMetrics.miles;
       if (unit.equals("mi")) {
            metric = DistanceMetrics.miles;
       } else if (unit.equals("ft")) {
            metric = DistanceMetrics.feets;
       } else if (unit.equals("in")) {
           metric = DistanceMetrics.inches;
       } else if (unit.equals("m")) {
           metric = DistanceMetrics.meters;
       } else if (unit.equals("km")) {
           metric = DistanceMetrics.kilometers;
       } else {
           throw new Exception("Unknown unit of distance in google response "+distanceStr);
       }

       try {
           float distancemeasure = Float.parseFloat(distance);
           return new Direction(DirectionType.straight,distancemeasure,metric);
       } catch ( NumberFormatException e) {
           throw new Exception("Bad number found in distance measure for step from google response "+distanceStr);
       }

    }

    private Direction getManeuverDirection(Step step) {
        if (step.maneuver == null || step.maneuver.length()==0) {
            return  null;
        }

        DirectionType directionType = DirectionType.unknown;
        if (step.maneuver.equals("turn-left")) {
           directionType = DirectionType.leftTurn;
        } else if(step.maneuver.equals("turn-right")){
            directionType = DirectionType.rightTurn;
        } else {
            directionType = DirectionType.unknown;
        }
        return new Direction(directionType,0,DistanceMetrics.meters);
    }
}
