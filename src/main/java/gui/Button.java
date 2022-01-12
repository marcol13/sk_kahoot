package main.java.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    private String text;
    public Button(String text, int x, int y, int width, int height){
        this.text = text;
        this.setText(text);
        this.setBounds(x, y, width, height);
    }
}

