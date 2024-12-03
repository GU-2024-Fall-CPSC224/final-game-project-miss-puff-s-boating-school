package edu.gonzaga.renderer;

import edu.gonzaga.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * The JPanel that is displayed when the settings button is clicked. Contains settings for the game.
 */
public class SettingsPanel extends JPanel {
    private JCheckBox hideShipsToggle;
    private JTextField roundLimitField;
    private JButton exitButton;

    /**
     * settingsPanel() is the constructor for the settings panel window.
     */
    public SettingsPanel(GameUICallbacks callbacks) {
        super(new BorderLayout());

        setBackground(Palette.BLACK);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        container.add(Box.createRigidArea(new Dimension(0, 30)));
        addHideShips(container);
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        addRoundLimit(container);
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        addExit(container);

        exitButton.addActionListener(e -> {
            updateSettings();
            callbacks.settingsPanelOnClose();
        });

        add(container, BorderLayout.NORTH);
    }

    private void addHideShips(JPanel outer) {
        JPanel inner = new JPanel();
        inner.setOpaque(false);

        JLabel label = new JLabel("Hide your ships from your opponent");
        label.setForeground(Palette.WHITE);
        label.setFont(Palette.getPrimaryFont(32));
        inner.add(label);

        hideShipsToggle = new JCheckBox();
        hideShipsToggle.setSelected(Settings.getInstance().hideShipsOnBoard);
        inner.add(hideShipsToggle);

        outer.add(inner);
    }

    private void addRoundLimit(JPanel outer) {
        JPanel inner = new JPanel();
        inner.setOpaque(false);

        JLabel label = new JLabel("Round limit");
        label.setForeground(Palette.WHITE);
        label.setFont(Palette.getPrimaryFont(32));
        inner.add(label);

        roundLimitField = new JTextField(String.valueOf(Settings.getInstance().turnLimit), 3);

        inner.add(roundLimitField);

        outer.add(inner);
    }

    private void addExit(JPanel outer) {
        exitButton = new JButton("Save and Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFont(Palette.getPrimaryFont(32));
        exitButton.setBackground(Palette.CLEAR);
        exitButton.setForeground(Palette.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Palette.WHITE, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                )
        );

        outer.add(exitButton);
    }

    /**
     * Updates settings based on the fields set in the panel.
     */
    private void updateSettings() {
        Settings currentSettings = Settings.getInstance();
        
        // Hide ships
        currentSettings.hideShipsOnBoard = this.hideShipsToggle.isSelected();
        System.out.println("Ship visibility: " + currentSettings.hideShipsOnBoard);

        // Round / Turn limit
        try {
            int roundLimit = Integer.parseInt(this.roundLimitField.getText().trim());

            currentSettings.turnLimit = roundLimit;
            System.out.println("Round limit: " + roundLimit);
        } catch (Exception e) {
            System.out.println("Unable to set round limit: " + e);
        }
    }
}