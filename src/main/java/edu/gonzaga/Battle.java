package edu.gonzaga;

public class Battle extends Ship{
    private final int length = 4; 
    private shipType shipId = shipType.BATTLE;

    public Battle(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    /**
     * multiShot() checks five spaces in one turn, selected in a small "X" shape.
     */
    public void multiShot( Coordinate centerCoordinate, Board enemyBoard ){
        //user selects center tile to shoot
        
        // Store X and Y coordinate values here for ease of checking multiple spaces quickly:
        Integer centerX = centerCoordinate.x();
        Integer centerY = centerCoordinate.y();

        Coordinate cornerCoordinate;

        // Check center tile for hit:
        enemyBoard.setMarked( centerCoordinate );

        // Check lower right space ( x++ y++ ):
        cornerCoordinate = new Coordinate( centerX++, centerY++ );
        enemyBoard.setMarked( cornerCoordinate );
        
        // Check upper right space ( x++ y-- ):
        cornerCoordinate = new Coordinate( centerX++, centerY-- );
        enemyBoard.setMarked( cornerCoordinate );

        // Check lower left space ( x-- y++ ):
        cornerCoordinate = new Coordinate( centerX--, centerY++ );
        enemyBoard.setMarked( cornerCoordinate );

        // Check upper left space ( x-- y-- ):
        cornerCoordinate = new Coordinate( centerX--, centerY-- );
        enemyBoard.setMarked( cornerCoordinate );
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
