package main.java.game_manager;
import main.java.province_construction.Province;
import main.java.province_construction.ProvinceAssembler;
import main.java.province_construction.ProvinceBuilder;
import main.java.snapshots.CaretakerProvince;
import main.java.snapshots.MementoProvince;
import main.java.snapshots.OriginatorProvince;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.ui.UserInterface;
import main.java.usecases.Battle;
import main.java.usecases.ProcessValues;
import main.java.game_save.GameState;
import main.java.game_save.SaveLoad;

/**
 *  This file contains the implementation for the GameEngine Class.
 *  Responsibility: This file is the core of our Game which is
 *  responsible for running the game, turns, taking input to the Use Cases,
 *  and taking output to the UserInterface.
 */

public class GameEngine {
    /**
     * Below are all the Instance Variables for most of the classes that are connected within
     * the Game Engine.
     * NOTE: We have made all the instance variables final to ensure that they do not get mutated during the game.
     */
    private final UserInterface ui;
    private final Decisions decisionList;
    private final ProcessValues processor;
    private  Province playerProvince;
    private  ArrayList<Province> aiProvinces;
    private final AIDecisionMaker aiChoices;
    private final OriginatorProvince origProvince;
    private final CaretakerProvince ctProvince;
    private final Battle battleGenerator;
    private String name;

    public GameEngine() throws IOException {
        // First, initialize the UserInterface, and
        // other instances classes that are instance variables
        ui = UserInterface.initializeUI();
        decisionList = new Decisions();
        processor = new ProcessValues();
        battleGenerator = new Battle();
        origProvince = new OriginatorProvince();
        ctProvince = new CaretakerProvince();
        aiChoices = new AIDecisionMaker();

        // Opening game message
        ui.displayText("Welcome to Rajan's Conquest! Gather resources, assemble your army, and conquer all the " +
                "neighbouring provinces! \n");

        // Asking the User about their previous GameState
        Boolean saveBool = ui.askLoad();
        ArrayList<Object> list;
        list = getSaveDecision(saveBool);

        // Creation of the User Province
        if(!saveBool) {
            startProvince(list);
        }


    }

    /**
     *  Asks the user if they want to load a previous state.
     *  If they load a previous state, new provinces are created
     *  Then each province is updated with the save.ser file
     *  If they choose to run a new game, then ui.startPlayer() is called
     *  and the game runs normally
     *
     * @param saveBool the result of the User's prompt
     * @throws IOException if the save or load process does not work.
     */
    private ArrayList<Object> getSaveDecision(Boolean saveBool) throws IOException {
        ArrayList<Object> list;
        if (saveBool) {
            File f = new File(ui.getFile());
            if(f.exists() && !f.isDirectory()) {
                list = new ArrayList<>(loadPoint(ui.getFile()));
                startProvince(list);

                String userProvinceName = (String) list.get(1);
                List<Object> subArr = list.subList(2, 6);

                processor.updateProvince(playerProvince, subArr, true, userProvinceName);


                int counter = 6;
                for (Province p: aiProvinces){
                    String userAiName = (String) list.get(counter);
                    List<Object> subArr1 = list.subList(counter + 1, counter + 5);

                    processor.updateProvince(p, subArr1, true, userAiName);
                    counter += 5;
                }
                for (Province p: aiProvinces) {
                    printAttributes(p);
                }
                ui.displayText("Welcome back to Rajan's Conquest, " + list.get(0));
            }else{
                ui.displayText("There is no save.ser file!");
                ui.displayText("Starting new game...");
                list = new ArrayList<>(ui.startPlayer());
                startProvince(list);

            }

        } else{
            list = new ArrayList<>(ui.startPlayer());
        }
        return list;
    }
    
    /**
     * Loops the game until the playerProvince is NOT dead.
     * @throws IOException if the save or load process does not work.
     */
    public void loopGame() throws IOException {
        while (!playerProvince.isDeath()) {
            turn();
        }
        death();
    }
    
    /**
     * When the game is started, this function initializes our provinces 
     *
     * @param list The list with the game parameters parsed from gameState
     */
    public void startProvince(ArrayList<Object> list) {
        ProvinceBuilder provinceBuilder1 = new ProvinceBuilder();
        ProvinceAssembler provinceUserAssembler = new ProvinceAssembler(provinceBuilder1);
        name = (String) list.get(0);
        provinceUserAssembler.makeUserProvince(name);
        playerProvince = provinceUserAssembler.getUserProvince();

        // Creation of the 4 AI Provinces
        ProvinceAssembler provinceAiAssembler = new ProvinceAssembler();
        aiProvinces = provinceAiAssembler.create();
    }

