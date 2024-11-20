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

/** Main program class for launching your team's program. */
public class MainGame {
    public static void main(String[] args) {
        Board leftBoard = new Board();
        Board rightBoard = new Board();

        javax.swing.SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
//            GamePanel panel = new GamePanel(leftBoard, rightBoard);
            IntroPanel panel = new IntroPanel();

            frame.setActivePanel(panel);
        });
    }
}
