package com.example.falconsselfdrivingrobo;

import com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs.GoogleAPIKeys;
import com.example.falconsselfdrivingrobo.implementation.directionAPIs.googleAPIs.GoogleDirectionFetcher;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.Direction;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionFetchException;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionsFetcher;
import com.example.falconsselfdrivingrobo.interfaces.directionAPIs.DirectionsHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void checkfileread() {
        String value = "";
        DirectionsFetcher directionFetcher = new GoogleDirectionFetcher(GoogleAPIKeys.directionAPIKey);
        directionFetcher.setStartAddress("6748 torrey pines drive, irving, texas");
        directionFetcher.setEndAddress("1148 pedernales trail, irving, texas");
        try {
            DirectionsHandler directionsHandler = directionFetcher.fetchDirections();
            List<Direction> directions = directionsHandler.getDirections();
            Assert.assertTrue(directions.size() > 0);
         //   assertEquals(71.0,directionsHandler.getRemainingDistance(),0);
            Direction direction = directionsHandler.getCurrentAndMoveToNext();
            while(direction != null) {
                System.out.println(direction);
                System.out.println(directionsHandler.getRemainingDistance());
                direction = directionsHandler.getCurrentAndMoveToNext();
            }
           System.out.println(directions.toString());
            directionsHandler.resetToStart();
         //   assertEquals(71.0,directionsHandler.getRemainingDistance(),0);
        } catch (DirectionFetchException e) {
            Assert.fail("Exception while getting google directions"+e.getMessage());
        }
    }


}