package edu.gonzaga.renderer;

import edu.gonzaga.ships.Ship;

import javax.swing.*;
import java.awt.*;

/**
 * A card that displays a ship and its information.
 */
public class ShipCard extends JPanel {
    public static final int HEIGHT = 130;

    private final Ship ship;

    public ShipCard(Ship ship) {
        super();

        this.ship = ship;

        if (ship.getIsSunk()) {
            setBackground(Color.RED);
        } else {
            setBackground(new Color(11, 48, 18));
        }

        setMinimumSize(new java.awt.Dimension(0, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(ImageBuffers.getInstance().getShipSideImage(ship.getType()), 0, 0, null);
    }
}