    /**
     * Runs each turn:
     * i) Each turn consists of 3 turns in total,
     *  where User is given 1 Decisions and 2 Random Events during the process. After each turn,
     *  changes are made and then is displayed to the User.
     *  NOTE: Each event affects the user's attributes that are preset depending on their choice.
     *
     *  *** A ROUND CONSISTS of 3 turns and AFTER EACH ROUND, USER HAS A OPTION TO BATTLE OTHER PROVINCE ***
     *
     * ii) Next, AI turns are conducted for every province and values are altered.
     * iii) After 3 turn (round ends), snapshot of the User attributes are taken.
     * iv) Then, the User is prompted to start a Battle
     * v) User has the option to get a summary of their attributes.
     *
     * @throws IOException if the save or load process does not work.
     */
    public void turn() throws IOException {
        // Displaying the two Event
        printAttributes(playerProvince);
        processEvent();
        //second display
        printAttributes(playerProvince);
        processEvent();

        if (playerProvince.isDeath()){
            death();}
        printAttributes(playerProvince);

        // will make decision about increasing food/soldiers/civilian
        processDecision();
        if (playerProvince.isDeath()){
            death();}
        //each AI province will make a similar decision
        // and we check to see if they have died
        aiTurn();
        stateSnapshot(playerProvince);

        //game ends if all provinces are dead
        if (allDead()){
            conclusion();
        }

        // go into battle with the provinces that are still alive
        battle_option();
        if (ui.askSummary()){
            summaryOfStates();
        }

        //each round there is food reduction
        foodReduction();

        // Checks to see if all the other provinces have been beaten

        if (allDead()){
            conclusion();
        }

        // saving the game after each round
        savePoint(createSaveList(name), ui.getFile());
    }

    /**
     * An equivalent of the Python "all" function to see if the user has beaten all provinces
     */
    private boolean allDead(){
        for (Province aiProvince: aiProvinces){
            if (aiProvince.getStatus()) return false;
        }
        return true;
    }

    /**
     * Ends the game
     */
    private void conclusion(){
        ui.displayText("Congratulations, you have conquered all the other provinces! You win!");
        System.exit(0);
    }

    /**
     * Makes the Decisions for all 4 AI Provinces in the back which is hidden from the User.
     */
    private void aiTurn() {
        displayValues(playerProvince);
        for (Province currProvince : aiProvinces) {
            if (currProvince.getStatus()) {
                if (currProvince.getProvinceGold() > 0){
                    aiChoices.makeDecisions(currProvince);}
                displayValues(currProvince);
                if (currProvince.isDeath()) {
                    provinceDeath(currProvince);
                }
            }
        }
    }


    /**
     * Displays values after the User's choice given a Decision.
     */
    private void displayValues(Province province) {
        printAttributes(province);
    }

    /**
     * This function gets a random event and the displays it
     * You then answer Y/N to the question, and it changes your parameters based on the individual event
     */
    public void processEvent() {
        Events event = new Events();
        String eventName = Events.getRandomEvent();
        List<Integer> eventValues = Events.getValues(eventName);
        ui.displayText(eventName);
        String choice = ui.getEventChoice();
        processor.getUserEventDecision(choice, playerProvince, eventValues);
    }

    /**
     * Multi-tasked Function for Processing the Values after User's decision:
     *                    i) Displays the 3 Decisions.
     *                    ii) Obtains the User's Choice.
     *                    iii) Finds the attribute that needs to be altered.
     *                    iv) Sends this information to the ProcessValues for updating.
     */
    private void processDecision() {
        decisionList.displayQuestions();
        String choice = ui.getDecisionsChoice();
        int max = ui.getDecisionValues(choice, playerProvince.returnMaximumValue(choice));
        processor.getUserDecision(choice, playerProvince, max);
    }

    /**
     * Reduces food each round depending on how many soldiers and civilians are on a team.
     */

    private void foodReduction() {
        processor.foodConsumption(playerProvince);
        for (Province province: aiProvinces) {
            processor.foodConsumption(province);
        }
    }


    /**
     * Displays the Province which are dead.
     */
    public void provinceDeath(Province province) {
        ui.displayText("*** Province is dead! ***");
        province.die();
    }

    /**
     * Displays when the User is dead and prompts the User to restart the game.
     */
    public void death() {
        ui.displayText("You have lost the game!");
        displayValues(playerProvince);
        ui.displayText("One of the values have reached zero :( :skull:");
        System.exit(0);
    }

