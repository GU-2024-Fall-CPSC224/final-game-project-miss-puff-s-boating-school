package edu.gonzaga;

public class Destroyer extends Ship {
    private final int length = 2; 
    private shipType shipId = shipType.DESTROYER;

    public Destroyer(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    
    /**
     * powerShot() sinks any shit it hits instantly, marking all spaces hit.
     */
    public void powerShot( Coordinate playerCoordinate, Board enemyBoard ) {
        
        // Check initial space:
        if ( enemyBoard.isMarkerHit( playerCoordinate ) == true ) {

            // Get the player's ship, their coordinates, and SINK THAT SUCKAH! ( using a for loop probably)
        }
    }

    @Override
    public int getLength() {
        return length;
    }
    @Override
    public shipType getType() {
        return shipId;
    }

}
