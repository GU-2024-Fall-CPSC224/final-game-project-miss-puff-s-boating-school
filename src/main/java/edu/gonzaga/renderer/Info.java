package edu.gonzaga.renderer;

import javax.swing.*;

import edu.gonzaga.Game;
import edu.gonzaga.Player;

import java.awt.*;

public class Info extends JPanel {
    /*
     * Holds the player names
     */
    Player player1;
    Player player2;

    JLabel gameTitle;

    JPanel centerContainer;
    JButton switchButton;
    JLabel statusDisplayTop;
    JLabel statusDisplayBottom;

    private GameUICallbacks callbacks;
    /**
     * Is the screen being handed off to the other player?
     */
    private boolean isInSwitch = false;

    /**
     * Constructor for painting?
     */
    public Info(GameUICallbacks callbacks, Player player1, Player player2) {
        super(new BorderLayout());

        setBackground(Color.BLACK);

        this.callbacks = callbacks;
        this.player1 = player1;
        this.player2 = player2;

        addGameTitle();
        addCenter();
    }

    public void setGameState(Game.GameState state) {
        switch (state) {
            case PLAYER_1_SETUP -> setStatusText(player1.getName(), "Set up your ships.");
            case PLAYER_2_SETUP -> setStatusText(player2.getName(), "Set up your ships.");
            case PLAYER_1_TURN -> setStatusText(player1.getName(), "It's your turn.");
            case PLAYER_2_TURN -> setStatusText(player2.getName(), "It's your turn.");
            default -> {
            }
        }
    }

    public void turnComplete() {
        switchButton.setEnabled(true);
        switchButton.setText("End turn");
        setStatusText("Turn complete", "");
    }

    private void onSwitchButtonPressed() {
        if (isInSwitch) {
            switchButton.setEnabled(false);
            switchButton.setText("Waiting...");
            callbacks.onPlayerSwitched();
        } else {
            switchButton.setText("Press to start turn");
            setStatusText("Hand off to", "other player");
            callbacks.onHandoffStarted();
        }

        isInSwitch = !isInSwitch;
    }

    /**
     * addGameTitle() Adds the BATTLESHIP (C) title to the top of the main game panel.
     */
    private void addGameTitle() {
        gameTitle = new JLabel();

        gameTitle.setText("BATTLESHIP");
        gameTitle.setFont(Palette.getTitleFont(48));
        gameTitle.setForeground(Palette.WHITE);

        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);

        add(gameTitle, BorderLayout.NORTH);
    }

    private void addCenter() {
        centerContainer = new JPanel();
        centerContainer.setOpaque(false);
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));

        addSwitchButton();
        addStatusDisplay();

        add(centerContainer, BorderLayout.CENTER);
    }

    private void addSwitchButton() {
        switchButton = new JButton("Button");
        switchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchButton.setEnabled(false);
        switchButton.setText("Waiting...");
        switchButton.addActionListener(e -> onSwitchButtonPressed());

        switchButton.setFont(Palette.getPrimaryFont(36));
        switchButton.setBackground(Palette.CLEAR);
        switchButton.setForeground(Palette.WHITE);
        switchButton.setFocusPainted(false);
        switchButton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Palette.WHITE, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                )
        );

        centerContainer.add(Box.createRigidArea(new Dimension(0, 60)));
        centerContainer.add(switchButton);
    }

    /**
     * addStatusDisplayScreen() adds the "display screen", which will tell both players when to setup,
     * who's turn it is, etc.
     */
    private void addStatusDisplay() {
        statusDisplayTop = new JLabel();
        statusDisplayBottom = new JLabel();

        statusDisplayTop.setFont(Palette.getPrimaryFont(36));
        statusDisplayTop.setForeground(Color.WHITE);
        statusDisplayTop.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContainer.add(Box.createRigidArea(new Dimension(0, 60)));
        centerContainer.add(statusDisplayTop);

        statusDisplayBottom.setFont(Palette.getPrimaryFont(36));
        statusDisplayBottom.setForeground(Color.WHITE);
        statusDisplayBottom.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContainer.add(statusDisplayBottom);
    }

    private void setStatusText(String top, String bottom) {
        statusDisplayTop.setText(top);
        statusDisplayBottom.setText(bottom);
    }
}
