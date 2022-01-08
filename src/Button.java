import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {
    private String text;
    public Button(String text, int x, int y, int width, int height){
        this.text = text;
        this.addActionListener(this);
        this.setText(text);
        this.setBounds(x, y, width, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this){
            System.out.println(text);
        }
    }
}

