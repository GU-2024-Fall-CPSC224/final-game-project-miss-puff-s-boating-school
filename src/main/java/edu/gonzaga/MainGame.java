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

/** Main program class for launching your team's program. */
public class MainGame {
    public static void main(String[] args) {
        Board leftBoard = new Board();
        leftBoard.ships.add(new GenericShip(0, 0, true, 5));
        leftBoard.ships.add(new GenericShip(2, 2, false, 4));
        leftBoard.ships.add(new GenericShip(7, 6, true, 3));
        leftBoard.ships.add(new GenericShip(6, 7, true, 2));
        leftBoard.ships.add(new GenericShip(4, 4, false, 3));
        leftBoard.ships.add(new GenericShip(4, 4, false, 3));
        leftBoard.ships.add(new GenericShip(4, 4, false, 3));
        leftBoard.ships.add(new GenericShip(4, 4, false, 3));

        leftBoard.hits[0][0] = true;
        leftBoard.hits[3][7] = true;
        leftBoard.hits[2][5] = true;

        Board rightBoard = new Board();
        rightBoard.ships.add(new GenericShip(3, 0, false, 5));
        rightBoard.ships.add(new GenericShip(5, 2, true, 4));
        rightBoard.ships.add(new GenericShip(2, 4, true, 3));
        rightBoard.ships.add(new GenericShip(5, 7, false, 2));
        rightBoard.ships.add(new GenericShip(0, 6, false, 3));

        rightBoard.hits[0][0] = true;
        rightBoard.hits[7][3] = true;
        rightBoard.hits[4][7] = true;

        javax.swing.SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            GamePanel panel = new GamePanel(leftBoard, rightBoard);

            frame.setActivePanel(panel);
        });
    }
}
