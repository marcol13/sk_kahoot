package gui;

import javax.swing.*;
import java.util.Objects;

public class Checkbox extends JCheckBox {

    //check
    //https://www.flaticon.com/premium-icon/check_368633?term=check&page=1&position=2&page=1&position=2&related_id=368633&origin=tag
    ImageIcon checkIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/check.png")));

    //cancel
    //https://www.flaticon.com/free-icon/close_1828665?term=cancel&page=1&position=14&page=1&position=14&related_id=1828665&origin=search
    ImageIcon closeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/resources/close.png")));

    public Checkbox(int x, int y, int width, int height){
        this.setFocusable(false);
        this.setBounds(x, y, 40, 32);
        this.setIcon(closeIcon);
        this.setSelectedIcon(checkIcon);
    }
}
