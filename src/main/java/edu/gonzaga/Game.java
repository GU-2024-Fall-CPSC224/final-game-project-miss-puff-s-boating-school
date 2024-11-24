package edu.gonzaga;

import edu.gonzaga.renderer.GameFrame;
import edu.gonzaga.renderer.GamePanel;
import edu.gonzaga.renderer.PlaceShipCallback;

public class Game implements Runnable, PlaceShipCallback {
    @Override
    public void run() {
        Board leftBoard = new Board();
        Board rightBoard = new Board();

        GameFrame frame = new GameFrame();
        GamePanel gamePanel = new GamePanel(leftBoard, rightBoard);

        frame.setActivePanel(gamePanel);

        gamePanel.placeShip(Ship.shipType.CARRIER, 0, this);
    }

    @Override
    public void onShipPlaced() {
        System.out.println("Ship placed");
    }
}
