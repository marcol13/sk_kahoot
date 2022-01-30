package gui;

import org.json.JSONObject;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AppSettings {
    public static final int width = 800;
    public static final int height = 800;
    public static final String title = "Kahoot";
    public static JSONObject teacherJSON;
    public static Game gameJSON;
    public static int answerCreationQuantity;
    public static List<Question> questionList;
    public static ClientConnection cl;
    public static String gameId;
    public static String myName;
    public static List<String> userNames = null;
    public static Panel userPanel;
    public static int questionToEnd;
    public static int currentQuestion;
    public static HashMap<String, Integer> rankingMap;

    public static Thread t1;
    public static String serverAddress;
}
