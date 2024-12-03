package edu.gonzaga.renderer;

import edu.gonzaga.Player;

import javax.swing.*;
import java.awt.*;

/**
 * The JPanel that is displayed when the game is over. Contains game statistics and buttons to restart or quit.
 */
public class EndPanel extends JPanel {

    public EndPanel(GameUICallbacks callbacks, Player winner) {
        super(new BorderLayout());

        setOpaque(false);

        JLabel endLabel = new JLabel("GAME OVER");
        endLabel.setFont(Palette.getTitleFont(48));
        endLabel.setForeground(Palette.WHITE);
        endLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(endLabel, BorderLayout.NORTH);

        JLabel winnerLabel = new JLabel();
        winnerLabel.setFont(Palette.getTitleFont(72));
        winnerLabel.setForeground(Palette.WHITE);
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        if (winner == null) {
            winnerLabel.setText("DRAW");
        } else {
            winnerLabel.setText(winner.getName() + " wins!");
        }

        add(winnerLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton mainMenuButton = createButton("Return to Main Menu");
        mainMenuButton.addActionListener(e -> callbacks.endPanelOnMainMenu());

        JButton quitButton = createButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(mainMenuButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(quitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageBuffers.getInstance().getImage("ending-splash"), 0, 0, getWidth(), getHeight(), null);

        super.paintComponent(g);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(Palette.getPrimaryFont(32));
        button.setBackground(Palette.CLEAR);
        button.setForeground(Palette.WHITE);
        button.setFocusPainted(false);
        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Palette.WHITE, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                )
        );

        return button;
    }
}
