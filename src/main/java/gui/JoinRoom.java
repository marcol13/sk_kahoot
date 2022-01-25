package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

        userNameText = new Text("Nazwa użytkownika", 0, Math.round(height / 7), width, Math.round(height / 18));
        userNameInput = new InputText(Math.round(width / 4), Math.round(height / 7) + 60, Math.round(width / 2), 50);

        roomIDText = new Text("Numer pokoju", 0, Math.round(height / 3), width, Math.round(height / 18));
        roomIDInput = new InputText(Math.round(width / 4), Math.round(height / 3) + 60, Math.round(width / 2), 50);

        nextButton = new Button("Dalej", Math.round(width / 4), Math.round(height / 3) + 300, Math.round(width / 2), Math.round(height / 12));
        nextButton.addActionListener(this);

        errorMessage = new Text("Podałeś błędne dane lub numer pokoju nie istnieje", 0, Math.round(height / 3) + 320 + Math.round(height / 12), AppSettings.width, Math.round(height/18));
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
                    AppSettings.cl = new ClientConnection("0.0.0.0", 5050);
                    AppSettings.cl.sendData("\\join_game\\id\\"+(gameID+("\\user\\"+userName)));
                    String answer = AppSettings.cl.getData();
                    System.out.println(answer);
                    System.out.println(answer.equals("\\error\\id"));
                    if(answer.indexOf("\\error\\id") == 0){
                        errorMessage.setText("Nie istnieje gra o takim ID");
                        errorMessage.setVisible(true);
                    }
                    else if(answer.indexOf("\\error\\user") == 0){
                        errorMessage.setText("Istnieje już taki użytkownik");
                        errorMessage.setVisible(true);
                    }
                    else if(answer.indexOf("\\ok\\") == 0){
                        int iQuantity = answer.indexOf("\\quantity\\");
                        int iTime = answer.indexOf("\\time\\");
                        String gameName = answer.substring(14, iQuantity);
                        int gameQuantity = Integer.parseInt(answer.substring(iQuantity + 10, iTime));
                        int time = Integer.parseInt(answer.substring(iTime + 6));
                        AppSettings.gameId = gameID;
                        AppSettings.gameJSON = new Game(gameName, gameQuantity, time);
                        new Lobby(window, false);
                    }
                    else{
                        errorMessage.setText("Błąd połączenia");
                        errorMessage.setVisible(true);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                errorMessage.setVisible(true);
            }
        }
    }
}
