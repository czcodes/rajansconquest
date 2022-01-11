
package main.java.game_manager;

import main.java.province_construction.Province;
import main.java.usecases.ProcessValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  This file contains the implementation for the AIDecisionMaker Class.
 *  Responsibility: This file is the basis and computation of the AI
 *  that is used in the game to make decision's for the 4 AI Provinces.
 */

public class AIDecisionMaker {
    public AIDecisionMaker(){
        // Empty Constructor
    }

    /** Totals all stats of given province up and creates a list of percentages that represent the odds of choosing
     * a certain choice. This is the third iteration of the AI.
     *
     * @param province The province that is acting
     *
     * returns a list of values of strings 1,2,3
     * May decide to return more than 1 value in the list.
     */
    public List<String> getDecisions(Province province){
        float total = province.getProvinceGold()+province.getProvinceSoldiers()+ province.getProvinceFood();
        List<Float> percents = new ArrayList<>();
        // Add percents for choice weights
        percents.add((total/province.getProvinceFood()));
        percents.add((percents.get(0) + total/province.getProvinceGold()));
        percents.add((percents.get(1) + total/province.getProvinceSoldiers()));
        List<String> choices = new ArrayList<>();
        Random rand = new Random();
        float choice;
        for (int i = 0; i < 3; i++) {
            choice = rand.nextFloat() * percents.get(2);
            if (choice <= percents.get(0)) {
                // Boost food
                choices.add("1");
            } else if (choice <= percents.get(1)) {
                // Boost soldiers
                choices.add("2");
            } else {
                // Boost gold
                choices.add("3");
            }
        }
        return choices;
    }

    /** Generate 1 number which represents the choice the given user made.
     * This method allows for the decisions to be skewed, meaning that one decision is favored over another
     */
    public List<String> getDecisions(List<Integer> skews){
        Random rand = new Random();
        int choice;
        List<String> choices = new ArrayList<>();

        choice = rand.nextInt(100);

        if (choice <= skews.get(0)){
            // Boost food
            choices.add("1");
        }

        else if (choice <= skews.get(1)){
            // Boost soldiers
            choices.add("2");
        }

        else {
            // Boost revenue
            choices.add("3");
        }

        return choices;
    }

    /**This is the basis of the AI. The AI uses the random numbers from getDecisions
     * to change the values of each province.
     *
     * @param province The province that the AI will act on
     */
    public void makeDecisions(Province province){
        ProcessValues processor = new ProcessValues();
        List<String> choices = getDecisions(province);
        //
        Random random = new Random();

        for (String choice : choices) {
            int bet = (province.returnMaximumValue(choice)/ (5 + random.nextInt(5)));
            processor.getUserDecision(choice, province, bet);
        }
        List<Integer> value = randomizeAiEvent();
        processor.getUserEventDecision("Y", province, value);
    }

    /** This is the basis of the AI. The AI uses the random numbers from getDecisions
     * to change the values of each province.
     *
     * @param province The province that the AI will act on
     * @param modifier The value that a province attribute will be modified by
     */
    public void makeDecisions(Province province, int modifier, List<Integer> skews){
        ProcessValues processor = new ProcessValues();
        List<String> choices = getDecisions(skews);

        for (String choice : choices) {
            processor.getUserDecision(choice, province, modifier);
        }
        List<Integer> value =  randomizeAiEvent();
        processor.getUserEventDecision("Y", province, value);
    }

    /**
     * This function randomizes a list of number which represents if the user will have an event.
     */
    public List<Integer> randomizeAiEvent(){

        List<Integer> randList = new ArrayList<>();
        int counter = 0;
        Random rand = new Random();
        int randInt;
        while(counter < 4){
            randInt = (-3 + rand.nextInt(6)) * 10;
            randList.add(randInt);
            counter++;
        }
        return randList;
    }
}
