package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class JoinRoom implements ActionListener {
    GUI window;
    Text userNameText;
    InputText userNameInput;
    Text roomIDText;
    InputText roomIDInput;
    Button nextButton;
    Text errorMessage;


    public JoinRoom(GUI window){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        userNameText = new Text("Nazwa użytkownika", 0, (height / 7), width, (height / 18));
        userNameInput = new InputText((width / 4), (height / 7) + 60, (width / 2), 50);

        roomIDText = new Text("Numer pokoju", 0, (height / 3), width, (height / 18));
        roomIDInput = new InputText((width / 4), (height / 3) + 60, (width / 2), 50);

        nextButton = new Button("Dalej", (width / 4), (height / 3) + 300, (width / 2), (height / 12));
        nextButton.addActionListener(this);

        errorMessage = new Text("Podałeś błędne dane", 0, (height / 3) + 320 + (height / 12), AppSettings.width, (height/18));
        errorMessage.setVisible(false);

        window.frame.add(userNameText);
        window.frame.add(userNameInput);
        window.frame.add(roomIDText);
        window.frame.add(roomIDInput);
        window.frame.add(nextButton);
        window.frame.add(errorMessage);

        window.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton){
            String userName = userNameInput.getText();
            String gameID = roomIDInput.getText();
            if(!userName.isEmpty() && !gameID.isEmpty()){
                try {
                    AppSettings.cl = new ClientConnection(AppSettings.serverAddress, 5050);
                    AppSettings.cl.sendData("\\join_game\\id\\"+(gameID+("\\user\\"+userName)));
                    for(int i = 0; i < 2; i++){
                        String answer = AppSettings.cl.getData();
                       if(answer.indexOf("\\error\\id") == 0){
                            errorMessage.setText("Nie istnieje gra o takim ID");
                            errorMessage.setVisible(true);
                            break;
                        }
                        else if(answer.indexOf("\\error\\user") == 0){
                            errorMessage.setText("Istnieje już taki użytkownik");
                            errorMessage.setVisible(true);
                            break;
                        }
                        else if(answer.indexOf("\\error\\started") == 0){
                            errorMessage.setText("Gra już się rozpoczęła");
                            errorMessage.setVisible(true);
                            break;
                        }
                        else if(answer.indexOf("\\ok\\") == 0){
                            int iQuantity = answer.indexOf("\\quantity\\");
                            int iTime = answer.indexOf("\\time\\");
                            String gameName = answer.substring(14, iQuantity);
                            int gameQuantity = Integer.parseInt(answer.substring(iQuantity + 10, iTime));
                            int time = Integer.parseInt(answer.substring(iTime + 6));
                            AppSettings.gameId = gameID;
                            AppSettings.gameJSON = new Game(gameName, gameQuantity, time);
                            AppSettings.myName = userName;
                        }
                        else if(answer.indexOf("\\users\\") == 0){
                            int pos;
                            if(answer.length() == 7){
                                AppSettings.userNames = null;
                            }
                            else{
                                AppSettings.userNames = new ArrayList<>();
                                answer = answer.substring(7);
                                while(answer.contains("\\")){
                                    pos = answer.indexOf("\\");
                                    AppSettings.userNames.add(answer.substring(0,pos));
                                    answer = answer.substring(pos + 1);
                                }
                                AppSettings.userNames.add(answer);
                            }
                            new Lobby(window, false);
                        }
                        else{
                            errorMessage.setText("Błąd połączenia");
                            errorMessage.setVisible(true);
                            break;
                        }
                    }

                } catch (IOException ex) {
                    System.out.println("Problem z dołączeniem do gry");
                }
            }
            else{
                errorMessage.setText("Podałeś błędne dane");
                errorMessage.setVisible(true);
            }
        }
    }
}
