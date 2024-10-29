package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;

public class SunkShips extends JPanel {
    public SunkShips() {
        super();
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.PLAIN, 32);
        g.setFont(font);

        g.drawString("Sunk Ships", 10, 40);
    }

    private void drawShip(Graphics g, int index, int length) {
        g.setColor(Color.RED);
        g.fillRect(10 + 75 * index, 50, 40, length * 50);
    }
}
