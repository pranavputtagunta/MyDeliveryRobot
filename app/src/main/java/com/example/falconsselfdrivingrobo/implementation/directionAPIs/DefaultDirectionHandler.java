package com.example.falconsselfdrivingrobo.implementation.directionAPIs;

import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.Direction;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionsHandler;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DistanceMetrics;
import com.example.falconsselfdrivingrobo.utils.DistanceUtil;

import java.util.List;

//TODO
public class DefaultDirectionHandler implements DirectionsHandler {
    private List<Direction> directions;
    private int currentIndex;
    private double totalDistance;
    private DistanceMetrics baseMetric = DistanceMetrics.inches;

    public DefaultDirectionHandler(List<Direction> directions) {
        this.directions = directions;
        currentIndex = 0;
        totalDistance = 0;
        for (Direction direction : directions) {
            totalDistance = totalDistance + DistanceUtil.getDistanceInMeters(direction.getDistance(), direction.getUnitMetrics());
        }
    }


    @Override
    public List<Direction> getDirections() {
        return directions;
    }

    @Override
    public Direction getCurrentAndMoveToNext() {
        if (currentIndex >= directions.size()) {
           return null;
        }
        Direction currentDirectionStep = directions.get(currentIndex);
        currentIndex++;
        return currentDirectionStep;
    }

    @Override
    public void resetToStart() {
        currentIndex = 0;
    }

    @Override
    public double getRemainingDistance() {
        double remainingDistance = 0;
        for (int i=currentIndex; i<directions.size(); i++) {
            Direction direction = directions.get(i);
           remainingDistance += DistanceUtil.getDistanceInMeters(direction.getDistance(), direction.getUnitMetrics());
        }
        return remainingDistance;
    }

    @Override
    public double getTotalDistance() {
        return totalDistance;
    }

    @Override
    public void setMetrics(DistanceMetrics metric) {
        baseMetric = metric;
    }
}
