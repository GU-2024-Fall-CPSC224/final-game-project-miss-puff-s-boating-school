package edu.gonzaga.renderer;

import java.awt.*;

public class IntroLayout implements LayoutManager {
    private Component title;
    private Component player1;
    private Component player2;
    private Component buttons;

    @Override
    public void addLayoutComponent(String name, Component comp) {
        switch (name) {
            case "title":
                title = comp;
                break;
            case "player1":
                player1 = comp;
                break;
            case "player2":
                player2 = comp;
                break;
            case "buttons":
                buttons = comp;
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

        title.setBounds(0, 0, width, height / 6);
        player1.setBounds(0, height / 6, width / 4, height * 3 / 6);
        player2.setBounds(width * 3 / 4, height / 6, width / 4, height * 3 / 6);
        buttons.setBounds(0, height * 4 / 6, width, height * 2 / 6);
    }
}