    /** Multi-tasked Function for Starting a Battle:
     *                     i) Displays the list of Provinces that can battle (Are still Alive).
     *                     ii) Prompts the User to choose their opponent
     *                     iii) Then, proceeds to start the battle between the user and its chosen province
     *                     iv) Displays the winner
     */
    public void battle_option() {
        boolean battle = ui.beginBattle();

        List<String> provinces = new ArrayList<>();

        if (battle) {
            for (Province province: aiProvinces){
                if (province.getStatus()){
                    provinces.add(province.getAiProvinceName());
                }
            }
            String enemy = ui.selectOpponent(provinces);
            for (Province province: aiProvinces){
                if (province.getAiProvinceName().equals(enemy)){
                    String winner = battleGenerator.startsBattle(playerProvince, province);
                    ui.displayText("\nThe Winner of the battle is " + winner + "\n");
                }
            }
        }
    }

    /**
     * Displays the Summary of each Round
     */
    private void summaryOfStates(){
        int counter = 0;
        for (Province p: origProvince.setListOfMementoProvinces(ctProvince.getMementoProvinceList())){
            ui.displayText("------------------------------");
            ui.displayText("Round: " + counter);
            ui.displayText("------------------------------");
            printAttributes(p);
            counter += 1;
        }
    }

    /**
     * Takes in a save list, and file path, then uses the saveLoad function to save the current gameState to save.ser
     *
     * @param list the given array list to serialize
     * @param filePathSave the file path of the save.ser file
     */
    private void savePoint(ArrayList<Object> list, String filePathSave) throws IOException {
        // Creates a save file for the current GameState
        ui.displayText("*******************************");
        ui.displayText("Saving Game...");
        GameState gs = new GameState();
        gs.setSaveState(list);
        SaveLoad.saveGame(filePathSave, gs);
        ui.displayText("Game State Saved");
        ui.displayText("*******************************");
        ui.displayText("\n");
    }

    /**
     * Creates an Arraylist of all attributes of the current
     * gameState by adding the user's name, then adding
     * each of the attributes of each province
     *
     * @param userName the user's name of the current gameState
     */
    public ArrayList<Object> createSaveList(String userName){
        ArrayList<Province> lstOfProvince = aiProvinces;
        ArrayList<Object> newList = new ArrayList<>();
        newList.add(userName);
        // Adding the playerPlayer's attributes
        newList.add(playerProvince.getUserProvinceName());
        newList.add(playerProvince.getProvinceCivilians());
        newList.add(playerProvince.getProvinceGold());
        newList.add(playerProvince.getProvinceSoldiers());
        newList.add(playerProvince.getProvinceSoldiers());

        // Adding the Ai Province's Attributes
        for (Province p: lstOfProvince){
            if (p.getStatus()) {
                newList.add(p.getAiProvinceName());
                newList.add(p.getProvinceCivilians());
                newList.add(p.getProvinceGold());
                newList.add(p.getProvinceSoldiers());
                newList.add(p.getProvinceGold());
            } else {
                // This province is dead, therefore we are making all values zero
                newList.add(p.getAiProvinceName());
                for(int i = 0; i<=3; i++){
                    newList.add(0);
                }

            }

        }
        return newList;
    }


    /**
     * Takes in an ArrayList, then uses loadGame to load contents of save.ser
     *
     * @param filePathLoad the file path of save.ser file
     */
    private ArrayList<Object> loadPoint(String filePathLoad) throws IOException {
        // Loads the save.ser file for the current GameState
        ui.displayText("*******************************");
        ui.displayText("Loading Game State...");
        ui.displayText("*******************************");
        ui.displayText("\n");
        return SaveLoad.loadGame(filePathLoad).getSaveState();
    }

    /**
     * Saves the Snapshot of the current state using the Memento Design Pattern
     *
     * @param p province we want to clone for memento
     */
    private void stateSnapshot(Province p) {
        try {Province copyProvince = (Province)p.clone();
            origProvince.setProvince(copyProvince);
            MementoProvince mp = origProvince.createMementoProvinces();
            ctProvince.addMementoProvince(mp); } catch (CloneNotSupportedException c){
            ui.displayText("The Object is not Cloneable");
        }
    }

    /**
     * prints all attributes for the given province
     * @param province the province we want to print
     */
    private void printAttributes(Province province) {
        ui.displayText("===============================");
        if ((province.getUserProvinceName() != null)) {
            ui.displayText("Values for province: " + province.getUserProvinceName());
        } else {
            ui.displayText("Values for province: " + province.getAiProvinceName());
        }
        ui.displayText("Civilian value: " + province.getProvinceCivilians());
        ui.displayText("Gold value: " + province.getProvinceGold());
        ui.displayText("Soldier value: " + province.getProvinceSoldiers());
        ui.displayText("Food value: " + province.getProvinceFood());
        ui.displayText("===============================");
    }
}
