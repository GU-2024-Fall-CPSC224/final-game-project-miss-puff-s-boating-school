package edu.gonzaga;

import java.util.ArrayList;

import edu.gonzaga.renderer.GameFrame;
import edu.gonzaga.renderer.GamePanel;
import edu.gonzaga.renderer.PlaceShipCallback;
import edu.gonzaga.ships.Ship;

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
        
        /* 
        while ( gameOver == false ) {
            
            // Setup phase:

        }
        */ // < ------ RUnning this seems to cause the game to crash / infinite white screen of death?

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
        ArrayList<Ship.ShipType> ships = new ArrayList<>();
        ships.add( Ship.ShipType.CARRIER );
        ships.add( Ship.ShipType.CARRIER );
        // All the needed ships would be listed here?

        // Tell the player it's their turn to set up ships!
        /*
         * I imagine we will need to add some sort of text display between the boards that tells the players when it's their turn,
         * how to rotate ship and place them, and other information. I notice that currently the text for the game uses a paint
         * component to do this? How do I access this?
         */
        
        // Place SHIPS!
        for ( Ship.ShipType shipToPlace : ships ) {

            gamePanel.placeShip( shipToPlace, this); // It appears that calling this function does not pause the loop.
            System.out.println( "Moving on to next ship..."); // I mean that all the ship type get passed before you even place one.
        }

    }
}
