package edu.gonzaga.renderer;

import javax.swing.*;

import edu.gonzaga.Game;
import edu.gonzaga.Player;
import edu.gonzaga.Game.GameState;

import java.awt.*;

public class Info extends JPanel {
    
    /*
     * Holds the player names
     */
    Player player1;
    Player player2;

    JLabel gameTitle = new JLabel();
    JLabel statusDisplay = new JLabel();
    JButton settingsButton = new JButton();
    
    /**
     * Constructor for painting?
     */
    public Info( Player player1, Player player2 ) {
        super();

        this.player1 = player1;
        this.player2 = player2;

        // add(westPanel, BorderLayout.WEST); < Reference code.

        // Setting the info Panel to have a box layout ( tile, textscreen, settings button, etc.)
        this.setLayout( new BorderLayout() );
        this.setAlignmentX( 0.5f );

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
        this.add( gameTitle, BorderLayout.NORTH );
        
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
        this.add( statusDisplay, BorderLayout.CENTER );
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
        this.add( settingsButton, BorderLayout.SOUTH );
    }


    /**
     * displayPlayerHit() updates the statusDisplayScreen to whether the current player's most
     * recent space check was a hit or a miss.
     */
    public void displayPlayerHit( Boolean isHit ) {
        // If the space check was a hit, display:
        if ( isHit == true ) {
            statusDisplay.setText( "< HIT >" );
            return;
        }
        // Otherwise, display:
        statusDisplay.setText( "< MISS >");
    }


    /**
     * displayPlayerHit() updates the statusDisplayScreen to whether the current player's most
     * recent space check was a hit or a miss.
     */
    public void displayPlayerTurn( Game.GameState gameState, Boolean isHit ) {
        
        String checkText = "";
        
        // Display hit/miss status:
        if ( isHit == true ) {
            statusDisplay.setText( "HIT." );
        }
        else {
            statusDisplay.setText( "MISS." );
        }
        checkText = statusDisplay.getText(); 
        // Determine player turn display:
        if ( gameState == Game.GameState.PLAYER_2_TURN ) {
            statusDisplay.setText( "<html><head><style>p{text-align: center;}<p>" + checkText + "<br/><br/> IT IS NOW <br/>" 
                                    + player1.getName() + "'S<br/> TURN.</p></html>" );
            return;
        }
        // Otherwise, display:
        if ( gameState == Game.GameState.PLAYER_1_TURN ) {
            statusDisplay.setText( "<html><head><style>p{text-align: center;}<p>" + checkText + "<br/><br/> IT IS NOW <br/>" 
                                    + player2.getName() + "'S<br/> TURN.</p></html>" );
            return;
        }
    }


    /**
     * displayPlayerSetup() updats the text display screen with who's turn it is to setup ships.
     */
    public void displayPlayerSetup( Game.GameState gameState ) {
        // Check for player 2's setup.
        if ( gameState == Game.GameState.PLAYER_2_SETUP ) {
            // Otherwise, display:
            statusDisplay.setText( "<html><head><style>p{text-align: center;}<p>IT IS NOW<br/>" + player2.getName() +
                                    "'S<br/> TURN. SET UP BATTLESHIPS.</p></html>" );
        }
        if ( gameState == Game.GameState.PLAYER_1_SETUP ) {
            statusDisplay.setText( "<html><head><style>p{text-align: center;}<p>IT IS NOW<br/>" + player1.getName() +
                                    "'S<br/> TURN. SET UP BATTLESHIPS.</p></html>" );
        }
    }
}
