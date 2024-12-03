package edu.gonzaga.renderer;

import edu.gonzaga.Player;

import javax.swing.*;
import java.awt.*;

/**
 * The JPanel that is displayed when the game is over. Contains game statistics and buttons to restart or quit.
 */
public class EndPanel extends JPanel {
    public EndPanel(EndPanelCallbacks callbacks, Player winner) {
        super(new BorderLayout());

        setBackground(Color.BLACK);

        JLabel endLabel = new JLabel("GAME OVER");
        endLabel.setForeground(Color.WHITE);
        endLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(endLabel, BorderLayout.NORTH);

        JLabel winnerLabel = new JLabel();
        // Check for a draw: Neither player wins.
        if ( winner == null ) {
           winnerLabel.setText( "DRAW" );
        }
        else {
            winnerLabel.setText( winner.getName() + " wins!" );
        }
        winnerLabel.setForeground(Color.WHITE);
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(winnerLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        add(buttonPanel, BorderLayout.SOUTH);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> callbacks.endPanelOnRestart());

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(e -> callbacks.endPanelOnMainMenu());

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(restartButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(mainMenuButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(quitButton);
    }
}
