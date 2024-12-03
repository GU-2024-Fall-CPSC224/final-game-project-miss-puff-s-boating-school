package edu.gonzaga.renderer;

import edu.gonzaga.Player;
import edu.gonzaga.Settings;

import javax.swing.*;
import java.awt.*;

/*
 * The Settings Panel provides the players with an additional userface where they can adjust various aspects of gameplay,
 * including ship visibility, turn timers, special abilities, and volume.
 */
public class SettingsPanel extends JPanel {
    private JPanel shipVisibilitySetting;
    private JCheckBox visibilityToggle;

    private JPanel roundCountSetting;
    private JLabel roundCountLabel;
    private JTextField roundCountTextField;

    /**
     * settingsPanel() is the constructor for the settings panel window.
     */
    public SettingsPanel(GameUICallbacks callbacks) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add Exit settings button:
        JButton exitButton = new JButton();
        exitButton.setText("Exit Settings");
        this.add(exitButton);

        // Add action listener to the exit button.
        exitButton.addActionListener(e -> {
            updateSettings();
            callbacks.settingsPanelOnClose();
        });

        // Add ship visibility setting.
        addShipVisibilitySetting();

        // Add maximum round count setting.
        addMaximumRoundCountSetting();
    }


    /*
     * addShipVisibilitySetting() creates a new Jpanel, which stores the settings toggle for
     * ship visibility to all players.
     */
    private void addShipVisibilitySetting() {
        shipVisibilitySetting = new JPanel();

        visibilityToggle = new JCheckBox("Hide Ships", Settings.getInstance().hideShipsOnBoard);
        shipVisibilitySetting.add(visibilityToggle);
        // Add the visibility setting panel to the main panel.
        add(shipVisibilitySetting);
    }


    /**
     * addMaximumTurnCountSetting() creates a text field with a changable number, which
     * determines how many rounds a game will last.
     */
    private void addMaximumRoundCountSetting() {
        roundCountSetting = new JPanel();

        roundCountLabel = new JLabel( "Max Round Count:" );
        roundCountSetting.add( roundCountLabel );

        roundCountTextField = new JTextField( Integer.toString( Settings.getInstance().turnLimit ) );
        roundCountSetting.add( roundCountTextField );

        // Add the maximum round cap to the settings panel.
        add(roundCountSetting);
    }

    /*
     * updateSettings() runs through all the toggled settings in the settings panel, updating any chages detected.
     */
    public void updateSettings() {
        // Get an instance of the settings.
        Settings currentSettings = Settings.getInstance();
        
        // Check if the ship visibility toggle has changed:
        currentSettings.hideShipsOnBoard = this.visibilityToggle.isSelected();
        System.out.println("Ship visibility: " + currentSettings.hideShipsOnBoard);

        // Check if the round count has been changed:
        currentSettings.turnLimit = Integer.valueOf( this.roundCountTextField.getText().trim() );
        System.out.println("Maximum turn count: " + roundCountTextField );

    }

}