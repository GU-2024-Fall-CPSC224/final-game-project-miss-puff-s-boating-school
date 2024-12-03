package edu.gonzaga.renderer;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends JPanel {
    private JTextField player1Name;
    private JTextField player2Name;

    public IntroPanel(IntroPanelCallbacks callbacks) {
        super();

        setLayout(new IntroLayout());
        setOpaque(false);

        createTitle();
        createPlayers();
        createButtons(callbacks);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageBuffers.getInstance().getImage("intro-splash"), 0, 0, getWidth(), getHeight(), null);

        super.paintComponent(g);
    }

    private void createTitle() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        JLabel title = new JLabel("BATTLESHIP");
        title.setAlignmentX(0.5f);
        title.setFont(Palette.getTitleFont(72));
        title.setForeground(Palette.WHITE);
        container.add(title);

        JLabel authors = new JLabel("Miss Puff's Boating School");
        authors.setAlignmentX(0.5f);
        authors.setFont(Palette.getPrimaryFont(24));
        authors.setForeground(Palette.WHITE);
        container.add(authors);

        add("title", container);
    }

    private void createPlayers() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        JLabel player1Label = new JLabel("Player 1 Name");
        player1Label.setFont(Palette.getPrimaryFont(32));
        player1Label.setForeground(Palette.WHITE);
        player1Label.setAlignmentX(0.5f);
        container.add(player1Label);

        player1Name = new JTextField("Player 1", 20);
        player1Name.setFont(Palette.getPrimaryFont(24));
        player1Name.setMaximumSize(player1Name.getPreferredSize());
        container.add(player1Name);

        container.add(Box.createVerticalGlue());

        JLabel player2Label = new JLabel("Player 2 Name");
        player2Label.setFont(Palette.getPrimaryFont(32));
        player2Label.setForeground(Palette.WHITE);
        player2Label.setAlignmentX(0.5f);
        container.add(player2Label);

        player2Name = new JTextField("Player 2", 20);
        player2Name.setFont(Palette.getPrimaryFont(24));
        player2Name.setMaximumSize(player2Name.getPreferredSize());
        container.add(player2Name);

        add("players", container);
    }

    private void createButtons(IntroPanelCallbacks callbacks) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        JButton startButton = createButton();
        startButton.setText("Start Game");
        container.add(startButton);
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        startButton.addActionListener(e -> callbacks.introPanelOnStartGame(
                player1Name.getText(),
                player2Name.getText()));

        JButton settingsButton = createButton();
        settingsButton.setText("Settings");
        container.add(settingsButton);
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        settingsButton.addActionListener(e -> callbacks.introPanelOnSettings());

        JButton quitButton = createButton();
        quitButton.setText("Quit");
        container.add(quitButton);

        quitButton.addActionListener(e -> System.exit(0));

        add("buttons", container);
    }

    private JButton createButton() {
        JButton button = new JButton();

        button.setFont(Palette.getPrimaryFont(32));
        button.setBackground(Palette.CLEAR);
        button.setOpaque(false);
        button.setForeground(Palette.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Palette.WHITE, 2),
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                )
        );

        return button;
    }
}
