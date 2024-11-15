package edu.gonzaga;

public abstract class Ship {
    
    // -----------------------------------
    // ATTRIBUTES START HERE
    // -----------------------------------
    
    private final int x;
    private final int y;

    // Determines if the ship will extend downwards or to the right.
    private final boolean isVertical;

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
        this.x = x;
        this.y = y;
        this.isVertical = isVertical;
    }


    /**
     * getX() gets the x coordinate of the front / nose of the ship.
     */
    public int getX() {
        return x;
    }


    /**
     * getY() gets the y coordinate of the front / nose of the ship.
     * 
     * @return y coordinate
     */
    public int getY() {
        return y;
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
}
