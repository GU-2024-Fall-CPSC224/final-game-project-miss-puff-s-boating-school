package edu.gonzaga.renderer;

import java.awt.*;

public class IntroLayout implements LayoutManager {
    private Component title;
    private Component players;
    private Component buttons;

    @Override
    public void addLayoutComponent(String name, Component comp) {
        switch (name) {
            case "title":
                title = comp;
                break;
            case "players":
                players = comp;
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
        players.setBounds(width / 4, height * 2 / 6, width * 2 / 4, height / 6);
        buttons.setBounds(0, height * 4 / 6, width, height * 2 / 6);
    }
}
