package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the ships the player has using a scrollable grid.
 */
public class Ships extends JScrollPane {
    edu.gonzaga.Board board;
    JPanel container;

    public Ships(edu.gonzaga.Board board) {
        super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);

        this.board = board;

        container = new JPanel();
        container.setLayout(new GridLayout(0, 1, 0, 10));
        container.setBackground(Color.BLACK);

        for (edu.gonzaga.Ship ship : board.ships) {
            ShipCard card = new ShipCard(ship);
            container.add(card);
        }

        container.setPreferredSize(new Dimension(0, board.ships.size() * 70 + (board.ships.size() - 1) * 10));

        setViewportView(container);
    }
}
