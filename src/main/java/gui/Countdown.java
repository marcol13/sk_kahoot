package gui;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Countdown{
    Timer timer;
    int secondsStart;
    int secondsPassed;
    Text content;
    GUI window;
    Button next;
    Button [] answers;
    Boolean [] isCorrect;

    public Countdown(GUI window, int secondsStart, int x, int y, int width, int height, int fontSize, Button next, Button [] answers, Boolean [] isCorrect){
        this.secondsStart = secondsStart;
        timer = new Timer();
        this.secondsPassed = 0;
        this.window = window;
        this.next = next;
        this.answers = answers;
        this.isCorrect = isCorrect;

        content = new Text(String.valueOf(secondsStart), x, y, width, height, Color.BLACK, fontSize);
        window.frame.add(content);
    }

    TimerTask task = new TimerTask(){
        public void run(){
            if(secondsStart == secondsPassed){
                timer.cancel();
                timer.purge();
                try {
                    AppSettings.cl.sendData("\\countdown_end\\id\\"+AppSettings.gameId);
                } catch (IOException e) {
                    if(AppSettings.rankingMap != null){
                        new ScoreBoard(window);
                    }
                    else{
                        new Menu(window);
                    }
                }
                return;
            }
            secondsPassed++;
            content.setText(String.valueOf(secondsStart - secondsPassed));
        }
    };

    public void start(){
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public static void changeWrongToGrey(){
        QuizTeacher.nextQuestion.setVisible(true);
        for(int i = 0; i < 4; i++){
            if(!QuizTeacher.correctAnswers[i]){
                QuizTeacher.answers[i].changeToGrey();
            }
        }
    }

}
