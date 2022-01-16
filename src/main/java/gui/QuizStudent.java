package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizStudent implements ActionListener {

    GUI window;
    Button [] answer;

    public QuizStudent(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        answer = new Button[4];

        answer[0] = new Button("", 20, 30, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "triangle", false);
        answer[1] = new Button("", Math.round(width / 2) + 20, 30, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "star", false);
        answer[2] = new Button("", 20, Math.round(height / 2) + 10, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "circle", false);
        answer[3] = new Button("", Math.round(width / 2) + 20, Math.round(height / 2) + 10, Math.round(width / 2) - 40, Math.round(height / 2) - 100, "square", false);

        answer[0].addActionListener(this);
        answer[1].addActionListener(this);
        answer[2].addActionListener(this);
        answer[3].addActionListener(this);

        window.frame.add(answer[0]);
        window.frame.add(answer[1]);
        window.frame.add(answer[2]);
        window.frame.add(answer[3]);

        window.reload();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
