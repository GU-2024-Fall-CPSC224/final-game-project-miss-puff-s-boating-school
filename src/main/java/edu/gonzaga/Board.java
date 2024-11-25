package edu.gonzaga;

import edu.gonzaga.ships.Ship;

import java.util.ArrayList;



public class Board {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------

    // BOARD_SIZE holds the current size of the board in both rows and columns
    private static final int BOARD_SIZE = 10;

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

        // When checking a space, determine if a ship occupies it (a.k.a. the ship is "hit").
        isMarkerHit( playerCoord );
    }


    /**
     * isShipSunk() checks whether a just-hit ship is now "sunk" and removed from the game.
     * @param attackedShip ship that has part of itself in the tile that was hit
     * @return if the ship is has all tiles hit, and is sunk
     */
    public boolean isShipSunk(Ship attackedShip){

        // segmentCoordnets contains the coordinate objects of each segment of the current ship being checked.
        ArrayList<Coordinate> segmentCoordinates = new ArrayList<>();
        segmentCoordinates = attackedShip.getAllCoordinates();

        for (Coordinate coordinate : segmentCoordinates) {
            // If a segment of the ship remains unmarked, return false.
            if ( isMarked( coordinate ) == false ) {
                return false;
            }
        }
        // If all ship segments have been marked, return true.
        return true; // <---------- This should probably be changed to call a Ship method setIsSunk on the attacked ship.
    }


    /**
     * addShip() adds a ship to the array list of ships sorted on the board.
     * @param newShip
    */
    public void addShip(Ship newShip ){

        // If the ship is not placed in a valid location, return without adding the ship.
        if ( validateShipPlacement( newShip ) == false ) {
            return;
        }

        // The placement of the ship is valid, add it to the list.
        shipList.add( newShip );
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

        // Generate a test ship:
        //Ship checkShip = new GenericShip( spaceChosen.x(), spaceChosen.y(), vertical, shipLength );
        
        // Get ship coordinates:
        ArrayList<Coordinate> segmentCoordinates = new ArrayList<>();
        segmentCoordinates = checkShip.getAllCoordinates();

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
            ArrayList<Coordinate> collidingShipCoordinates = new ArrayList<>();
            collidingShipCoordinates = collidingShip.getAllCoordinates();

            // If the collision ship has an intersecting point with your new ship, return false.
            for ( Coordinate coordinate : segmentCoordinates ) {
                if ( collidingShipCoordinates.contains( coordinate ) ) {
                    System.out.println( "INVALID: ship collision detected at X-" 
                                        + coordinate.x() + " : Y-" + coordinate.y() );
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
        boolean hasShip = false;

        if (getShip(playerCoord) != null){
            hasShip = true;
        }
        return hasShip;
    }


    /**
     * getAllShipCoordinates takes each ship in the board's shiplist and calculates the coordinates
     * of each "segment" of each ship, returning all these coordinates in an array.
     */
    public ArrayList<Integer> getAllShipCoordinates(){
        ArrayList<Integer> shipCoordinates = new ArrayList<>();
        return shipCoordinates;
    }


    /**
     * printBoard() is used for testing purposes, and prints an ASCII-based version
     * of the player board / G.U.I., with all markers shown.
     * 
     */
    public void printBoard(){
        // for all rows.
        for (int i = 0; i < markers.length; i++){
            // for all columns.
            for (int j = 0; j < markers[i].length; j++){
                // If the space printed has a set marker, display it.
                if (markers[i][j] == true){
                    // If the marker is a hit, display a hit marker.
                    if ( isMarkerHit( new Coordinate( i, j ) ) ){ // <<<<<< Added coordinate here so this still works. Hopefully.
                        System.out.print(" X ");
                    }
                    // If the marker is a miss, display a miss marker.
                    else {
                        System.out.print(" O ");
                    }
                }
                // If the printed space has no marker status, print the default board background.
                else {
                    System.out.print(" ~ ");
                }
            }
            System.out.println("");
        }
    }
}
