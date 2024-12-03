package edu.gonzaga.renderer;

/**
 * Callbacks used to communicate between the Game and its panels.
 */
public interface GameUICallbacks {
    /**
     * The user has started the game.
     * @param player1Name The name of player 1.
     * @param player2Name The name of player 2.
     */
    void introPanelOnStartGame(String player1Name, String player2Name);

    /**
     * The user has gone to the settings panel.
     */
    void introPanelOnSettings();

    /**
     * The user has closed the settings panel.
     */
    void settingsPanelOnClose();

    /**
     * The screen is being handed off to the next player.
     */
    void onHandoffStarted();

    /**
     * The next player has the screen and is ready to play.
     */
    void onPlayerSwitched();

    /**
     * The user has placed a ship on the board.
     */
    void onShipPlaced();

    /**
     * The user has taken an action on the board.
     */
    void onActionTaken();

    /**
     * The user wants to go back to the main menu.
     */
    void endPanelOnMainMenu();
}
