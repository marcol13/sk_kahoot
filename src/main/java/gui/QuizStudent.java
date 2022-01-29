package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuizStudent implements ActionListener {

    GUI window;
    Button [] answer;
    Text questionNumber;

    public QuizStudent(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        questionNumber = new Text("Pytanie " + (AppSettings.currentQuestion + 1), 20, 20, Math.round(width) - 40, 60, Color.BLACK, 32);

        AppSettings.currentQuestion++;

        answer = new Button[4];

        answer[0] = new Button("", 20, 100, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "triangle", false);
        answer[1] = new Button("", Math.round(width / 2) + 20, 100, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "star", false);
        answer[2] = new Button("", 20, Math.round(height / 2) + 30, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "circle", false);
        answer[3] = new Button("", Math.round(width / 2) + 20, Math.round(height / 2) + 30, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "square", false);

        answer[0].addActionListener(this);
        answer[1].addActionListener(this);
        answer[2].addActionListener(this);
        answer[3].addActionListener(this);

        window.frame.add(questionNumber);
        window.frame.add(answer[0]);
        window.frame.add(answer[1]);
        window.frame.add(answer[2]);
        window.frame.add(answer[3]);

        window.reload();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 4; i++){
            if(e.getSource() == answer[i]){
                for(int j = 0; j < 4; j++){
                    if(i != j)
                        answer[j].changeToGrey();
                    answer[j].removeActionListener(this);
                }
                try {
                    AppSettings.cl.sendData("\\answer_student\\id\\"+AppSettings.gameId+"\\number\\"+ AppSettings.currentQuestion + "\\answer\\" + i);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
