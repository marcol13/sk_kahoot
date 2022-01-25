package gui;

import org.json.JSONObject;

public class Answer {
    String content;
    Boolean isCorrect;

    public Answer(String content, Boolean isCorrect){
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public JSONObject addToJSON() throws Exception {
        JSONObject joAnswer = new JSONObject();
        joAnswer.put("question", this.content);
        joAnswer.put("isCorrect", this.isCorrect);
        return joAnswer;
    }
}
