package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizTeacher implements ActionListener {

    GUI window;
    Text questionContent;
    Button [] answers;
    Countdown timer;
    Button nextQuestion;

    public QuizTeacher(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        questionContent = new Text("Ile jest rzek w Polsce", 15, 65, width - 30, 100, Color.BLACK);

        answers = new Button[4];

        answers[0] = new Button("25", 15, 220, Math.round(width / 2) - 30, 150, "triangle", true);
        answers[1] = new Button("145", Math.round(width / 2) + 15, 220, Math.round(width / 2) - 30, 150, "star", true);
        answers[2] = new Button("12", 15, 385, Math.round(width / 2) - 30, 150, "circle", true);
        answers[3] = new Button("2137", Math.round(width / 2) + 15, 385, Math.round(width / 2) - 30, 150, "square", true);

        nextQuestion = new Button("Dalej", 600, 600, 100, 40);
        nextQuestion.setVisible(false);

        nextQuestion.addActionListener(this);

        timer = new Countdown(window, 10, Math.round(width / 2) - 30, 600, 60, 60, 40, nextQuestion);
        timer.start();


        window.frame.add(nextQuestion);

        window.frame.add(answers[0]);
        window.frame.add(answers[1]);
        window.frame.add(answers[2]);
        window.frame.add(answers[3]);

        window.frame.add(questionContent);

        window.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextQuestion){
            System.out.println("siema");
        }
    }
}
