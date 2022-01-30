package gui;


import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

public class ReadThread extends Thread{

    GUI window;

    public ReadThread(GUI window){
        this.window = window;
    }

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
                    System.out.println(AppSettings.myName + (" v1: "+ AppSettings.userNames));
                    AppSettings.userNames.add(user);
                    System.out.println(AppSettings.myName + (" v2: "+ AppSettings.userNames));
                    System.out.println("add user: " + user);

                    AppSettings.userPanel.showNames(window);
                }
                if(command.indexOf("\\delete_user\\") == 0){
                    String user = command.substring(13);
                    if(AppSettings.userNames.remove(user)){
                        AppSettings.userPanel.showNames(window);
                    }
                    else{
                        System.out.println("Nie ma takiego użytkownika: " + user);
                    }
                }
                if(command.indexOf("\\unreachable_host\\") == 0){
                    new Menu(window);
                    JOptionPane.showMessageDialog(null, "Host nieosiągalny", "Kahoot", JOptionPane.PLAIN_MESSAGE);
//                    AppSettings.cl.closeConnection();
                    this.join();
                }
                if(command.indexOf("\\next_question\\") == 0){
                    new QuizStudent(window);
                }
                if(command.indexOf("\\ok\\start_game\\") == 0){
                    new QuizTeacher(window, AppSettings.questionToEnd);
                }
                if(command.indexOf("\\error\\no_users") == 0){
                    JOptionPane.showMessageDialog(null, "Nie ma żadnych graczy", "Kahoot", JOptionPane.PLAIN_MESSAGE);
                }
                if(command.indexOf("\\end_game\\") == 0){
                    new Menu(window);
                    JOptionPane.showMessageDialog(null, "Koniec gry", "Kahoot", JOptionPane.PLAIN_MESSAGE);
//                    AppSettings.cl.closeConnection();
                    this.join();
                }
                if(command.indexOf("\\question_end\\admin") == 0){
                    Countdown.changeWrongToGrey();
                }
                if(command.indexOf("\\question_end\\user") == 0){
                    QuizStudent.endQuestion();
                }
                if(command.indexOf("\\game_results\\") == 0){
                    AppSettings.rankingMap = new HashMap<>();
                    while(command.contains("\\name\\")){
                        int nameIndex = command.indexOf("\\name\\");
                        int pointsIndex = command.indexOf("\\score\\");
                        String tempCommand = command.substring(pointsIndex + 7);
                        String user = command.substring(nameIndex + 6, pointsIndex);
                        String points;

                        if(!tempCommand.contains("\\"))
                            points = command.substring(pointsIndex + 7);
                        else{
                            int tempSlash = tempCommand.indexOf("\\");
                            points = command.substring(pointsIndex + 7, pointsIndex + 7 + tempSlash);
                        }

                        AppSettings.rankingMap.put(user, Integer.parseInt(points));
                        System.out.println("user: " + user + " points: " + points);
                        command = command.substring(pointsIndex + 8);
                    }
                }

                //TODO End game with score board -> close connection and end thread
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
