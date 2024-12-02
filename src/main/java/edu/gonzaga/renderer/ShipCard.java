package edu.gonzaga.renderer;

import edu.gonzaga.ships.Ship;

import javax.swing.*;
import java.awt.*;

/**
 * A card that displays a ship and its information.
 */
public class ShipCard extends JPanel {
    /** The ship to display. */
    private final Ship ship;

    public ShipCard(Ship ship) {
        super();

        this.ship = ship;

        if (ship.getIsSunk()) {
            setBackground(Color.RED);
        } else {
            setBackground(Color.GREEN);
        }

        setMinimumSize(new java.awt.Dimension(0, 70));

        JLabel nameLabel = new JLabel(ship.toString());
        add(nameLabel);
    }
}
