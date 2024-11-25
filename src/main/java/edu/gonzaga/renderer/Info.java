package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;

public class Info extends JPanel {
    
    /**
     * Constructor for painting?
     */
    public Info() {
        super();

        // Setting the info Panel to have a box layout ( tile, textscreen, settings button, etc.)
        this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        setBackground(Color.BLACK);
        addGameTitle();
        
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

    public void addGameTitle() {
        JLabel newLabel = new JLabel();
        // Set object alignment inside the panel to the center of the panel.
        newLabel.setAlignmentX( 0.5f );
        Font font = new Font("Arial", Font.PLAIN, 42); // Set font size and style
        newLabel.setForeground( Color.WHITE ); // Set font color.
        newLabel.setFont( font ); // add the customized font to the label.
        newLabel.setText( "BATTLESHIP" ); // Set label text.
        this.add( newLabel );
    }
    
}
