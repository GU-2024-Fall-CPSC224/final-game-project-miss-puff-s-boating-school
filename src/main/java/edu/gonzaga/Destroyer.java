package edu.gonzaga;

public class Destroyer extends Ship {
    private final int length = 2; 
    private shipType shipId = shipType.DESTROYER;

    public Destroyer(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    //use ability: one-shot enemy boat
    public void powerShot(){
        //user selects tile to shoot
        //if its a hit, all other tiles occupied by that ship will be marked, and ship is sunk
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
