package edu.gonzaga.renderer;

import javax.swing.*;

public class IntroPanel extends JPanel {
    private JTextField player1Name;
    private JTextField player2Name;

    public IntroPanel(IntroPanelCallbacks callbacks) {
        super();

        setLayout(new IntroLayout());

        createTitle();
        createPlayers();
        createButtons(callbacks);
    }

    private void createTitle() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Battleship");
        title.setAlignmentX(0.5f);
        container.add(title);

        JLabel authors = new JLabel("Miss Puff's Boating School");
        authors.setAlignmentX(0.5f);
        container.add(authors);
        add("title", container);
    }

    private void createPlayers() {
        JPanel player1Container = new JPanel();
        player1Container.setLayout(new BoxLayout(player1Container, BoxLayout.Y_AXIS));

        JLabel player1Label = new JLabel("Player 1");
        player1Label.setAlignmentX(0.5f);
        player1Container.add(player1Label);

        player1Name = new JTextField(20);
        player1Name.setMaximumSize(player1Name.getPreferredSize());
        player1Container.add(player1Name);

        JPanel player2Container = new JPanel();
        player2Container.setLayout(new BoxLayout(player2Container, BoxLayout.Y_AXIS));

        JLabel player2Label = new JLabel("Player 2");
        player2Label.setAlignmentX(0.5f);
        player2Container.add(player2Label);

        player2Name = new JTextField(20);
        player2Name.setMaximumSize(player2Name.getPreferredSize());
        player2Container.add(player2Name);

        add("player1", player1Container);
        add("player2", player2Container);
    }

    private void createButtons(IntroPanelCallbacks callbacks) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(0.5f);
        container.add(startButton);

        startButton.addActionListener(e -> callbacks.introPanelOnStartGame(
                player1Name.getText(),
                player2Name.getText()));

        JButton settingsButton = new JButton("Settings");
        settingsButton.setAlignmentX(0.5f);
        container.add(settingsButton);

        settingsButton.addActionListener(e -> callbacks.introPanelOnSettings());

        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(0.5f);
        container.add(quitButton);

        quitButton.addActionListener(e -> System.exit(0));

        add("buttons", container);
    }
}
