package edu.gonzaga;

import java.util.ArrayList;
import java.util.Arrays;

import edu.gonzaga.renderer.GameFrame;
import edu.gonzaga.renderer.GamePanel;
import edu.gonzaga.renderer.PlaceShipCallback;
import edu.gonzaga.renderer.TakeActionCallback;
import edu.gonzaga.ships.Ship;

public class Game implements Runnable, PlaceShipCallback, TakeActionCallback {
    
    // --------------------------------------
    //         Attributes are here:
    // --------------------------------------

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
    Player player2 = new Player( "TEST PLAYER 2" );

    Board leftBoard = new Board();
    Board rightBoard = new Board();

    GameFrame frame = new GameFrame();
    GamePanel gamePanel = new GamePanel(leftBoard, rightBoard);


    // --------------------------------------
    //          Methods start here:
    // --------------------------------------
    
    @Override
    public void run() {
        System.out.println("Running game...");

        // INTRO SCREEN:

        // Introduction / setup phase:
        System.out.println("Going to game panel...");
        frame.setActivePanel(gamePanel);
        // Setup player 1 ships:
        changeGameState( Game.GameState.PLAYER_1_SETUP );
        runSetupPhase( gamePanel );

        // Turns phase

        

        // Ending screen
    }

    @Override
    public void onShipPlaced() {
        shipsPlaced++;

        // If finished, return from this method.
        if ( shipsPlaced >= 5 ) {
            shipsPlaced = 0;

            System.out.println("All ships placed.");

            // If both players have finished setting up, then move to the turns phase.
            if ( (currentGameState == Game.GameState.PLAYER_2_SETUP) ) {
                changeGameState( Game.GameState.PLAYER_1_TURN);
                runTurnsPhase( gamePanel );
            }
            // If the first player has completed their setup, switch the game state to the second player's setup.
            if ( currentGameState == Game.GameState.PLAYER_1_SETUP ) {
                changeGameState( Game.GameState.PLAYER_2_SETUP );
                runSetupPhase( gamePanel );
                // Set the shipsPlaced Integer to zero here as well.
            }

            return;
        }

        // If fewer than 5 ships have been placed, simply call the placeship method again with the next ship ENUM.
        gamePanel.placeShip( Ship.ShipType.values()[ shipsPlaced ], this);
    }


    /**
     * onActionTaken() runs during the turns phase where players are checking spaces. Each time a space is
     * checked, this functino will check who's turn it currently is, and set the turn phase to the other player,
     * before running another call to runTurnsPhase.
     */
    @Override
    public void onActionTaken() {

        // Check if the game is over.
        checkGameIsOver();

        // Check who's turn it is, and swap the game state to the other player, switching the turn.
        if ( currentGameState == Game.GameState.PLAYER_1_TURN ) {
            changeGameState( Game.GameState.PLAYER_2_TURN);
            runTurnsPhase( gamePanel );
        }
        else {
            changeGameState( Game.GameState.PLAYER_1_TURN);
            runTurnsPhase( gamePanel );
        }
    }


    /** 
     * runSetupPhase() requires both players to place all five of their ships on their board.
     */
    public void runSetupPhase(GamePanel gamePanel ) {



        /*
         * I imagine we will need to add some sort of text display between the boards that tells the players when it's their turn,
         * how to rotate ship and place them, and other information. I notice that currently the text for the game uses a paint
         * component to do this? How do I access this?
         */

        // Place SHIPS!
        gamePanel.placeShip( Ship.ShipType.values()[ shipsPlaced ], this);
    }


    /**
     * runTurnsPhase() flips between player turn states while the user's attempt to mark
     * their opponent's ships.
     */
    public void runTurnsPhase(GamePanel gamePanel ) {
        // Check a space on the the enemy board!
        gamePanel.takeAction( this );
    }
    
    
    /**
     * changeGameState() changes the state of the game.
     */
    public void changeGameState( Game.GameState newState ) {
        System.out.println("Game state changed to: " + newState);
        currentGameState = newState;
        gamePanel.setGameState( newState );
    }


    /**
     * checkGameIsOver() determines if all the ships on either board have been sunk. If true,
     * then the game switches to the ending screen, with the winning player displayed.
     */
    private void checkGameIsOver() {

        // Check if player 1 has no ships:
        if ( leftBoard.checkAllShipsSunk() == true ) {
            System.out.println( "Player 1 has no ships! Player 2 Wins!" );
            currentGameState = GameState.GAME_OVER;
        }
        // Check if player 2 has no ships:
        if ( rightBoard.checkAllShipsSunk() == true ) {
            System.out.println( "Player 2 has no ships! Player 1 Wins!" );
            currentGameState = GameState.GAME_OVER;
        }
    }
}
