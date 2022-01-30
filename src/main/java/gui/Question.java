package gui;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Question {
    String content;
    List<Answer> answers;

    public Question(String content, List<Answer> answers){
        this.content = content;
        this.answers = answers;
    }

    public JSONObject addToJSON() {
        JSONObject joQuestion = new JSONObject();
        JSONArray jaAnswers = new JSONArray();
        joQuestion.put("question", this.content);
        for(int i = 0; i < 4; i++){
            jaAnswers.put(answers.get(i).addToJSON());
        }
        joQuestion.put("answers", jaAnswers);
        return joQuestion;
    }
}