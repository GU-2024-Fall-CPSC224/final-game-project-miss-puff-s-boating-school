package edu.gonzaga.renderer;

import javax.swing.*;

/**
 * A card that displays a ship and its information.
 */
public class ShipCard extends JPanel {
    /** The ship to display. */
    private edu.gonzaga.Ship ship;

    public ShipCard(edu.gonzaga.Ship ship) {
        super();

        this.ship = ship;

        setMinimumSize(new java.awt.Dimension(0, 70));

        JLabel nameLabel = new JLabel("Ship " + ship.getLength());
        add(nameLabel);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            }
        });
    }
}
