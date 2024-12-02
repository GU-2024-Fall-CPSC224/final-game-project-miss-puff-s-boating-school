package edu.gonzaga.ships;

public class Submarine extends Ship {
    private final int length = 3; 
    private ShipType shipId = ShipType.SUBMARINE;

    public Submarine(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    //use ability: hide from recon (passive)
    //nothing really special in this code, actually take place in carriers code

    @Override
    public int getLength() {
        return length;
    }
    @Override
    public ShipType getType() {
        return shipId;
    }
   
}
