package com.example.falconsselfdrivingrobo.navigation;

public interface NavigationEngine {
    public void start() throws NavigationException;
    public void stop() throws NavigationException;
    public void pause() throws NavigationException;
    public void resetToStart();
}
