package main.java.gui;

import main.java.gui.GUI;
import main.java.gui.Menu;

public class main {
    public static void main(String[] args){
        GUI window = new GUI();
        new Menu(window);
    }
}
