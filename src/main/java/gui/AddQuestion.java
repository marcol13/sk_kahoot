package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddQuestion implements ActionListener {
    GUI window;
    Text questionContentText;
    InputText questionContentInput;
    Text [] answerText;
    InputText [] answerInput;
    Checkbox [] answerCheckbox;
    Button buttonNext;
    Button buttonLobby;
    Text errorMessage;
    int toMake;

    public AddQuestion(GUI window, int toMake){
        int width = AppSettings.width;
        int height = AppSettings.height;
        this.window = window;
        this.toMake = toMake;

        window.frame.getContentPane().removeAll();
        window.frame.repaint();

        questionContentText = new Text("Treść pytania " + (AppSettings.answerCreationQuantity - toMake + 1), 0, height / 14, width, height / 18);
        questionContentInput = new InputText(width / 4, height / 8, width / 2, 50);

        answerText = new Text[4];
        answerInput = new InputText[4];
        answerCheckbox = new Checkbox[4];

        for(int i = 0; i < 4; i++){
            answerText[i] = new Text("Odpowiedź "+(i+1), (width / 4) - 50, (height / 4) - 40 + i * 90, (width * 3/4), (height / 18));
            answerInput[i] = new InputText((width * 2 / 5) - 70, (height / 4) + i * 90, (width / 2), 50);
            answerCheckbox[i] = new Checkbox((width / 8) + 50, (height / 4) + 10 + i * 90);
            window.frame.add(answerText[i]);
            window.frame.add(answerInput[i]);
            window.frame.add(answerCheckbox[i]);
        }

        if(toMake == 1){
            buttonLobby = new Button("Przejdź do lobby", (width / 4), (height / 3) + 300, (width / 2), (height / 12));
            buttonLobby.addActionListener(this);
            window.frame.add(buttonLobby);
        }
        else{
            buttonNext = new Button("Następne pytanie", (width / 4), (height / 3) + 300, (width / 2), (height / 12));
            buttonNext.addActionListener(this);
            window.frame.add(buttonNext);
        }

        errorMessage = new Text("Podałeś błędne dane", 0, (height / 3) + 320 + (height / 12), AppSettings.width, (height/18));
        errorMessage.setVisible(false);

        window.frame.add(questionContentText);
        window.frame.add(questionContentInput);
        window.frame.add(errorMessage);

        window.reload();
    }

    public Question addQuestion(String questionContent, String [] answersContent){
        List<Answer> answers;
        answers = new ArrayList<>();
        Question result;
        for(int i = 0; i < 4; i++){
            answers.add(new Answer(answersContent[i], answerCheckbox[i].isSelected()));
        }
        result = new Question(questionContent, answers);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonNext){
            String questionContent = questionContentInput.getText();
            String [] answerContent = new String [4];
            boolean flagAnswer = true;
            boolean flagCheckbox = false;
            for(int i = 0; i < 4; i++){
                answerContent[i] = answerInput[i].getText();
                if(answerContent[i].isEmpty()){
                    flagAnswer = false;
                    break;
                }
                if(!flagCheckbox && answerCheckbox[i].isSelected()){
                    flagCheckbox = true;
                }
            }
            if(!questionContent.isEmpty() && flagAnswer && flagCheckbox){
                AppSettings.questionList.add(addQuestion(questionContent, answerContent));
                try {
                    sendAnswers();
                } catch (IOException ex) {
                    System.out.println("Problem z wysłaniem pytań");
                }
                new AddQuestion(window, toMake - 1);
            }
            else{
                errorMessage.setVisible(true);
            }
        }
        else if(e.getSource() == buttonLobby){
            String questionContent = questionContentInput.getText();
            String [] answerContent = new String [4];
            boolean flagAnswer = true;
            boolean flagCheckbox = false;
            for(int i = 0; i < 4; i++){
                answerContent[i] = answerInput[i].getText();
                if(answerContent[i].isEmpty()){
                    flagAnswer = false;
                    break;
                }
                if(!flagCheckbox && answerCheckbox[i].isSelected()){
                    flagCheckbox = true;
                }
            }
            if(!questionContent.isEmpty() && flagAnswer && flagCheckbox){
                AppSettings.questionList.add(addQuestion(questionContent, answerContent));
                try {
                    sendAnswers();
                } catch (IOException ex) {
                    System.out.println("Problem z wysłaniem pytań");
                }
                new Lobby(window, true);
            }
            else{
                errorMessage.setVisible(true);
            }
        }
    }

    public void sendAnswers() throws IOException {
        String answers = "";
        for(int i = 0; i < 4; i++){
            if(answerCheckbox[i].isSelected())
                answers += "\\1";
            else
                answers += "\\0";
        }
        AppSettings.cl.sendData("\\send_answers\\id\\"+(AppSettings.gameId+("\\answers"+answers)));
    }
}
