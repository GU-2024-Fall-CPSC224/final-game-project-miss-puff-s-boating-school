package edu.gonzaga;

public class Sub extends Ship {
    private final int length = 3; 
    private shipType shipId = shipType.SUB;

    public Sub(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    //use ability: hide from recon (passive)
    //nothing really special in this code, actually take place in carriers code

    @Override
    public int getLength() {
        return length;
    }
    @Override
    public shipType getType() {
        return shipId;
    }
   
}
