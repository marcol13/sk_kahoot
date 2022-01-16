package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Button extends JButton {
    private String text;
    ImageIcon icon;
    public Button(String text, int x, int y, int width, int height){
        this.text = text;
        this.setText(text);
        this.setBounds(x, y, width, height);
    }

    //https://www.iconsdb.com/white-icons/star-icon.html
    //https://www.iconsdb.com/white-icons/square-icon.html
    //https://www.iconsdb.com/white-icons/circle-icon.html
    //https://www.iconsdb.com/white-icons/triangle-icon.html
    public Button(String text, int x, int y, int width, int height, String shape, Boolean isAdmin){
        if(isAdmin){
            if(shape.equals("star")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/star-32.png")));
                this.setBackground(Color.CYAN);
            }
            else if(shape.equals("triangle")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/triangle-32.png")));
                this.setBackground(Color.RED);
            }
            else if(shape.equals("circle")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/circle-32.png")));
                this.setBackground(Color.YELLOW);
            }
            else if(shape.equals("square")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/square-32.png")));
                this.setBackground(Color.GREEN);
            }
            this.setHorizontalAlignment(SwingConstants.LEFT);
        }
        else{
            if(shape.equals("star")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/star-48.png")));
                this.setBackground(Color.CYAN);
            }
            else if(shape.equals("triangle")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/triangle-48.png")));
                this.setBackground(Color.RED);
            }
            else if(shape.equals("circle")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/circle-48.png")));
                this.setBackground(Color.YELLOW);
            }
            else if(shape.equals("square")){
                icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/square-48.png")));
                this.setBackground(Color.GREEN);
            }
        }
        this.setIcon(icon);
        this.setText(text);
        this.setHorizontalTextPosition(JButton.RIGHT);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setIconTextGap(15);
        this.setBounds(x, y, width, height);
        this.setFocusable(false);
    }
}

