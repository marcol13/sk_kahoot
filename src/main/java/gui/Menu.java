package main.java.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    //Create with maketext.io
    private ImageIcon logo = new ImageIcon(getClass().getResource("/main/resources/images/Kahoot.png"));
    Button createButton;
    Button joinButton;
    Button exitButton;
    GUI window;

    public Menu(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        createButton = new Button("Stwórz nową grę", Math.round(width / 4), Math.round(height / 3), Math.round(width / 2), Math.round(height / 12));
        joinButton = new Button("Dołącz do istniejącej gry", Math.round(width / 4), Math.round(height / 3) + 150, Math.round(width / 2), Math.round(height / 12));
        exitButton = new Button("Zamknij", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));

        createButton.addActionListener(this);
        joinButton.addActionListener(this);
        exitButton.addActionListener(this);

        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds(Math.round((width - logo.getIconWidth()) / 2), 100, logo.getIconWidth(), logo.getIconHeight());
        logoLabel.setVisible(true);
        System.out.println(logoLabel);

        window.frame.add(logoLabel);
        window.frame.add(createButton);
        window.frame.add(joinButton);
        window.frame.add(exitButton);

        window.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createButton){
            System.out.println("Create");
            new CreateGame(window);
        }
        else if(e.getSource() == joinButton){
            System.out.println("Join");
        }
        else if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}
