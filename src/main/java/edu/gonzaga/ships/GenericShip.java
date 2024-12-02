package edu.gonzaga.ships;

public class GenericShip extends Ship {
    private final int length;

    public GenericShip(int x, int y, boolean isVertical, int length) {
        super(x, y, isVertical);
        this.length = length;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public ShipType getType() {
        // Because this is a generic ship, we don't know what type it is
        return null;
    }
}
