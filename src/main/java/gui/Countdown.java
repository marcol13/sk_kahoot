package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Countdown{
    Timer timer;
    int secondsStart;
    int secondsPassed;
    Text content;
    GUI window;
    Button next;

    public Countdown(GUI window, int secondsStart, int x, int y, int width, int height, int fontSize, Button next){
        this.secondsStart = secondsStart;
        timer = new Timer();
        this.secondsPassed = 0;
        this.window = window;
        this.next = next;

        content = new Text(String.valueOf(secondsStart), x, y, width, height, Color.BLACK, fontSize);
        window.frame.add(content);
    }

    TimerTask task = new TimerTask(){
        public void run(){
            if(secondsStart == secondsPassed){
                timer.cancel();
                timer.purge();
                next.setVisible(true);
                return;
            }
            secondsPassed++;
            content.setText(String.valueOf(secondsStart - secondsPassed));
            System.out.println(secondsStart - secondsPassed);
        }
    };

    public void start(){
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

}
