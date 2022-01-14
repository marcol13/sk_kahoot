package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGame implements ActionListener {
    GUI window;
    Button nextStepButton;
    InputText gameNameInput;
    Text gameNameLabel;
    InputNumeric questionsQuantityNumeric;
    Text questionsQuantityLabel;
    InputNumeric timeNumeric;
    Text timeLabel;

    public CreateGame(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        gameNameInput = new InputText(Math.round(width / 4), Math.round(height / 7), Math.round(width / 2), 50);
        gameNameLabel = new Text("Nazwa gry", 0, Math.round(height / 12), width, Math.round(height / 18));

        //TODO Dodać przyciski +/-
        questionsQuantityNumeric = new InputNumeric(1, Math.round(width / 2) - 25, Math.round(height / 7) + 160, 50, 50, 1, 10);
        questionsQuantityLabel = new Text("Ilość pytań", 0, Math.round(height/12) + 150, width, Math.round(height/18));

        timeNumeric = new InputNumeric(10, Math.round(width / 2) - 25, Math.round(height / 7) + 300, 50, 50, 10, 99);
        timeLabel = new Text("Czas na pytanie", 0, Math.round(height/12) + 300, width, Math.round(height/18));

        nextStepButton = new Button("Dalej", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));

        window.frame.add(gameNameInput);
        window.frame.add(gameNameLabel);
        window.frame.add(questionsQuantityNumeric);
        window.frame.add(questionsQuantityLabel);
        window.frame.add(timeNumeric);
        window.frame.add(timeLabel);
        window.frame.add(nextStepButton);

        window.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
