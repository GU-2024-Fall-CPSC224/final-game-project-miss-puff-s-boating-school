package edu.gonzaga;

/**
 * The settings for the game.
 */
public class Settings {
    private static final Settings instance = new Settings();

    /**
     * Whether to hide ships on the board from the opponent.
     */
    public boolean hideShipsOnBoard;

    /**
     * The number of turns before the game is forced to end.
     */
    public int turnLimit;

    private Settings() {
        hideShipsOnBoard = true;
        turnLimit = 50;
    }

    /**
     * Returns the instance of the settings.
     */
    public static Settings getInstance() {
        return instance;
    }
}
