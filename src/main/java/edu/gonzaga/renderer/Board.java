package edu.gonzaga.renderer;

import edu.gonzaga.Coordinate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Board extends JPanel {
    private static final int TOTAL_CELLS = 11;


    private final edu.gonzaga.Board model;
    private int gridCellSize;
    private int boardSize;

    public edu.gonzaga.Ship ghostShip;

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
            drawShip((Graphics2D) g, Color.WHITE, ship);
        }

        if (ghostShip != null) {
            boolean valid = model.validateShipPlacement(ghostShip.getPosition(), ghostShip.isVertical(), ghostShip.getLength());

            drawShip((Graphics2D) g, valid ? Color.GREEN : Color.RED, ghostShip);
        }

        drawGrid(g);

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Coordinate coord = new Coordinate(x, y);

                if (model.isMarked(coord)) {
                    drawMarker(g, x + 1, y + 1, model.isMarkerHit(coord));
                }
            }
        }
    }

    public int getGridCellSize() {
        return gridCellSize;
    }

    public Coordinate getCellMouseIsOver() {
        Point mousePos = getMousePosition();

        if (mousePos == null) {
            return null;
        }

        int x = (int) Math.floor((double) mousePos.x / gridCellSize) - 1;
        int y = (int) Math.floor((double) mousePos.y / gridCellSize) - 1;

        return new Coordinate(x, y);
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

    private void drawShip(Graphics2D g, Color color, edu.gonzaga.Ship ship) {
        int x = ship.getPosition().x() + 1;
        int y = ship.getPosition().y() + 1;

        AffineTransform prevTransform = g.getTransform();

        if (!ship.isVertical()) {
            g.rotate(Math.toRadians(-90), x * gridCellSize, y * gridCellSize);
            g.translate(-gridCellSize, 0);
        }

        BufferedImage img;

        if (color == Color.RED) {
            img = ImageBuffers.getInstance().getImage(ship.getType().name().toLowerCase() + "-red");
        } else if (color == Color.GREEN) {
            img = ImageBuffers.getInstance().getImage(ship.getType().name().toLowerCase() + "-green");
        } else {
            img = ImageBuffers.getInstance().getImage(ship.getType().name().toLowerCase());
        }

        g.drawImage(img,
                x * gridCellSize,
                y * gridCellSize,
                gridCellSize,
                gridCellSize * ship.getLength(),
                null);

        g.setTransform(prevTransform);
    }

    private void drawMarker(Graphics g, int x, int y, boolean hit) {
        BufferedImage hitImage = ImageBuffers.getInstance().getImage("hit");
        BufferedImage missImage = ImageBuffers.getInstance().getImage("miss");

        g.drawImage(hit ? hitImage : missImage,
                x * gridCellSize,
                y * gridCellSize,
                gridCellSize,
                gridCellSize,
                null
        );
    }
}
