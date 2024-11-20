package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private static final int TOTAL_CELLS = 11;

    private final edu.gonzaga.Board model;
    private int gridCellSize;
    private int boardSize;

    public Board(edu.gonzaga.Board model) {
        super();

        this.model = model;
        setLayout(new GridLayout(TOTAL_CELLS, TOTAL_CELLS));
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        gridCellSize = Math.min(getWidth() / TOTAL_CELLS, getHeight() / TOTAL_CELLS);
        boardSize = gridCellSize * TOTAL_CELLS;

        for (edu.gonzaga.Ship ship : model.getShips()) {
            drawShip(g, Color.GRAY, ship);
        }

        drawGrid(g);

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (model.isMarked(x, y)) {
                    drawMarker(g, x, y, model.isMarkerHit(x, y));
                }
            }
        }
    }

    public int getGridCellSize() {
        return gridCellSize;
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.WHITE);

        Font font = new Font("Arial", Font.PLAIN, 32);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);

        for (int i = 1; i < TOTAL_CELLS; i++) {
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

    private void drawShip(Graphics g, Color color, edu.gonzaga.Ship ship) {
        g.setColor(color);

        int x = ship.getX() + 1;
        int y = ship.getY() + 1;

        if (ship.isVertical()) {
            g.fillRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize * ship.getLength());
        } else {
            g.fillRect(x * gridCellSize, y * gridCellSize, gridCellSize * ship.getLength(), gridCellSize);
        }
    }

    private void drawMarker(Graphics g, int x, int y, boolean hit) {
        if (hit) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.WHITE);
        }

        int markerSize = Math.round(0.75f * gridCellSize);

        g.fillOval((x + 1) * gridCellSize + 10, (y + 1) * gridCellSize + 10, markerSize, markerSize);
    }

//    private Vec2i getCellMouseIsOver(Point mousePos) {
//        int x = (int) Math.floor((double) mousePos.x / gridCellSize) - 1;
//        int y = (int) Math.floor((double) mousePos.y / gridCellSize) - 1;
//
//        return new Vec2i(x, y);
//    }
}
