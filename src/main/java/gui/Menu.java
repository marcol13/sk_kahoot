package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Menu implements ActionListener {
    //Create with maketext.io

    Button createButton;
    Button joinButton;
    Button exitButton;
    GUI window;

    public Menu(GUI window){
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/Kahoot.png")));
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        AppSettings.currentQuestion = 0;
        AppSettings.isAdmin = false;
        AppSettings.isGameStarted = false;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        createButton = new Button("Stwórz nową grę", (width / 4), (height / 3), (width / 2), (height / 12));
        joinButton = new Button("Dołącz do istniejącej gry", (width / 4), (height / 3) + 150, (width / 2), (height / 12));
        exitButton = new Button("Zamknij", (width / 4), (height / 3) + 300, (width / 2), (height / 12));

        createButton.addActionListener(this);
        joinButton.addActionListener(this);
        exitButton.addActionListener(this);

        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(((width - logo.getIconWidth()) / 2), 100, logo.getIconWidth(), logo.getIconHeight());
        logoLabel.setVisible(true);

        window.frame.add(logoLabel);
        window.frame.add(createButton);
        window.frame.add(joinButton);
        window.frame.add(exitButton);

        window.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton){
            new CreateGame(window);
        }
        else if(e.getSource() == joinButton){
            new JoinRoom(window);
        }
        else if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}
