package gui;

import javax.swing.*;
import java.awt.*;

public class Text extends JLabel {

    public Text(String text, int x, int y, int width, int height){
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
//        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
