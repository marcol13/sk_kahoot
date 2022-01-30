package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ScoreBoard implements ActionListener {

    GUI window;
    Text scoreBoardTitle;
    Button toMenu;

    public ScoreBoard(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

//        AppSettings.t1.interrupt();
//        AppSettings.cl.closeConnection();

        window.frame.getContentPane().removeAll();
        window.frame.repaint();


        Map<String, Integer> sorted = sortByValue(AppSettings.rankingMap);

        scoreBoardTitle = new Text("Tablica wyników", Math.round(width / 2) - 250, 75, 500, 100, Color.BLACK, 45);

        int i = 0;
        for(Map.Entry<String, Integer> en: sorted.entrySet()){
            Text textUser = new Text(en.getKey(), Math.round(width / 2) - 250, 175 + i * 30, 450, 30, Color.BLACK);
            Text scoreUser = new Text(String.valueOf(en.getValue()), Math.round(width / 2) + 200, 175 + i * 30, 50, 30, Color.BLACK);
            window.frame.add(textUser);
            window.frame.add(scoreUser);
            i++;
        }

        toMenu = new Button("Wróć do menu", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));
        toMenu.addActionListener(this);

        window.frame.add(scoreBoardTitle);
        window.frame.add(toMenu);


        window.reload();
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == toMenu){
            new Menu(window);
        }
    }
}
