package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;

public class Info extends JPanel {
    public Info() {
        super();

        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.PLAIN, 42);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);

        int x = (getWidth() - metrics.stringWidth("Battleship")) / 2;

        g.drawString("Battleship", x, 40);

        font = new Font("Arial", Font.PLAIN, 32);
        g.setFont(font);
        metrics = g.getFontMetrics(font);

        x = (getWidth() - metrics.stringWidth("_____'s Turn")) / 2;

        g.drawString("_____'s Turn", x, 100);
    }
}
