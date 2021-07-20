package com.example.falconsselfdrivingrobo.interfaces.directionAPIs;

import java.util.List;

public interface DirectionsHandler {
    public List<Direction> getDirections();
    public Direction getCurrentAndMoveToNext();
    public void resetToStart();
    public double getRemainingDistance();
    public double getTotalDistance();
    public void setMetrics(DistanceMetrics metric);
}
