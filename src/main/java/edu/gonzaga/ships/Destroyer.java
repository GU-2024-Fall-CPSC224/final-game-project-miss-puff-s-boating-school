package edu.gonzaga.ships;

import edu.gonzaga.Board;
import edu.gonzaga.Coordinate;

import java.util.ArrayList;

public class Destroyer extends Ship {
    private final int length = 2; 
    private ShipType shipId = ShipType.DESTROYER;

    public Destroyer(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    
    /**
     * powerShot() sinks any shit it hits instantly, marking all spaces hit.
     */
    public void powerShot(Coordinate playerCoordinate, Board enemyBoard ) {
        
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
    public ShipType getType() {
        return shipId;
    }

}
