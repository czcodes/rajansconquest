package main.java.game_manager;
import java.util.ArrayList;

/**
 *  This file contains the implementation for the Decisions Class.
 *  Responsibility: This file is to send the decisions to the GameEngine
 *  when needed.
 */
public class Decisions {

    // Instance Variable: ArrayList with all the Possible Decisions Presented to User
    private final ArrayList<String> decisionQuestions = new ArrayList<>();

    public Decisions() {
        this.decisionQuestions.add("Do you want to boost your food for one round?");
        this.decisionQuestions.add("Do you want to enlist more soldiers?");
        this.decisionQuestions.add("Do you want to increase revenue?");
    }

    /**
     * Returns the ArrayList with All the Possible Decisions
     */
    public ArrayList<String> getDecisionQuestions() {
        return decisionQuestions;
    }

    /* Displays All Decision Questions In the Console */
    public void displayQuestions(){
        for (String decisionQuestion : decisionQuestions) {
            System.out.println(decisionQuestion);
        }
    }
}
