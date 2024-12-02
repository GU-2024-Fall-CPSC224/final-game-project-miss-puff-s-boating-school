package edu.gonzaga;

import edu.gonzaga.renderer.*;
import edu.gonzaga.ships.Ship;

/**
 * The main class that runs the game, handling state and transitions between panels.
 */
public class Game implements Runnable, IntroPanelCallbacks, GamePanelCallbacks, EndPanelCallbacks, SettingsPanelCallbacks {
    /**
     * The current state of the game.
     */
    GameState currentGameState;

    /**
     * The number of ships that have been placed in the current setup phase.
     */
    Integer shipsPlaced = 0;

    /**
     * Defines the possible states of the game.
     */
    public enum GameState {
        /**
         * Player 1 is taking their turn.
         */
        PLAYER_1_TURN,
        /**
         * Player 1 is setting up their ships.
         */
        PLAYER_1_SETUP,
        /**
         * Player 2 is taking their turn.
         */
        PLAYER_2_TURN,
        /**
         * Player 2 is setting up their ships.
         */
        PLAYER_2_SETUP,
        /**
         * The game has ended.
         */
        GAME_OVER
    }

    Player player1;
    Player player2;

    Board leftBoard;
    Board rightBoard;

    GameFrame frame = new GameFrame();
    GamePanel gamePanel;

    @Override
    public void run() {
        System.out.println("Running game...");

        System.out.println("Going to intro panel...");
        frame.setActivePanel(new IntroPanel(this));
    }

    @Override
    public void introPanelOnStartGame(String player1Name, String player2Name) {
        System.out.println("Starting game with players: " + player1Name + " and " + player2Name);

        player1 = new Player(player1Name);
        player2 = new Player(player2Name);

        leftBoard = new Board();
        rightBoard = new Board();

        gamePanel = new GamePanel(this, leftBoard, rightBoard, player1, player2);

        System.out.println("Going to game panel...");
        setGameState(Game.GameState.PLAYER_1_SETUP);
        frame.setActivePanel(gamePanel);

        gamePanel.placeShip(Ship.ShipType.values()[shipsPlaced]);
    }

    @Override
    public void introPanelOnSettings() {
        System.out.println("Going to settings panel...");
        frame.setActivePanel(new SettingsPanel(this));
    }

    @Override
    public void onHandoffStarted() {
        gamePanel.onHandoffStarted();
    }

    @Override
    public void onPlayerSwitched() {
        switch (currentGameState) {
            case PLAYER_1_SETUP -> {
                setGameState(GameState.PLAYER_2_SETUP);
                gamePanel.placeShip(Ship.ShipType.values()[shipsPlaced]);
            }
            case PLAYER_2_SETUP, PLAYER_2_TURN -> {
                setGameState(GameState.PLAYER_1_TURN);
                gamePanel.takeAction();
            }
            case PLAYER_1_TURN -> {
                setGameState(GameState.PLAYER_2_TURN);
                gamePanel.takeAction();
            }
            default -> {}
        }
    }

    @Override
    public void onShipPlaced() {
        shipsPlaced++;

        // If less than 5 ships have been placed, call the placeShip method again
        if (shipsPlaced < 5) {
            gamePanel.placeShip(Ship.ShipType.values()[shipsPlaced]);
            return;
        }

        // Otherwise, this player has placed all their ships
        shipsPlaced = 0;

        System.out.println("All ships placed.");

        // We either have to switch to the next player's setup phase, or to the turns phase
        if ((currentGameState == GameState.PLAYER_2_SETUP)) {
            gamePanel.turnComplete();

//            setGameState(GameState.PLAYER_1_TURN);
//
//            gamePanel.takeAction();
        }
        if (currentGameState == GameState.PLAYER_1_SETUP) {
            gamePanel.turnComplete();
//            setGameState(GameState.PLAYER_2_SETUP);
//
//            gamePanel.placeShip(Ship.ShipType.values()[shipsPlaced]);
        }
    }

    @Override
    public void onActionTaken() {
        if (leftBoard.checkAllShipsSunk()) {
            System.out.println("Player 1 has no ships left. Moving to end screen.");
            frame.setActivePanel(new EndPanel(this, player2));
            return;
        } else if (rightBoard.checkAllShipsSunk()) {
            System.out.println("Player 2 has no ships left. Moving to end screen.");
            frame.setActivePanel(new EndPanel(this, player1));
            return;
        }

        gamePanel.turnComplete();

        // Check whose turn it is, and swap the game state to the other player, switching the turn
        if (currentGameState == Game.GameState.PLAYER_1_TURN) {

//            setGameState(Game.GameState.PLAYER_2_TURN);
//            gamePanel.takeAction();
        } else {
//            setGameState(Game.GameState.PLAYER_1_TURN);
//            gamePanel.takeAction();
        }
    }

    @Override
    public void endPanelOnRestart() {
        System.out.println("Restarting game...");
    }

    @Override
    public void endPanelOnMainMenu() {
        System.out.println("Returning to main menu...");
    }

    /**
     * Sets the current game state and updates the game panel.
     *
     * @param newState The new game state.
     */
    private void setGameState(Game.GameState newState) {
        System.out.println("Game state changed to: " + newState);
        currentGameState = newState;
        gamePanel.setGameState(newState);
    }

    @Override
    public void previousPanelOnCLoseSettings() {
        System.out.println("Returning to intro panel...");

        frame.setActivePanel(new IntroPanel(this));
    }
}
