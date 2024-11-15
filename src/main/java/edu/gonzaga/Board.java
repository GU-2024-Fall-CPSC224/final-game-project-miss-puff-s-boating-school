package edu.gonzaga;

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
    public boolean isMarked(int x, int y){
        return markers[y][x];
    }


    /**
     * setMarked() sets the marker of this tile as a hit or miss.
     * @param x x-coordinate of attempted shot
     * @param y y-coordinate of attempted shot
     * @param hit does that tile have a boat on it
     */
    public void setMarked(int x, int y, boolean hit){

        markers[x][y] = true;
    }


    /**
     * isShipSunk() checks whether a just-hit ship is now "sunk" and removed from the game.
     * @param attackedShip ship that has part of itself in the tile that was hit
     * @return if the ship is has all tiles hit, and is sunk
     */
    public boolean isShipSunk(Ship attackedShip){
        //check if all other x and y have been hit for the ship, if so return true
        return false;
    }

    /**
     * addShip() adds a ship to the array list of ships sorted on the board.
     * @param newShip
    */
    public void addShip(Ship newShip ){
      //add a boat to the arraylist of active boats:
      shipList.add( newShip );
    }


    /**
     * isMarkerHit() determines if the checked space contains a ship by subsiquently
     * checking every space that is overlapping with a ship.
     * 
     * @param markerX -coordinate of attempted shot
     * @param markerY -coordinate of attempted shot
     * @return is there a ship on that tile
     */
    public boolean isMarkerHit(int markerX, int markerY){
        
        // Stores whether a ship was found while checking ship spaces.
        Boolean shipDetected = false;

        // Check if the checked space hits a ship:
        for ( Ship ship : shipList ) {
            // Get the coordinate of the ship's front / tip, and its dircetion:
            Integer shipXCoord = ship.getX();
            Integer shipYCoord = ship.getY();
            Boolean tempDirection = ship.isVertical();
            
            for ( int i = 0; i < ship.getLength(); i++ ) {
                
                // Compare marker coordinates against each ship coordinate and see if they overlap:
                if ( ( shipXCoord == markerX ) && ( shipYCoord == markerY ) ) {
                    // A ship overlaps with the space checked! YOU'VE HIT A BATTLESHIP!
                    shipDetected = true;
                    // Potentially return here, as you don't need to check any other spaces?
                }
                // No overlapping ships found. check next section of ship.
                else {
                    // If the ship is facing downwards:
                    if ( tempDirection == true ) {
                        shipYCoord++;
                    }
                    // Else if the ship is facing to the right:
                    else {
                        shipXCoord++;
                    }
                }
            } // All sections of a ship checked.
        } // All ships checked.
        
        return shipDetected;
    }
    //for testing purposes, no GUI
    public void printBoard(){
        for (int i = 0; i < markers.length; i++){
            for (int j = 0; j < markers[i].length; j++){
                if (markers[i][j] == true){
                    if (isMarkerHit(j,i)){
                        System.out.print(" X ");
                    }
                    else {
                        System.out.print(" O ");
                    }
                }
                else {
                    System.out.print(" ~ ");
                }
            }
            System.out.println("");
        }
    }
}
