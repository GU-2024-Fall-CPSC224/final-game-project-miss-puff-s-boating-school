package edu.gonzaga;

import edu.gonzaga.ships.Ship;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;



public class Board {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------

    // BOARD_SIZE holds the current size of the board in both rows and columns
    private static final int BOARD_SIZE = 10;

    public final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    // shipList stores all ships placed on the player's board.
    private ArrayList<Ship> shipList;

    // markers stores all markers placed on the player's board.
    private boolean[][] markers;

    // -----------------------------------
    // METHODS START HERE
    // -----------------------------------

    /**
     * Basic constructor of a board.
     */ 
    public Board() {
        shipList = new ArrayList<>();
        // Generate a new blank list for holding markers
        markers = new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Returns an array of ships on the board.
     */
    public Ship[] getShips() {
        return shipList.toArray(new Ship[0]);
    }

    /**
     * isMarked() checks a player-specified space for a marker.
     * @param x coordinate
     * @param y coordinate
     * @return true/false there is a mark on the selected tile.
     */
    public boolean isMarked( Coordinate checkCoordinate ){
        //return markers[y][x]; // < --- markers[][] here flips coords?
        return markers[ checkCoordinate.y() ][ checkCoordinate.x() ];
    }

    /**
     * setMarked() sets the marker of this tile as a hit or miss.
     * @param x x-coordinate of attempted shot
     * @param y y-coordinate of attempted shot
     * @param hit does that tile have a boat on it
     */
    public void setMarked( Coordinate playerCoord ){
        markers[ playerCoord.y() ][ playerCoord.x() ] = true;

        // Check if the marker sunk a ship
        Ship attackedShip = getShip(playerCoord);

        if (attackedShip == null) {
            System.out.println("Marker at " + playerCoord + " was a miss.");
        } else {
            // If every segment of the ship has been marked, the ship was sunk
            if (attackedShip.getAllCoordinates().stream().allMatch(this::isMarked)) {
                attackedShip.sinkShip();
                System.out.println("Marker at " + playerCoord + " sunk " + attackedShip);
            } else {
                System.out.println("Marker at " + playerCoord + " hit " + attackedShip);
            }
        }

        pcs.firePropertyChange("mark", null, null);
    }

    /**
     * addShip() adds a ship to the array list of ships sorted on the board.
     * @param newShip
    */
    public void addShip(Ship newShip ) {
        // If the ship is not placed in a valid location, return without adding the ship.
        if ( validateShipPlacement( newShip ) == false ) {
            return;
        }

        System.out.println("Ship placed: " + newShip);

        // The placement of the ship is valid, add it to the list.
        shipList.add( newShip );

        pcs.firePropertyChange("ship", null, null);
    }
    /** getter function of the ship on a given tile
     * searches location by checking every single coordinate of the ships 
     * @param tile tile to be searched
     * @return ship if ship exist in that space, null if the space is empty
     */
    public Ship getShip(Coordinate tile){

        for ( Ship ship : shipList ) {
            // Get the coordinates of the ship's segments:

            // segmentCoordnets contains the coordinate objects of each segment of the current ship being checked.
            ArrayList<Coordinate> segmentCoordinates = new ArrayList<>();
            segmentCoordinates = ship.getAllCoordinates();

            // Compare each segment against the space being checked:
            // If they are equal, return true.
            for ( Coordinate checkingCoord : segmentCoordinates ) {
                if ( checkingCoord.equals( tile ) ) {

                    return ship;
                }
            }
            // If the coordinates are not equal, check next coordinate.
        } // All ships checked.

        return null;
    }

    /**
     * validateShipPlacement() takes a pair of coordinates and checks if a ship
     * can be placed there, comparing ship direction and length.
     * @param newShip
     * @return true / false the ship can be placed at these coordinates.
    */
    //public Boolean validateShipPlacement( Coordinate spaceChosen, Boolean vertical, Integer shipLength ){
    public Boolean validateShipPlacement( Ship checkShip ){
        ArrayList<Coordinate> segmentCoordinates = checkShip.getAllCoordinates();

        // Check ship placement:
        for (Coordinate coordinate : segmentCoordinates) {
            // Check X:
            if ( ( coordinate.x() > 9 ) || (coordinate.x() < 0 ) ) {
                return false;
            }
            // Check Y:
            if ( ( coordinate.y() > 9 ) || (coordinate.y() < 0 ) ) {
                return false;
            }
        }

        // Check for ship collision:
        for ( Ship collidingShip : shipList ) {

            // Get all the potential collision points of each ship placed on the boards.
            ArrayList<Coordinate> collidingShipCoordinates = collidingShip.getAllCoordinates();

            // If the collision ship has an intersecting point with your new ship, return false.
            for ( Coordinate coordinate : segmentCoordinates ) {
                if ( collidingShipCoordinates.contains( coordinate ) ) {
                    return false;
                }
            }
        }

        // If all spaces are checked without issue, then return true.
        return true;
    }


    /**
     * isMarkerHit() determines if the checked space contains a ship by calling getShip
     * 
     * @param markerX -coordinate of attempted shot
     * @param markerY -coordinate of attempted shot
     * @return is there a ship on that tile
     */
    public boolean isMarkerHit( Coordinate playerCoord ){
        return getShip( playerCoord ) != null;
    }
}
