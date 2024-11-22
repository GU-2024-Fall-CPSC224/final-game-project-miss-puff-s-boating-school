package edu.gonzaga;

import java.util.ArrayList;

public abstract class Ship {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------

    /**
     * Primary ship coordinates are stored here.
     */
    private Coordinate position;

    /**
     * Determines if the ship will extend downwards or to the right.
     */ 
    private final boolean isVertical;

    private boolean toggleAbility = false;


    enum shipType {
        CARRIER,
        BATTLE,
        SUB,
        CRUISER,
        DESTROYER,
        GENERIC //to be deleted later, just for testing purposes
    }

    /**
     * Holds the state of whether the ship is still in play.
     */ 
    private boolean isSunk;


    // -----------------------------------
    // METHODS START HERE
    // -----------------------------------


    /**
     * Basic ship constructor
     * @param x
     * @param y 
     * @param isVertical
     */
    public Ship(int x, int y, boolean isVertical) {
        
        //Set ship primary coordinates:
        this.position = new Coordinate( x, y );
        this.isVertical = isVertical;
        this.isSunk = false;
    }


    /**
     * getPrimaryCoordinate() gets the primary (main) coordinate of a ship.
     * 
     * @return Coordinate primaryShipCoordinates
     */
    public Coordinate getPosition() {
        return position;
    }


    /**
     * isVertical() checks the direction the ship is facing (upwards to to the right)
     *
     *  @return True/false the ship is facing vertical (upwards / straight)
     */
    public boolean isVertical() {
        return isVertical;
    }


    /** 
     * getLength() returns the length of the ship.
    */
    public abstract int getLength();

    public abstract shipType getType();



    /**
     * sinkShip() sets when the ship is out of play.
     */
    public void sinkShip() {
        isSunk = true;
    }


    /**
     * getIsSunk() returns the ship's status on whether it is in play.
     * @return true/fasle ship is "sunk" and out of play.
     */
    public Boolean getIsSunk() {
        return isSunk;
    }

    public boolean canUseAbility(){
        return toggleAbility;
    }
    public void setAbilityUse(boolean toggle){
        toggleAbility = toggle;
    }


    /** 
     * getAllCoordinates() takes the primary coordinates of a ship, and calculates each
     * additional coordinate point the ship occupies. A ship should know where it is.
     * 
     * THIS IS THE HELPER FUNCTION THAT REPLACES the isMarkerHit() comparison.
     * This is what the coordinate class was primary implemented for.
     */
    public ArrayList<Coordinate> getAllCoordinates() {
        
        // Generate a new coordinate arraylist. This will contain all the calculated coordinates.
        ArrayList<Coordinate> shipCoordinates = new ArrayList<>();
        
        Integer shipXCoord = position.x();
        Integer shipYCoord = position.y();
        // Add the primary ship coordinates to the array as well!
        shipCoordinates.add( position );

        Integer shipLength = this.getLength();

        // For each section of the ship, increment it's coordinates (depending on direction) and add the to the list!
        // NOTE: i is set to 1 to account for the primaryShipCoordinate!
        for ( int i = 1; i < shipLength; i++ ) {
            
            // If the ship is facing downwards:
            if ( isVertical == true ) {
                shipYCoord++;
            }
            // Else if the ship is facing to the right:
            else {
                shipXCoord++;
            }
            // Add the new coordinate to the shipCoordinates list.
            shipCoordinates.add( new Coordinate( shipXCoord, shipYCoord ) );
            
        } // All ship sections assigned coordinates.
        // Return the calculated ship coordinates.
        return shipCoordinates;
    }

}
