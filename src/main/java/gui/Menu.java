package main.java.gui;

import javax.swing.*;

public class Menu {
    //Create with maketext.io
    private ImageIcon logo = new ImageIcon(getClass().getResource("/main/resources/images/Kahoot.png"));

    public Menu(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        Button createButton = new Button("Stwórz nową grę", Math.round(width / 4), Math.round(height / 3), Math.round(width / 2), Math.round(height / 12));
        Button joinButton = new Button("Dołącz do istniejącej gry", Math.round(width / 4), Math.round(height / 3) + 150, Math.round(width / 2), Math.round(height / 12));
        Button exitButton = new Button("Zamknij", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));

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
}
