package edu.gonzaga.renderer;

import edu.gonzaga.ships.Ship;

import javax.swing.*;

/**
 * A card that displays a ship and its information.
 */
public class ShipCard extends JPanel {
    /** The ship to display. */
    private Ship ship;

    public ShipCard(Ship ship) {
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
