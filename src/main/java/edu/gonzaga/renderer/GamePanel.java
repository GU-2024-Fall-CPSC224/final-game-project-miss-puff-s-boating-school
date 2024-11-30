package edu.gonzaga.renderer;

import edu.gonzaga.Game;
import edu.gonzaga.ships.*;
import edu.gonzaga.Coordinate;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamePanel extends JPanel implements PropertyChangeListener {
    private edu.gonzaga.Board leftBoardModel;
    private edu.gonzaga.Board rightBoardModel;

    private Game.GameState gameState;

    private boolean placingShip;
    private Ship.ShipType currentShipType;
    private boolean currentShipVertical;
    private PlaceShipCallback placeShipCallback;

    private boolean takingAction;
    private TakeActionCallback takeActionCallback;

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

        leftBoardModel.pcs.addPropertyChangeListener(this);
        leftBoardModel.pcs.addPropertyChangeListener(leftShips);
        rightBoardModel.pcs.addPropertyChangeListener(this);
        rightBoardModel.pcs.addPropertyChangeListener(rightShips);

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
                if (!placingShip) {
                    return;
                }

                currentShipVertical = !currentShipVertical;
                updateGhostShip();
            }
        };

        getInputMap().put(KeyStroke.getKeyStroke("R"), "rotateShip");
        getActionMap().put("rotateShip", rotateShipAction);

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        setOpaque(true);
    }

    public void setGameState(Game.GameState gameState) {
        this.gameState = gameState;
    }

    public void placeShip(Ship.ShipType type, PlaceShipCallback callback) {
        placingShip = true;
        currentShipType = type;
        currentShipVertical = true;
        placeShipCallback = callback;
    }

    public void takeAction( TakeActionCallback callback) {
        takingAction = true;
        takeActionCallback = callback;
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

                getCurrentBoard().ghostShip = null;
                placingShip = false;
                placeShipCallback.onShipPlaced();
            }
        }

        if (takingAction) {
            Coordinate coord = getOppositeBoard().getCellMouseIsOver();

            // If the current coordinate is not a space on the board, return.
            if (coord == null) {
                return;
            }
            // If the current coordinate is already marked, return.
            if (getOppositeBoardModel().isMarked(coord)) {
                return;
            }

            getOppositeBoard().ghostMarker = null;
            getOppositeBoardModel().setMarked(coord);
            info.displayPlayerHit( getOppositeBoardModel().isMarkerHit(coord) );

            /*
             * Here we would need to indicate to the display field that there was a hit, maybe?
             */

            takingAction = false;
            takeActionCallback.onActionTaken();
        }
    }

    private void onMouseMoved(MouseEvent e) {
        updateGhostShip();
        updateGhostMarker();
//        repaint();
    }

    private Board getCurrentBoard() {
        if ( (gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN) ) {
            return leftBoard;
        } else {
            return rightBoard;
        }
    }

    private Board getOppositeBoard() {
        if ( (gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN) ) {
            return rightBoard;
        } else {
            return leftBoard;
        }
    }

    private edu.gonzaga.Board getCurrentBoardModel() {
        if ( (gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN) ) {
            return leftBoardModel;
        } else {
            return rightBoardModel;
        }
    }

    private edu.gonzaga.Board getOppositeBoardModel() {
        if ( (gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN) ) {
            return rightBoardModel;
        } else {
            return leftBoardModel;
        }
    }

    private void updateGhostShip() {
        if (!placingShip) {
            return;
        }

        getCurrentBoard().ghostShip = constructGhostShip(getCurrentBoard());
        getCurrentBoard().repaint();
    }

    private void updateGhostMarker() {
        if (!takingAction) {
            return;
        }

        getOppositeBoard().ghostMarker = getOppositeBoard().getCellMouseIsOver();
        getOppositeBoard().repaint();
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
