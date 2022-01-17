package gui;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizTeacher implements ActionListener {

    GUI window;
    Text questionContent;
    Button [] answers;
    Countdown timer;
    Button nextQuestion;
    int questionToEnd;
    JSONObject questionJSON;
    String questionName;
    String [] answersName;
    Boolean [] correctAnswers;

    public QuizTeacher(GUI window, int questionToEnd){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;
        this.questionToEnd = questionToEnd;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        try {
            questionJSON = AppSettings.teacherJSON.getJSONArray("questions").getJSONObject(AppSettings.answerCreationQuantity - questionToEnd);
            questionName = String.valueOf(questionJSON.get("question"));
            answersName = new String[4];
            correctAnswers = new Boolean[4];
            for(int i = 0; i < 4; i++){
                answersName[i] = String.valueOf(questionJSON.getJSONArray("answers").getJSONObject(i).get("question"));
                correctAnswers[i] = (Boolean) questionJSON.getJSONArray("answers").getJSONObject(i).get("isCorrect");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        questionContent = new Text(questionName, 15, 65, width - 30, 100, Color.BLACK);

        answers = new Button[4];

        answers[0] = new Button(answersName[0], 15, 220, Math.round(width / 2) - 30, 150, "triangle", true);
        answers[1] = new Button(answersName[1], Math.round(width / 2) + 15, 220, Math.round(width / 2) - 30, 150, "star", true);
        answers[2] = new Button(answersName[2], 15, 385, Math.round(width / 2) - 30, 150, "circle", true);
        answers[3] = new Button(answersName[3], Math.round(width / 2) + 15, 385, Math.round(width / 2) - 30, 150, "square", true);

        nextQuestion = new Button("Dalej", 600, 600, 150, 40);
        nextQuestion.setVisible(false);

        nextQuestion.addActionListener(this);

        timer = new Countdown(window, AppSettings.gameJSON.questionTime, Math.round(width / 2) - 30, 600, 60, 60, 40, nextQuestion, answers, correctAnswers);
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
            if(questionToEnd == 1){
                new ScoreBoard(window);
            }
            else{
                new QuizTeacher(window, questionToEnd - 1);
            }
        }
    }
}
