package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;

public class Info extends JPanel {
    
    JLabel gameTitle = new JLabel();
    JLabel statusDisplay = new JLabel();
    JButton settingsButton = new JButton();
    
    /**
     * Constructor for painting?
     */
    public Info() {
        super();

        

        // Setting the info Panel to have a box layout ( tile, textscreen, settings button, etc.)
        this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        setBackground(Color.BLACK);
        addGameTitle();
        addStatusDisplayScreen();
        addSettingsButton();
        
    }

    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.PLAIN, 42);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);

        int x = (getWidth() - metrics.stringWidth("Battleship")) / 2;

        g.drawString("Battleship", x, 40);

        font = new Font("Arial", Font.PLAIN, 32);
        g.setFont(font);
        metrics = g.getFontMetrics(font);

        x = (getWidth() - metrics.stringWidth("_____'s Turn")) / 2;

        g.drawString("_____'s Turn", x, 100);
    }
    */


    /**
     * addGameTitle() Adds the BATTLESHIP (C) title to the top of the main game panel.
     */
    public void addGameTitle() {
        // Set object alignment inside the panel to the center of the panel.
        this.gameTitle.setAlignmentX( 0.5f );
        Font font = new Font("Arial", Font.PLAIN, 42); // Set font size and style
        this.gameTitle.setForeground( Color.WHITE ); // Set font color.
        this.gameTitle.setFont( font ); // add the customized font to the label.
        this.gameTitle.setText( "BATTLESHIP" ); // Set label text.
        this.add( gameTitle );
    }


    /**
     * addStatusDisplayScreen() adds the "display screen", which will tell both players when to setup,
     * who's turn it is, etc.
     */
    public void addStatusDisplayScreen() {
        // Set object alignment inside the panel to the center of the panel.
        this.statusDisplay.setAlignmentX( 0.5f );
        Font font = new Font("Arial", Font.PLAIN, 32); // Set font size and style
        this.statusDisplay.setForeground( Color.WHITE ); // Set font color.
        this.statusDisplay.setFont( font ); // add the customized font to the label.
        this.statusDisplay.setText( "This is the text display field." ); // Set label text.
        this.add( statusDisplay );
    }


    /**
     * addSettingsButton() adds a settings button which can be accessed to change various settings.
     */
    public void addSettingsButton() {
        // Set object alignment inside the panel to the center of the panel.
        this.settingsButton.setAlignmentX( 0.5f );
        // Set object alignment to place the settings button at the bottom of the screen.
        this.settingsButton.setAlignmentY(BOTTOM_ALIGNMENT);

        Font font = new Font("Arial", Font.PLAIN, 32); // Set font size and style
        this.settingsButton.setForeground( Color.BLACK ); // Set font color.
        this.settingsButton.setFont( font ); // add the customized font to the label.
        this.settingsButton.setText( "Settings" ); // Set label text.
        this.add( settingsButton );
    }
}
