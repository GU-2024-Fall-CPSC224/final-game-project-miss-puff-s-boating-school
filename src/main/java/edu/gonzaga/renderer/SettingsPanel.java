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
    private JTextField visibilityLabel;
    private JCheckBox visibilityToggle;
    
    /**
     * settingsPanel() is the constructor for the settings panel window.
     */
    public SettingsPanel( SettingsPanelCallbacks callbacks ) {
            
        super();
        setLayout( new BoxLayout( this, BoxLayout.Y_AXIS) );
            
        // Add Exit settings button:
        JButton exitButton = new JButton();
        exitButton.setText( "Exit Settings" );
        this.add( exitButton );
        // Add action listener to the exit button.
            exitButton.addActionListener( e -> {
                updateSettings();
                callbacks.previousPanelOnCLoseSettings();
            });
        
        // Add Ship Visibility setting to panel:
        shipVisibilitySetting = new JPanel();
        addShipVisibilitySetting();

        // Add Button 3:
        JCheckBox turnTimer = new JCheckBox();
        turnTimer.setText( "Toggle turn timer" );
        this.add( turnTimer );
        
    }

    /*
     * addShipVisibilitySetting() creates a new Jpanel, which stores the settings toggle for
     * ship visibility to all players.
     */
    private void addShipVisibilitySetting() {
        // Add Ship visibility lable:
        visibilityLabel = new JTextField( "Ships visible to both players" );
        shipVisibilitySetting.add( visibilityLabel );
        // Add ship visibility checkbox:
        visibilityToggle = new JCheckBox();
        // Determine if hideShips is toggled on or off.
        visibilityToggle.setSelected( Settings.getInstance().hideShipsOnBoard );
        //visibilityToggle.setText( "Toggle hidden ships" );
        shipVisibilitySetting.add( visibilityToggle );
    }

    /*
     * updateSettings() runs through all the toggled settings in the settings panel, updating any chages detected.
     */
    public void updateSettings() {
        // Get an instance of the settings.
        Settings currentSettings = Settings.getInstance();

        //Check for updated ship visibility:
        currentSettings.hideShipsOnBoard = this.visibilityToggle.isSelected();
        System.out.println( "Ship visibility: " + currentSettings.hideShipsOnBoard );
        
       

    }

}