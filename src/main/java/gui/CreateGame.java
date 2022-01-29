package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class CreateGame implements ActionListener {
    GUI window;
    Button nextStepButton;
    InputText gameNameInput;
    Text gameNameLabel;
    InputNumeric questionsQuantityNumeric;
    Text questionsQuantityLabel;
    InputNumeric timeNumeric;
    Text timeLabel;
    Text errorMessage;

    public CreateGame(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        AppSettings.questionList = new ArrayList<Question>();

        //TODO Dodać przycisk back

        gameNameInput = new InputText(Math.round(width / 4), Math.round(height / 7), Math.round(width / 2), 50);
        gameNameLabel = new Text("Nazwa gry", 0, Math.round(height / 12), width, Math.round(height / 18));

        //TODO Dodać przyciski +/-
        questionsQuantityNumeric = new InputNumeric(1, Math.round(width / 2) - 25, Math.round(height / 7) + 160, 50, 50, 1, 10);
        questionsQuantityLabel = new Text("Ilość pytań", 0, Math.round(height/12) + 150, width, Math.round(height/18));

        timeNumeric = new InputNumeric(10, Math.round(width / 2) - 25, Math.round(height / 7) + 300, 50, 50, 10, 99);
        timeLabel = new Text("Czas na pytanie", 0, Math.round(height/12) + 300, width, Math.round(height/18));

        nextStepButton = new Button("Dalej", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));
        nextStepButton.addActionListener(this);

        errorMessage = new Text("Podałeś błędne dane", 0, Math.round(height / 3) + 320 + Math.round(height / 12), AppSettings.width, Math.round(height/18));
        errorMessage.setVisible(false);

        window.frame.add(gameNameInput);
        window.frame.add(gameNameLabel);
        window.frame.add(questionsQuantityNumeric);
        window.frame.add(questionsQuantityLabel);
        window.frame.add(timeNumeric);
        window.frame.add(timeLabel);
        window.frame.add(nextStepButton);
        window.frame.add(errorMessage);

        window.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextStepButton){
            String name = gameNameInput.getText();
            int quantity = (int) questionsQuantityNumeric.getValue();
            int time = (int) timeNumeric.getValue();
            if(!name.isEmpty() && quantity >= 1 && quantity <= 10 && time >= 10 && time <= 99){
                AppSettings.gameJSON = new Game(name, quantity, time);
                AppSettings.answerCreationQuantity = quantity;
                try {
                    AppSettings.cl = new ClientConnection("0.0.0.0", 5050);
                    AppSettings.cl.sendData("\\create_game\\"+name+"\\quantity\\"+quantity+"\\time\\"+time);
                    AppSettings.gameId = AppSettings.cl.getData();
                    AppSettings.questionToEnd = quantity;
//                    AppSettings.cl.sendData("\\id-"+AppSettings.gameId+"\\quantity\\"+quantity);
//                    AppSettings.cl.sendData("\\id-"+AppSettings.gameId+"\\time\\"+time);
                    new AddQuestion(window, quantity);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    errorMessage.setText("Błąd połączenia");
                    errorMessage.setVisible(true);
                }
            }
            else{
                errorMessage.setText("Błędne dane");
                errorMessage.setVisible(true);
            }

        }
    }
}
