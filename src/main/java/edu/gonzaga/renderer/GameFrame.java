package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents the window in which the game is displayed.
 */
public class GameFrame extends JFrame {
    /** Default width of the game window. */
    public static final int DEFAULT_WIDTH = 1920;
    /** Default height of the game window. */
    public static final int DEFAULT_HEIGHT = 1080;

    /**
     * The main panel that the game is showing.
     */
    private JPanel activePanel;

    public GameFrame() {
        super("Battleship");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        activePanel = null;
    }

    /**
     * Updates the active panel of the game, removing the old one.
     * The first time this is called, the window will be made visible.
     *
     * @param panel The new panel to be used.
     */
    public void setActivePanel(JPanel panel) {
        // The panel will be null the first time
        if (activePanel == null) {
            activePanel = panel;
            add(activePanel, BorderLayout.CENTER);

            setVisible(true);
        } else {
            remove(activePanel);
            activePanel = panel;
            add(activePanel, BorderLayout.CENTER);
        }

    }
}
