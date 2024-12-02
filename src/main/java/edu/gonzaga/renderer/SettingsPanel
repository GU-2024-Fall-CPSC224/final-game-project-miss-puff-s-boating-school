package edu.gonzaga.renderer;

import edu.gonzaga.Player;

import javax.swing.*;
import java.awt.*;

/*
 * The Settings Panel provides the players with an additional userface where they can adjust various aspects of gameplay,
 * including ship visibility, turn timers, special abilities, and volume.
 */
public class SettingsPanel extends JPanel {
    public SettingsPanel( SettingsPanelCallbacks callbacks ){
        super( new BoxLayout() ) {

            // Add Button 1:
            JButton exitButton = new JButton();
            exitButton.setText( "Exit Settings" );
            this.add( exitButton );

            // Add Button 2:
            JButton visibilityButton = new JButton();
            visibilityButton.setText( "Toggle hidden ships" );
            this.add( visibilityButton );

            // Add Button 3:
            JButton turnTimer = new JButton();
            turnTimer.setText( "Toggle turn timer" );
            this.add( turnTimer );

        }  
    }
}