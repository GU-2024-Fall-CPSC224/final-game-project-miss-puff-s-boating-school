package edu.gonzaga;

public class Carrier extends Ship{
    private final int length = 5; 
    private shipType shipId = shipType.CARRIER;

    public Carrier(int x, int y, boolean isVertical) {
        super(x, y, isVertical);
    }

    
    /**Aircraft carrier ability: launch aircraft to do recon, and discover the number of occuipied tiles in a given row
     * 
     * @param rowNum row number to be checked for ships
     * @return number of occupied spaces in the searched row
     */
    public int shipsInRow(int rowNum){
        int tileCount = 0;
        /*for (tiles: row) {
         * if (ship on tile && id != SUB){
         * tileCount++
         * }
         * 
        */
        return tileCount;
        //note: does not return the exact location of the occupied spaces, just the total number in that row
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
