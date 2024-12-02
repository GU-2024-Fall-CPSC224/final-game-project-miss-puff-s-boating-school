package edu.gonzaga.renderer;

/**
 * Callbacks used by EndPanel.
 */
public interface EndPanelCallbacks {
    /**
     * The user wants to restart the game with the same players.
     */
    void endPanelOnRestart();

    /**
     * The user wants to go back to the main menu.
     */
    void endPanelOnMainMenu();
}
