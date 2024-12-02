package edu.gonzaga.renderer;

import edu.gonzaga.Game;
import edu.gonzaga.Settings;
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

    private GamePanelCallbacks callbacks;

    private boolean placingShip;
    private Ship.ShipType currentShipType;
    private boolean currentShipVertical;

    private boolean takingAction;

    private Board leftBoard;
    private Board rightBoard;
    private Info info;
    private Ships leftShips;
    private Ships rightShips;

    /**
     * Handles mouse events.
     */
    private MouseAdapter mouseAdapter;

    private Action rotateShipAction;

    public GamePanel(GamePanelCallbacks callbacks,
                     edu.gonzaga.Board leftBoardModel,
                     edu.gonzaga.Board rightBoardModel,
                     edu.gonzaga.Player player1,
                     edu.gonzaga.Player player2) {
        super();

        this.callbacks = callbacks;

        this.leftBoardModel = leftBoardModel;
        this.rightBoardModel = rightBoardModel;

        setLayout(new BattleshipLayout());

        leftBoard = new Board(leftBoardModel);
        add("leftBoard", leftBoard);

        rightBoard = new Board(rightBoardModel);
        add("rightBoard", rightBoard);

        info = new Info( player1, player2 );
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

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "rotateShip");
        getActionMap().put("rotateShip", rotateShipAction);

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        setOpaque(true);
    }

    public void setGameState(Game.GameState gameState) {
        this.gameState = gameState;
        // While setting up, Change display based on player setup.:
        info.displayPlayerSetup( gameState );

        if (Settings.getInstance().hideShipsOnBoard) {
            getCurrentBoard().hideShips = false;
            getOppositeBoard().hideShips = true;
        }
    }

    public void placeShip(Ship.ShipType type) {
        placingShip = true;
        currentShipType = type;
        currentShipVertical = true;
    }

    public void takeAction() {
        takingAction = true;
    }

    private void onMouseClicked(MouseEvent e) {
        if (placingShip) {
            Ship ship = getCurrentBoard().ghostShip;

            // Just because we are placing a ship doesn't mean we have a ghost ship to try and place
            if (ship == null) {
                return;
            }

            if (getCurrentBoardModel().validateShipPlacement(ship)) {
                getCurrentBoardModel().addShip(ship);

                getCurrentBoard().ghostShip = null;
                placingShip = false;
                // Display who's turn it is to setup ships:

                callbacks.onShipPlaced();
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

            takingAction = false;
            // Display checked space result, and that it is now the other player's turn.
            info.displayPlayerTurn( gameState, getOppositeBoardModel().isMarkerHit(coord) );
            callbacks.onActionTaken();
        }
    }

    private void onMouseMoved(MouseEvent e) {
        updateGhostShip();
        updateGhostMarker();
    }

    private Board getCurrentBoard() {
        if ((gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN)) {
            return leftBoard;
        } else {
            return rightBoard;
        }
    }

    private Board getOppositeBoard() {
        if ((gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN)) {
            return rightBoard;
        } else {
            return leftBoard;
        }
    }

    private edu.gonzaga.Board getCurrentBoardModel() {
        if ((gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN)) {
            return leftBoardModel;
        } else {
            return rightBoardModel;
        }
    }

    private edu.gonzaga.Board getOppositeBoardModel() {
        if ((gameState == Game.GameState.PLAYER_1_SETUP) || (gameState == Game.GameState.PLAYER_1_TURN)) {
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
