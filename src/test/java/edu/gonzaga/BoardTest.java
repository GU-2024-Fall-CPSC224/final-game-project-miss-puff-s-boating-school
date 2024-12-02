package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import edu.gonzaga.ships.GenericShip;
import edu.gonzaga.ships.Ship;
import org.junit.jupiter.api.Test;

public class BoardTest {
    
    @Test
    void checkIsMarked() {
        // Generate test board
        Board testBoard = new Board();

        // Make a test coordinate.
        Coordinate testCoordinate = new Coordinate(0, 0);

        // Mark the board at the test coordinate.
        testBoard.setMarked( testCoordinate );

        System.out.println( "Expected boolean state is false: the checked tile is not marked." );
        assertTrue( testBoard.isMarked( testCoordinate ) );
    }

    @Test
    void checkIsMarkedFalse() {
        // Generate test board
        Board testBoard = new Board();

        // Make a test coordinate.
        Coordinate testCoordinate = new Coordinate(0, 0);
        Coordinate falseCoordinate = new Coordinate(5, 6);

        // Mark the board at the test coordinate.
        testBoard.setMarked( testCoordinate );

        System.out.println( "Expected boolean state is false: the checked tile is not marked." );
        assertFalse( testBoard.isMarked( falseCoordinate ) );
    }

    @Test 
    void checkVerticalFrontShipHitDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, true, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 5, 5 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }

    @Test 
    void checkVerticalBackShipHitDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, true, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 5, 7 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }

    @Test 
    void checkHorizontalFrontShipHitDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, false, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 5, 5 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }

    @Test 
    void checkHorizontalBackShipHitDetection() {

        // Generate test board
        Board testBoard = new Board();

        // Add a test ship to the board, facing downward and starting at coordinate [5, 5]:
        testBoard.addShip( new GenericShip(5, 5, false, 3) );
        // Add test coordinate to simulate player input:
        Coordinate testCoordinate = new Coordinate( 7, 5 );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the checked tile contains a ship. HIT!" );
        assertTrue( testBoard.isMarkerHit( testCoordinate ) );
    }

    // ------------------------ Valid Ship Placement tests: --------------------------------

    @Test
    void checkValidShipPlacement() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(5, 5, false, 3);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the ship can be placed." );
        assertTrue( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement2() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(2, 8, false, 7);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is true: the ship can be placed." );
        assertTrue( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement3() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(11, 5, false, 3);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is false: the ship cannot be placed." );
        assertFalse( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement4() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(5, 11, false, 1);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is false: the ship cannot be placed." );
        assertFalse( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement5() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(5, 10, true, 3);

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is false: the ship cannot be placed." );
        assertFalse( testBoard.validateShipPlacement( testShip ) );
    }

    @Test
    void checkValidShipPlacement6() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(5, 0, true, 5);
        Ship collisionShip = new GenericShip(2, 3, false, 5);

        // Add the collision ship to the board:
        testBoard.addShip( collisionShip );

        // Check if the player's chosen space overlaps with a ship!
        System.out.println( "Expected boolean state is false: the ship cannot be placed." );
        assertFalse( testBoard.validateShipPlacement( testShip ) );
    }

    // ---------------------------- ship sinking method checks -----------------------------

    @Test
    void checkIsShipSunk() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(4, 4, true, 4);
        testBoard.addShip(testShip);

        // Hit boat segments:
        for ( int i = 0; i < 4; i++ ) {
            // Make a coordinate:
            Coordinate testCoordinate = new Coordinate( 4, (4 + i) );
            testBoard.setMarked( testCoordinate );
        }

        // Check if isShipSunk() recognizses that a ship has been sunk:
        System.out.println( "Expected state is true: A ship has been sunk." );
        assertTrue( testShip.getIsSunk() );
    }

    @Test
    void checkIsShipSunk2() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(4, 4, true, 4);
        testBoard.addShip(testShip);

        // Hit boat segments:
        for ( int i = 0; i < 4; i++ ) {
            // Make a coordinate:
            Coordinate testCoordinate = new Coordinate( (2 + i), (6) );
            testBoard.setMarked( testCoordinate );
        }

        // Check if isShipSunk() recognizses that a ship has been sunk:
        System.out.println( "Expected state is false: A ship has not been sunk." );
        assertFalse(testShip.getIsSunk() );
    }


    @Test
    void checkAreAllShipsSunk() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(4, 4, true, 4);
        testBoard.addShip(testShip);

        // Hit boat segments:
        for ( int i = 0; i < 4; i++ ) {
            // Make a coordinate:
            Coordinate testCoordinate = new Coordinate( (4), (4 + i) );
            testBoard.setMarked( testCoordinate );
        }

        // Check if isShipSunk() recognizses that a ship has been sunk:
        System.out.println( "Expected state is true: All ships have been sunk." );
        assertTrue( testBoard.checkAllShipsSunk() );
    }


    @Test
    void checkAreAllShipsSunk2() {

        // Generate test board
        Board testBoard = new Board();

        // Create a test ship for the board, facing downward and starting at coordinate [5, 5]:
        Ship testShip = new GenericShip(4, 4, true, 4);
        testBoard.addShip(testShip);

        Ship testShip2 = new GenericShip(1, 1, false, 4);
        testBoard.addShip(testShip2);

        // Sink the ships!
        Coordinate ship1Coordinate = new Coordinate(4, 4);
        testBoard.getShip( ship1Coordinate ).sinkShip();

        Coordinate ship2Coordinate = new Coordinate(1, 1);
        testBoard.getShip( ship2Coordinate ).sinkShip();

        // Check if isShipSunk() recognizses that a ship has been sunk:
        System.out.println( "Expected state is true: All ships have been sunk." );
        assertTrue( testBoard.checkAllShipsSunk() );
    }
}