package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
    
    @Test
    void checkGetX() {
        Integer expectedX = 0;

        // Create test coordinate.
        Coordinate testCoord = new Coordinate(0, 0);

        System.out.println( "Expected output for x-coordinate is 0.");
        assertEquals(expectedX, testCoord.x());
    }

    @Test
    void checkGetY() {
        Integer expectedY = 0;

        // Create test coordinate.
        Coordinate testCoord = new Coordinate(0, 0);

        System.out.println( "Expected output for y-coordinate is 0.");
        assertEquals(expectedY, testCoord.y());
    }

    @Test 
    void checkCompareCoordinates() {

        // Create test coordinates.
        Coordinate testCoord = new Coordinate(0, 0);
        Coordinate comparisonCoord = new Coordinate(0, 0);

        System.out.println( "Expected output for comparison is true: the coordinates are equal."); 
        assertTrue( comparisonCoord.equals(testCoord) ); 
    }

    @Test 
    void checkCompareCoordinates2() {

        // Create test coordinates.
        Coordinate testCoord = new Coordinate(4, 9);
        Coordinate comparisonCoord = new Coordinate(4, 9);

        System.out.println( "Expected output for comparison is true: the coordinates are equal."); 
        assertTrue( comparisonCoord.equals(testCoord) ); 
    }

    @Test 
    void checkCompareCoordinates3() {

        // Create test coordinates.
        Coordinate testCoord = new Coordinate(4, 9);
        Coordinate comparisonCoord = new Coordinate(3, 5);

        System.out.println( "Expected output for comparison is false: the coordinates are not equal."); 
        assertFalse( comparisonCoord.equals(testCoord) ); 
    }
}
