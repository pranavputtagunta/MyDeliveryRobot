package com.example.falconsselfdrivingrobo.interfaces.directionAPIs;

public class Direction {
    private final DirectionType type;
    private final float distance;
    private final DistanceMetrics unitMetrics;

    public Direction(DirectionType type, float distance, DistanceMetrics unitMetrics) {
        this.type = type;
        this.distance = distance;
        this.unitMetrics = unitMetrics;
    }

    public DirectionType getType() {
        return type;
    }

    public float getDistance() {
        return distance;
    }

    public DistanceMetrics getUnitMetrics() {
        return unitMetrics;
    }
}
