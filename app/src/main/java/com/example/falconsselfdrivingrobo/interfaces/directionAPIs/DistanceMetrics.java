package com.example.falconsselfdrivingrobo.interfaces.directionAPIs;

public enum DistanceMetrics {
    meters(1.0),
    feets(0.3048),
    inches(0.0254),
    miles(1609.34),
    kilometers(1000.0);

    private final double unitInMeters;

    DistanceMetrics(double unitInMeters) {
        this.unitInMeters = unitInMeters;
    }


    public double getUnitInMeters() {
        return unitInMeters;
    }

}
