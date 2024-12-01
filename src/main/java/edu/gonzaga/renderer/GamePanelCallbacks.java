package edu.gonzaga.renderer;

/**
 * Callbacks used by GamePanel.
 */
public interface GamePanelCallbacks {
    /**
     * The user has placed a ship on the board.
     */
    void onShipPlaced();

    /**
     * The user has taken an action on the board.
     */
    void onActionTaken();
}
