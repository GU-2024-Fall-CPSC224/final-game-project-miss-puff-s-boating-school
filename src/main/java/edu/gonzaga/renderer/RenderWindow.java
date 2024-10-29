package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RenderWindow implements Runnable {
    public static final int DEFAULT_WIDTH = 1920;
    public static final int DEFAULT_HEIGHT = 1080;

    private edu.gonzaga.Board leftBoardModel;
    private edu.gonzaga.Board rightBoardModel;

    private JFrame window;
    private Info info;
    private Board leftBoard;
    private SunkShips leftSunkShips;
    private Board rightBoard;
    private SunkShips rightSunkShips;

    public RenderWindow(edu.gonzaga.Board leftBoardModel, edu.gonzaga.Board rightBoardModel) {
        this.leftBoardModel = leftBoardModel;
        this.rightBoardModel = rightBoardModel;
    }

    @Override
    public void run() {
        window = new JFrame("Battleship");
        window.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        window.getContentPane().setLayout(new BattleshipLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

        window.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        window.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        leftBoard = new Board(leftBoardModel);
        window.add("leftBoard", leftBoard);

        rightBoard = new Board(rightBoardModel);
        window.add("rightBoard", rightBoard);

        leftSunkShips = new SunkShips();
        window.add("leftSunkShips", leftSunkShips);

        rightSunkShips = new SunkShips();
        window.add("rightSunkShips", rightSunkShips);

        info = new Info();
        window.add("info", info);

        window.pack();
        window.setVisible(true);
    }
}