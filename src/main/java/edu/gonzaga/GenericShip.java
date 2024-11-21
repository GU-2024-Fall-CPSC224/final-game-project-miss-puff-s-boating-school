package edu.gonzaga;

public class GenericShip extends Ship {
    private final int length;
    private shipType shipId = shipType.GENERIC;

    public GenericShip(int x, int y, boolean isVertical, int length) {
        super(x, y, isVertical);
        this.length = length;
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
