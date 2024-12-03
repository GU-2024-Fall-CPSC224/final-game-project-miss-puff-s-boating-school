package edu.gonzaga.renderer;

import edu.gonzaga.ships.Ship;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Displays the ships the player has using a scrollable grid.
 */
public class Ships extends JScrollPane implements PropertyChangeListener {
    edu.gonzaga.Board board;
    JPanel container;

    public Ships(edu.gonzaga.Board board) {
        super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);

        this.board = board;

        container = new JPanel();
        container.setLayout(new GridLayout(0, 1));
        container.setBackground(Color.BLACK);

        setViewportView(container);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateShips();
        revalidate();
    }

    private void updateShips() {
        container.removeAll();

        for (Ship ship : board.getShips()) {
            container.add(new ShipCard(ship));
        }

        int numShips = board.getShips().length;

        container.setPreferredSize(new Dimension(0, numShips * ShipCard.HEIGHT));
    }
}
