package edu.gonzaga.renderer;

/**
 * Callbacks used by IntroPanel.
 */
public interface IntroPanelCallbacks {
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
}
