package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lobby extends Thread implements ActionListener {
    GUI window;
    Text gameNameText;
    Text usersListTitleText;
    List<String> userNames;
    Boolean isAdmin;
    Text gameId;
    Text questionTime;
    Text questionQuantity;
    Button startGame;
    JPanel userPanel;

    String command;

    public Lobby(GUI window, Boolean isAdmin){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;
        this.isAdmin = isAdmin;

        if(isAdmin || AppSettings.userNames == null)
            AppSettings.userNames = new ArrayList<>();

        if(!isAdmin)
            System.out.println("is empty lobby: " +(AppSettings.myName + (" v1: "+ AppSettings.userNames.size())));
//        System.out.println(AppSettings.myName + (" v1: "+ AppSettings.userNames));


        window.frame.getContentPane().removeAll();
        window.frame.repaint();



        ////
//        userNames.add("Michał");
//        userNames.add("Marcin");
//        userNames.add("Maciej");
//        userNames.add("PawełToGej2137");
        ////





//        showNames();

//        userPanel = new JPanel()

        gameNameText = new Text(AppSettings.gameJSON.gameName, 0, Math.round(height / 15), width, Math.round(height / 18));

        usersListTitleText = new Text("Użytkownicy:", 0, Math.round(height / 6), Math.round(width / 2), Math.round(height / 18));

        gameId = new Text("Id gry: "+AppSettings.gameId, Math.round(width / 2) + 20, Math.round(height / 6) + 10, Math.round(width / 2) - 40, 50, Color.BLACK);

        questionQuantity = new Text("Ilość pytań: " + AppSettings.gameJSON.questionQuantity, Math.round(width / 2) + 20, Math.round(height / 6) + 70, Math.round(width / 2) - 40, 50, Color.BLACK);

        questionTime = new Text("Czas na pytanie: " + AppSettings.gameJSON.questionTime, Math.round(width / 2) + 20, Math.round(height / 6) + 130, Math.round(width / 2) - 40, 50, Color.BLACK);



        window.frame.add(gameNameText);
        window.frame.add(usersListTitleText);
        window.frame.add(gameId);
        window.frame.add(questionQuantity);
        window.frame.add(questionTime);

        if(isAdmin){
            startGame = new Button("Rozpocznij grę", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));
            startGame.addActionListener(this);
            window.frame.add(startGame);

            try {
                AppSettings.teacherJSON = AppSettings.gameJSON.addJSON(AppSettings.questionList);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        window.reload();

        Thread t1 = new readThread();

        t1.start();
    }

    public class readThread extends Thread{


        @Override
        public void run(){
            String command = "";

            while(true){
                try {
                    command = AppSettings.cl.getData();
                    System.out.println("komenda lobby: " + command);
//                    if(command.indexOf("\\users\\") == 0){
//                        int pos;
//                        if(command.length() == 7){
//                            continue;
//                        }
//                        command = command.substring(6);
//                        while(command.contains("\\")){
//                            pos = command.indexOf("\\");
//                            userNames.add(command.substring(0,pos));
//                            System.out.println("pos + 1: " + command.substring(0,pos));
//                            command = command.substring(pos + 1);
//                        }
//                        userNames.add(command);
//                        showNames();
//                        System.out.println(command);
//                    }
                    if(command.indexOf("\\add_user\\") == 0){
                        String user = command.substring(10);
                        if(user.equals("")){
                            System.out.println("o ja pierdole");
                        }
                        System.out.println(AppSettings.myName + (" v1: "+ AppSettings.userNames));
                        AppSettings.userNames.add(user);
                        System.out.println(AppSettings.myName + (" v2: "+ AppSettings.userNames));
                        System.out.println("add user: " + user);

                        showNames();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showNames(){
        System.out.println("show names!");
        if(AppSettings.userNames.size() != 0){
            int i = 0;
            for(String user : AppSettings.userNames){
                Text temp = new Text(user, 20, Math.round(AppSettings.height / 4) + i * 30, Math.round(AppSettings.width / 2) - 40,  Math.round(AppSettings.height / 18));
                window.frame.add(temp);
                i++;
            }
//            for(int i = 0; i < userNames.size(); i++){
//                Text temp = new Text(userNames.get(i), 20, Math.round(AppSettings.height / 4) + i * 30, Math.round(AppSettings.width / 2) - 40,  Math.round(AppSettings.height / 18));
//                window.frame.add(temp);
//            }
            System.out.println(AppSettings.myName + (": "+ AppSettings.userNames));
            window.reload();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGame){
            new QuizTeacher(window, AppSettings.gameJSON.questionQuantity);
        }
    }
}
