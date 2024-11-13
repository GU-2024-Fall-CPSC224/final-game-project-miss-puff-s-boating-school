package edu.gonzaga.renderer;

import java.awt.*;

public class BattleshipLayout implements LayoutManager {
    private Component leftBoard;
    private Component rightBoard;
    private Component leftShips;
    private Component rightShips;
    private Component info;

    @Override
    public void addLayoutComponent(String name, Component comp) {
        switch (name) {
            case "leftBoard":
                leftBoard = comp;
                break;
            case "rightBoard":
                rightBoard = comp;
                break;
            case "leftShips":
                leftShips = comp;
                break;
            case "rightShips":
                rightShips = comp;
                break;
            case "info":
                info = comp;
                break;
        }
    }

    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int width = parent.getWidth() - insets.left - insets.right;
        int height = parent.getHeight() - insets.top - insets.bottom;

        int gridSize = Math.round(Math.min(width / 2.5f, height / 1.4f));

        leftBoard.setBounds(insets.left, insets.top, gridSize, gridSize);
        rightBoard.setBounds(width - gridSize - insets.right, insets.top, gridSize, gridSize);

        leftShips.setBounds(insets.left, insets.top + gridSize, gridSize, height - gridSize);
        rightShips.setBounds(width - gridSize - insets.right, insets.top + gridSize, gridSize, height - gridSize);

        info.setBounds(insets.left + gridSize, insets.top, width - gridSize * 2, height);
    }
}
