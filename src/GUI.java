import javax.swing.*;
import java.awt.*;

public class GUI {
    private int top = 400;
    private int bottom = 400;
    private int left = 400;
    private int right = 400;
    public JFrame frame;
    public JPanel panel;

    public GUI(){
        frame = new JFrame();
//        panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right));
//        panel.setLayout(new GridLayout());
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setSize(width, height);
        frame.setPreferredSize(new Dimension(AppSettings.width, AppSettings.height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(AppSettings.title);
        frame.pack();
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void reload(){
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }




}
