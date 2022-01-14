package gui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Game {
    String gameName;
    int questionQuantity;
    int questionTime;
    List<Question> questions;

    public Game(String gameName, int questionQuantity, int questionTime){
        this.gameName = gameName;
        this.questionQuantity = questionQuantity;
        this.questionTime = questionTime;
    }

    public void setQuestions(List<Question> questions){
        this.questions = questions;
    }

    public JSONObject addJSON(List<Question> questions) throws JSONException {
        JSONObject joGame = new JSONObject();
        JSONArray jaGame = new JSONArray();
        setQuestions(questions);
        joGame.put("name", this.gameName);
        joGame.put("questionQuantity", this.questionQuantity);
        joGame.put("questionTime", this.questionTime);
        for(int i = 0; i < this.questionQuantity; i++){
            jaGame.put(questions.get(i).addToJSON());
        }
        joGame.put("questions", jaGame);
        return joGame;
    }
}
