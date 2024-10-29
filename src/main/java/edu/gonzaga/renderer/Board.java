package edu.gonzaga.renderer;

import edu.gonzaga.Ship;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private edu.gonzaga.Board board;
    private int gridCellSize;
    private int boardSize;

    public Board(edu.gonzaga.Board board) {
        super();

        this.board = board;
        setLayout(new GridLayout(11, 11));
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        gridCellSize = getWidth() / 11;
        boardSize = gridCellSize * 11;

        for (Ship ship : board.ships) {
            drawShip(g, ship.getX() + 1, ship.getY() + 1, ship.isVertical(), ship.getLength());
        }

        drawGrid(g);

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (board.hits[x][y]) {
                    drawMarker(g, x + 1, y + 1, true);
                }
            }
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.PLAIN, 32);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);

        for (int i = 1; i < 11; i++) {
            g.drawLine(0, i * gridCellSize, boardSize, i * gridCellSize);
            g.drawLine(i * gridCellSize, 0, i * gridCellSize, boardSize);

            String letterString = String.valueOf((char) (i + 64));
            int x = i * gridCellSize + (gridCellSize - metrics.stringWidth(letterString)) / 2;
            int y = (gridCellSize - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(letterString, x, y);

            String numberString = String.valueOf(i);
            x = (gridCellSize - metrics.stringWidth(numberString)) / 2;
            y = i * gridCellSize + (gridCellSize - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(numberString, x, y);
        }
    }

    private void drawShip(Graphics g, int x, int y, boolean vertical, int length) {
        g.setColor(Color.GRAY);

        if (vertical) {
            g.fillRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize * length);
        } else {
            g.fillRect(x * gridCellSize, y * gridCellSize, gridCellSize * length, gridCellSize);
        }
    }

    private void drawMarker(Graphics g, int x, int y, boolean hit) {
        if (hit) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.WHITE);
        }

        int markerSize = Math.round(0.75f * gridCellSize);

        g.fillOval(x * gridCellSize + 10, y * gridCellSize + 10, markerSize, markerSize);
    }
}
