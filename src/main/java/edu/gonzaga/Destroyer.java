package edu.gonzaga;

import java.util.ArrayList;

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

            // Get the enemy player ship hit.
            Ship enemyShip = enemyBoard.getShip( playerCoordinate );
            // Get all the coordinates from the enemy player's ship.
            ArrayList<Coordinate> enemyShipCoordinates = new ArrayList<>();
            enemyShipCoordinates = enemyShip.getAllCoordinates();

            // Set all coordinate of the enemy ship to marked:
            for ( Coordinate coordinate : enemyShipCoordinates ) {
                enemyBoard.setMarked( coordinate );
            }
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
