package com.example.falconsselfdrivingrobo.utils;

import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DistanceMetrics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DistanceUtil {
    public static double getDistanceInMeters(double distance, DistanceMetrics currentMetric) {
        return distance * currentMetric.getUnitInMeters();
    }

    public static long getNumberOfSteps(double distance, DistanceMetrics currentMetric, double stepMeasurement) {
        return (long) Math.ceil(getDistanceInMeters(distance, currentMetric) / stepMeasurement);
    }

    public static String readStringFromFile(String filePathStr) throws Exception {
        StringBuffer builder = new StringBuffer();
        FileInputStream fs = new FileInputStream(filePathStr);
        Reader reader = new BufferedReader(new InputStreamReader(fs));
        char[] buffer = new char[8192];
        int read;
        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
            builder.append(buffer, 0, read);
        }
        return builder.toString();
    }
}
