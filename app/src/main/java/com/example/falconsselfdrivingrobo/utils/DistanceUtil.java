package com.example.falconsselfdrivingrobo.utils;

import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DistanceMetrics;

public class DistanceUtil {
    public static double getDistanceInInches(double distance, DistanceMetrics currentMetric) {
        return distance * currentMetric.getUnitInInches();
    }

    static long getNumberOfSteps(double distance, DistanceMetrics currentMetric, double stepMeasurement) {
        return (long) Math.ceil(getDistanceInInches(distance, currentMetric) / stepMeasurement);
    }
}
