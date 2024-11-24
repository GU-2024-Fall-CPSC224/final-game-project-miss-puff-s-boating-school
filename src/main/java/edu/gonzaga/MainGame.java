/*
 * Final project main driver class
 *
 *
 * Project Description:
 *
 *
 * Contributors:
 *
 *
 * Copyright: 2023
 */
package edu.gonzaga;


import edu.gonzaga.renderer.GameFrame;
import edu.gonzaga.renderer.GamePanel;
import edu.gonzaga.renderer.IntroPanel;
import edu.gonzaga.renderer.PlaceShipCallback;

/** Main program class for launching your team's program. */
public class MainGame {
    public static void main(String[] args) {
        Board leftBoard = new Board();
        Board rightBoard = new Board();

        javax.swing.SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
//            IntroPanel introPanel = new IntroPanel();

//            frame.setActivePanel(introPanel);
//
//            introPanel.addPropertyChangeListener("start", evt -> {
//                GamePanel gamePanel = new GamePanel(leftBoard, rightBoard);
//                frame.setActivePanel(gamePanel);
//            });
//
//            introPanel.addPropertyChangeListener("settings", evt -> {
//                System.out.println("Settings");
//            });

            GamePanel gamePanel = new GamePanel(leftBoard, rightBoard);
            gamePanel.placeShip(Ship.shipType.CARRIER, 0, () -> {
                System.out.println("Ship placed");
            });

            frame.setActivePanel(gamePanel);
        });
    }
}
