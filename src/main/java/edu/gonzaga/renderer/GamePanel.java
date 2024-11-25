package edu.gonzaga.renderer;

import edu.gonzaga.ships.*;
import edu.gonzaga.Coordinate;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamePanel extends JPanel implements PropertyChangeListener {
    private edu.gonzaga.Board leftBoardModel;
    private edu.gonzaga.Board rightBoardModel;

    private boolean player1Turn = true;

    private boolean placingShip = false;
    private Ship.ShipType currentShipType;
    private boolean currentShipVertical;
    private PlaceShipCallback placeShipCallback;

    private Board leftBoard;
    private Board rightBoard;
    private Info info;
    private Ships leftShips;
    private Ships rightShips;

    /** Handles mouse events. */
    private MouseAdapter mouseAdapter;

    private Action rotateShipAction;

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

        rotateShipAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (placingShip) {
                    currentShipVertical = !currentShipVertical;
                    updateGhostShip();
                }
            }
        };

        getInputMap().put(KeyStroke.getKeyStroke("R"), "rotateShip");
        getActionMap().put("rotateShip", rotateShipAction);

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        setOpaque(true);
    }

    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    public void placeShip(Ship.ShipType type, PlaceShipCallback callback) {
        placingShip = true;
        currentShipType = type;
        currentShipVertical = true;
        placeShipCallback = callback;
    }

    private void onMouseClicked(MouseEvent e) {
        if (placingShip) {
            Ship ship = getCurrentBoard().ghostShip;

            // Just because we are placing a ship doesn't mean we have a ghost ship to try and place
            if (ship == null) {
                return;
            }

            if (getCurrentBoardModel().validateShipPlacement( ship )) {
                getCurrentBoardModel().addShip(ship);

                placingShip = false;
                placeShipCallback.onShipPlaced();
            }
        }
    }

    private void onMouseMoved(MouseEvent e) {
        updateGhostShip();
    }

    private Board getCurrentBoard() {
        return player1Turn ? leftBoard : rightBoard;
    }

    private edu.gonzaga.Board getCurrentBoardModel() {
        return player1Turn ? leftBoardModel : rightBoardModel;
    }

    private void updateGhostShip() {
        if (placingShip) {
            if (player1Turn) {
                leftBoard.ghostShip = constructGhostShip(leftBoard);

                leftBoard.repaint();
            } else {
                rightBoard.ghostShip = constructGhostShip(rightBoard);

                rightBoard.repaint();
            }
        }
    }

    private Ship constructGhostShip(Board board) {
        Coordinate coords = board.getCellMouseIsOver();

        if (coords == null) {
            return null;
        }

        switch (currentShipType) {
            case CARRIER -> {
                return new Carrier(coords.x(), coords.y(), currentShipVertical);
            }
            case BATTLESHIP -> {
                return new Battleship(coords.x(), coords.y(), currentShipVertical);
            }
            case CRUISER -> {
                return new Cruiser(coords.x(), coords.y(), currentShipVertical);
            }
            case SUBMARINE -> {
                return new Submarine(coords.x(), coords.y(), currentShipVertical);
            }
            case DESTROYER -> {
                return new Destroyer(coords.x(), coords.y(), currentShipVertical);
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
