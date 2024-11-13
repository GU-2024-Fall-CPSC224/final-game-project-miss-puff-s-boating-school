package edu.gonzaga;

import java.util.ArrayList;

public class Board {
    public static final int BOARD_SIZE = 10;

    private ArrayList<Ship> ships;
    private boolean[][] markers;

    public Board() {
        ships = new ArrayList<>();
        markers = new boolean[BOARD_SIZE][BOARD_SIZE];
    }
    /**
     * Is this tile marked?
     * @param x coordinate
     * @param y coordinate
     * @return is there a mark on that tile
     */
    public boolean isMarked(int x, int y){
        return markers[y][x];
    }
    /**
     * Does this hit sink the ship?
     * @param attackedShip ship that has part of itself in the tile that was hit
     * @return if the ship is has all tiles hit, and is sunk
     */
    public boolean isShipSunk(Ship attackedShip){
        //check if all other x and y have been hit for the ship, if so return true
        return false;
    }
    /**
     * Set the marker of this tile as a hit or miss
     * @param x x-coordinate of attempted shot
     * @param y y-coordinate of attempted shot
     * @param hit does that tile have a boat on it
     */
    public void setMarked(int x, int y, boolean hit){
        //if hit: do something in Swing
        markers[y][x] = true;
    }
    public void addShip(Ship boat){
        //add a boat to the arraylist of active boats
    }
    /**
     * Is this marker a hit or miss
     * @param x -coordinate of attempted shot
     * @param y -coordinate of attempted shot
     * @return is there a ship on that tile
     */
    public boolean isMarkerHit(int x, int y){
        return false;
    }
    //for testing purposes, no GUI
    public void printBoard(){
        for (int i = 0; i < markers.length; i++){
            for (int j = 0; j < markers[i].length; j++){
                if (markers[i][j] == true){
                    if (isMarkerHit(j,i)){
                        System.out.print(" X ");
                    }
                    else {
                        System.out.print(" O ");
                    }
                }
                else {
                    System.out.print(" ~ ");
                }
            }
            System.out.println("");
        }
    }
}
