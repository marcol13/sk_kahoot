package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Lobby implements ActionListener {
    GUI window;
    Text gameNameText;
    Text usersListTitleText;
    List<String> userNames;
    Boolean isAdmin;
    Text gameId;
    Text questionTime;
    Text questionQuantity;
    Button startGame;

    public Lobby(GUI window, Boolean isAdmin){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;
        this.isAdmin = isAdmin;

        userNames = new ArrayList<>();
        ////
        userNames.add("Michał");
        userNames.add("Marcin");
        userNames.add("Maciej");
        userNames.add("PawełToGej2137");

        ////

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        gameNameText = new Text("Nazwa gry", 0, Math.round(height / 15), width, Math.round(height / 18));

        usersListTitleText = new Text("Użytkownicy:", 0, Math.round(height / 6), Math.round(width / 2), Math.round(height / 18));

        gameId = new Text("Id gry: 1234", Math.round(width / 2) + 20, Math.round(height / 6) + 10, Math.round(width / 2) - 40, 50, Color.BLACK);

        questionQuantity = new Text("Ilość pytań: 8", Math.round(width / 2) + 20, Math.round(height / 6) + 70, Math.round(width / 2) - 40, 50, Color.BLACK);

        questionTime = new Text("Czas na pytanie: 60", Math.round(width / 2) + 20, Math.round(height / 6) + 130, Math.round(width / 2) - 40, 50, Color.BLACK);

        if(userNames.size() != 0){
            for(int i = 0; i < userNames.size(); i++){
                Text temp = new Text(userNames.get(i), 20, Math.round(height / 4) + i * 30, Math.round(width / 2) - 40,  Math.round(height / 18));
                window.frame.add(temp);
            }
        }

        if(isAdmin){
            startGame = new Button("Rozpocznij grę", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));
            window.frame.add(startGame);
        }

        window.frame.add(gameNameText);
        window.frame.add(usersListTitleText);
        window.frame.add(gameId);
        window.frame.add(questionQuantity);
        window.frame.add(questionTime);

        window.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
