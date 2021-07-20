package com.example.falconsselfdrivingrobo.interfaces.directionAPIs;

public enum DistanceMetrics {
    meters(39.3701),
    feets(12.0),
    inches(1.0),
    miles(63360.0),
    kilometers(39370.1);

    private final double unitInInches;

    DistanceMetrics(double unitInInches) {
        this.unitInInches = unitInInches;
    }


    public double getUnitInInches() {
        return unitInInches;
    }

}
