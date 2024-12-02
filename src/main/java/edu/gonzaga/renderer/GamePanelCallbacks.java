package edu.gonzaga.renderer;

/**
 * Callbacks used by GamePanel.
 */
public interface GamePanelCallbacks {
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
}
