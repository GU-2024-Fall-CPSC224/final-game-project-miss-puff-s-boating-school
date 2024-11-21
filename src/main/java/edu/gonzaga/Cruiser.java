package edu.gonzaga;

public class Cruiser extends Ship {
    private final int length = 3; 
    private shipType shipId = shipType.CRUISER;

    public Cruiser(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    //no ability just chilling

    @Override
    public int getLength() {
        return length;
    }
    @Override
    public shipType getType() {
        return shipId;
    }
}
