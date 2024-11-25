package edu.gonzaga;

import java.util.ArrayList;

import edu.gonzaga.renderer.GameFrame;
import edu.gonzaga.renderer.GamePanel;
import edu.gonzaga.renderer.PlaceShipCallback;

public class Game implements Runnable, PlaceShipCallback {
    
    // --------------------------------------
    //         Attributes are here:
    // --------------------------------------
    
    /**
     * Holds the state of the game (whether it is over or not).
     */
    Boolean gameOver = false;


    // --------------------------------------
    //          Methods start here:
    // --------------------------------------
    
    @Override
    public void run() {

        Player player1 = new Player( "TEST PLAYER 1" );

        Board leftBoard = new Board();
        Board rightBoard = new Board();

        GameFrame frame = new GameFrame();
        GamePanel gamePanel = new GamePanel(leftBoard, rightBoard);

        frame.setActivePanel(gamePanel);

        //gamePanel.placeShip(Ship.shipType.CARRIER, 0, this);
        runSetupPhase( player1, gamePanel );



        // INTRO SCREEN:

        // Introduction / setup phase:

        // While the game is not over 
        
        /* while ( gameOver == false ) {
            
            // Setup phase:

        } */

        // Turns phase

        // Ending screen
    }


    @Override
    public void onShipPlaced() {
        System.out.println("Ship placed");
    }


    /** 
     * runSetupPhase() requires both players to place all five of their ships on their board.
     */
    public void runSetupPhase( Player currentPlayer, GamePanel gamePanel ) {

        // determines when a player is finished palcing ships.
        Boolean allShipsPlaced = false;

        // Stores all the ship types for our player to pull ships from.
        ArrayList<Ship.shipType> ships = new ArrayList<>();
        ships.add( Ship.shipType.CARRIER );

        // Tell the player it's their turn to set up ships!
        /*
         * I imagine we will need to add some sort of text display between the boards that tells the players when it's their turn,
         * how to rotate ship and place them, and other information.
         */
        
        // Place SHIPS!
        for ( Ship.shipType shipToPlace : ships ) {

            gamePanel.placeShip( shipToPlace, 0, this);
        }

    }
}
