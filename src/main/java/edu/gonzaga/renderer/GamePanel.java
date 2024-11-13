package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private edu.gonzaga.Board leftBoardModel;
    private edu.gonzaga.Board rightBoardModel;

    private Board leftBoard;
    private Board rightBoard;
    private Info info;
    private Ships leftShips;
    private Ships rightShips;

    /** Handles mouse events. */
    private MouseAdapter mouseAdapter;
    /** Handles key events. */
    private KeyAdapter keyAdapter;

    public GamePanel(edu.gonzaga.Board leftBoardModel, edu.gonzaga.Board rightBoardModel) {
        super();

        this.leftBoardModel = leftBoardModel;
        this.rightBoardModel = rightBoardModel;

        setLayout(new BattleshipLayout());

        leftBoard = new Board(leftBoardModel);
        add("leftBoard", leftBoard);

        rightBoard = new Board(rightBoardModel);
        add("rightBoard", rightBoard);

        info = new Info();
        add("info", info);

        leftShips = new Ships(leftBoardModel);
        add("leftShips", leftShips);

        rightShips = new Ships(rightBoardModel);
        add("rightShips", rightShips);

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClicked(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                onMouseMoved(e);
            }
        };

        keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                onKeyTyped(e);
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addKeyListener(keyAdapter);

        setOpaque(true);
    }

    private void onMouseClicked(MouseEvent e) {
    }

    private void onMouseMoved(MouseEvent e) {
        Point mousePos = rightBoard.getMousePosition();

        if (mousePos == null) {
            rightBoard.ghostShip = null;
        } else {
            int x = mousePos.x / rightBoard.getGridCellSize() - 1;
            int y = mousePos.y / rightBoard.getGridCellSize() - 1;

            rightBoard.ghostShip = new edu.gonzaga.GenericShip(x, y, true, 5);
        }

        rightBoard.repaint();
    }

    private void onKeyTyped(KeyEvent e) {
    }
}
