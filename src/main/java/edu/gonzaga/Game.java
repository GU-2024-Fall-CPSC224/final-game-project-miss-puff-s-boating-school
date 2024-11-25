package edu.gonzaga;

import java.util.ArrayList;
import java.util.Arrays;

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

    Integer shipsPlaced = 0;

    /**
     * Tracks the current state of the game, which can be:
     * 1. SETUP: Placing ships
     * 2. PLAYER_1_TURN: Player 1's turn
     * 3. PLAYER_2_TURN: Player 2's turn
     * 4. GAMEOVER: The game has finished
     */
    Game.GameState currentGameState;
    public enum GameState {
        PLAYER_1_TURN,
        PLAYER_1_SETUP,
        PLAYER_2_TURN,
        PLAYER_2_SETUP,
        GAME_OVER
    }


    Player player1 = new Player( "TEST PLAYER 1" );

    Board leftBoard = new Board();
    Board rightBoard = new Board();

    GameFrame frame = new GameFrame();
    GamePanel gamePanel = new GamePanel(leftBoard, rightBoard);


    // --------------------------------------
    //          Methods start here:
    // --------------------------------------
    
    @Override
    public void run() {

        

        frame.setActivePanel(gamePanel);

        //gamePanel.placeShip(Ship.shipType.CARRIER, 0, this);
        
        runSetupPhase( player1, gamePanel );



        // INTRO SCREEN:

        // Introduction / setup phase:

        // While the game is not over 
        
        */ // < ------ RUnning this seems to cause the game to crash / infinite white screen of death?

        // Turns phase

        // Ending screen
    }


    @Override
    public void onShipPlaced() {
        shipsPlaced++;
        // If finished, return from this method.
        if ( shipsPlaced >= 5 ) {
            return;
        }
        // If fewer than 5 ships have been placed, simply call the placeship method again with the next ship ENUM.
        gamePanel.placeShip( Ship.ShipType.values()[ shipsPlaced ], this);
    }


    /** 
     * runSetupPhase() requires both players to place all five of their ships on their board.
     */
    public void runSetupPhase( Player currentPlayer, GamePanel gamePanel ) {

        changeGameState( Game.GameState.PLAYER_1_SETUP );
        // Tell the player it's their turn to set up ships!
        /*
         * I imagine we will need to add some sort of text display between the boards that tells the players when it's their turn,
         * how to rotate ship and place them, and other information. I notice that currently the text for the game uses a paint
         * component to do this? How do I access this?
         */
        
        // values returns list of all ship types
        // this states that on completion, callback to here.
        gamePanel.placeShip( Ship.ShipType.values()[ shipsPlaced ], this);
        // Place SHIPS!
        
    }


    /**
     * changeGameState() changes the state of the game.
     */
    public void changeGameState( Game.GameState newState ) {
        currentGameState = newState;
    }
}
