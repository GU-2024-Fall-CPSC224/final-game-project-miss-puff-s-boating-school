package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import edu.gonzaga.ships.GenericShip;
import edu.gonzaga.ships.Ship;
import org.junit.jupiter.api.Test;

public class ShipTest {
    
    @Test
    void checkGetPosition() {
        // Expected position compares to the ships's position.
        Coordinate expectedCoordinate = new Coordinate(0, 0);

        // Make a test ship object:
        Ship testShip = new GenericShip(0, 0, false, 0);

        // compare coordinates:
        System.out.println( "Expected result is true: the ship's position matches the coordinate." );
        assertTrue( testShip.getPosition().equals(expectedCoordinate) );
    }

    @Test
    void checkIsVerticalFalse() {
        // Make a test ship object:
        Ship testShip = new GenericShip(0, 0, false, 0);

        // getVerticalBoolean and assert:
        System.out.println( "Expected result is false: the ship is not vertically oriented." );
        assertFalse( testShip.isVertical() );
    }

    @Test
    void checkIsVerticalTrue() {
        // Make a test ship object:
        Ship testShip = new GenericShip(0, 0, true, 0);

        // getVerticalBoolean and assert:
        System.out.println( "Expected result is false: the ship is not vertically oriented." );
        assertTrue( testShip.isVertical() );
    }

    @Test
    void checkGetLength() {
        // Expected length:
        Integer expectedLength = 5;
        
        // Make a test ship object:
        Ship testShip = new GenericShip(0, 0, true, 5);

        // getVerticalBoolean and assert:
        System.out.println( "Expected result is true: the ship's length is equal to the expected length." );
        assertEquals( testShip.getLength(), expectedLength );
    }

    @Test
    void checkSinkShip() {
        // Make a test ship object:
        Ship testShip = new GenericShip(0, 0, true, 5);

        // Sink the ship!
        testShip.sinkShip();

        // Assert:
        System.out.println( "Expected result is true: the ship's length is equal to the expected length." );
        assertTrue( testShip.getIsSunk() );
    }
}
