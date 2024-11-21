package edu.gonzaga.renderer;

import edu.gonzaga.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamePanel extends JPanel implements PropertyChangeListener {
    private edu.gonzaga.Board leftBoardModel;
    private edu.gonzaga.Board rightBoardModel;

    private boolean leftTurn = true;

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

    public void setPlayerTurn(boolean leftTurn) {
        this.leftTurn = leftTurn;
    }

    public void placeShip(boolean player1, Ship.shipType type, int length, PlaceShipCallback callback) {

    }

    private void onMouseClicked(MouseEvent e) {
    }

    private void onMouseMoved(MouseEvent e) {
        leftBoard.repaint();
        rightBoard.repaint();
    }

    private void onKeyTyped(KeyEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
