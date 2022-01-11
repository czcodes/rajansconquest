package main.java.ui;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *  This file contains the implementation for the UserInterface Class.
 *  Responsibility: This file stores for taking user inputs for the choice of event
 *  and translating them to other use Case Classes. This class also displays anything
 *  from other classes
 */

public class UserInterface {
    //This Scanner object takes the input on the next line, it will be used commonly
    final Scanner input = new Scanner(System.in);

    public String getDecisionsChoice(){
        /* Display choices and Get the choice of the player, and return the choice as a string
         *
         * This function should be called after display decisions so that the UI is clean and makes sense*/
        displayText("Please choose your choice (Enter a number, 1-3):");
        String choice = input.nextLine();
        // The following line is how many choices we provide
        return getValidChoice(choice, Arrays.asList("1","2","3"), "Please enter a valid choice (Enter a number, 1-3)");
    }

    /** Gets the value that the player wants to spend to gain a specific resource
     *
     * @param decision 1 is how much money player spends for food, 2 is how many civilians for soldiers, and 3 is
     *                 money gain for civilians.
     */
    public int getDecisionValues(String decision, int maximum){

        String choice = null;
        boolean intAsStr = true;
        boolean isValid = false;

        while (!isValid){
            // Get value that player wants
            choice = getDecisionHelper(decision);

            // Split given number into string components
            String[] checks = choice.split("");

            // Check that given string is in the numbers
            intAsStr = checkValid(checks,intAsStr);

            // check if given input is valid
            System.out.println(choice);
            if(choice.length() > 9){
                displayText("Your input is too large! Please enter a smaller number");
            }
            else if (!intAsStr){
                displayText("Your input is not valid! Enter a number:");
            }
            else if (Integer.parseInt(choice) >= maximum){
                displayText("Your maximum is "+ (maximum - 1) +"! You have passed this. Please enter a valid number");
            }

            else {
                isValid = true;
                displayText("Adjustments will be made shortly.");
                displayText("\n");
            }
        }

        return Integer.parseInt(choice);
    }
    /** Gets the value that the player wants to spend to gain a specific resource
     *
     * @param checks a list of characters to check if they are valid
     * @param intAsStr if the string is valid
     */
    public boolean checkValid(String[] checks, Boolean intAsStr){
        List<String> validChoices = Arrays.asList("1","2","3","4","5","6","7","8","9","0");

        for (String curr: checks){
            if (!validChoices.contains(curr)){
                intAsStr = false;
                break;
            }
            intAsStr = true;
        }

        return intAsStr;
    }

    /** Gets the value that the player wants to spend to gain a specific resource
     *
     * @param decision returns the player choice
     */
    public String getDecisionHelper(String decision){
        String choice;
        if (decision.equals("1")){
            displayText("How much money will you pay to gain food? Conversion rate for food is 1 gold = 2 food");
            choice = input.nextLine();
        }

        else if (decision.equals("2")){
            displayText("How many civilians will you give to gain soldiers? " +
                    "Conversion rate for soldiers is 1 civilian = 1 soldier");
            choice = input.nextLine();
        }

        else{
            displayText("How many civilians will you tax more to gain money? " +
                    "Conversion rate for money is 1 civilian = 2 gold");
            choice = input.nextLine();
        }
        return choice;
    }

    /** This even is very similar to getDecisions, but it only gets a y/n for events.

     upper or lower case is accepted */
    public String getEventChoice(){
        this.displayText("Enter Y for yes, or enter N for no:");
        String choice = input.nextLine();
        return getString(choice);
    }

    /** Gets function checks if the given input is valid for events. only accepts upper or lower case Y and N
     *
     * @param choice the string to be checked*/
    private String getString(String choice) {
        return getValidChoice(choice, Arrays.asList("Y", "N", "y", "n"), "Please enter a valid choice (Enter Y/N)");
    }


    /**
     * Helper for the getString() that returns if the string is valid or not
     * */
    private String getValidChoice(String choice, List<String> strings, String s) {
        boolean valid = strings.contains(choice);

        //this function will loop until a valid input is given
        while (!valid) {
            this.displayText(s);
            choice = input.nextLine();
            valid = strings.contains(choice);
        }
        return choice;
    }

    /**
     * This is the main output function used for UI that displays text to player.
     * @param Text the text to be displayed*/
    public void displayText(String Text){
        /*This method will display the decisions or text that is given to it*/
        System.out.println(Text);
    }

    /** This function is used at the start of the game to get data for the player. This is all user inputted data
     * and more specialized, so a function is created. */
    public List<String> startPlayer() {
        this.displayText("What is your name: "); // ask user for their name
        String name = input.nextLine();

        // choosing a name for the province that the player will play as
        this.displayText(name + ", choose a name for your province:");
        String provinceName = input.nextLine();

        this.displayText(name + ", your province name is " + provinceName);
        return Arrays.asList(name, provinceName);
    }

    /** This function is used to ask if the player wants to start a battle and returns a boolean*/
    public boolean beginBattle() {
        this.displayText("Would you like to battle? Y/N:");
        String choice = input.nextLine();

        String battleChoice = getString(choice);

        return !battleChoice.equals("n") && !battleChoice.equals("N");
    }

    /** This function is used to select an opponent. A list of valid opponents is given and the player enters a
     * number to check which opponent it will be.
     *
     * @param opponentList a list of valid opponents that the user may face*/
    public String selectOpponent(List<String> opponentList) {
        // Allows user to pick an opponent

        for (int i = 1; i <= opponentList.size(); i++){
            this.displayText(i + ". " + opponentList.get(i - 1));
        }
        this.displayText("Select a number to indicate which opponent you would like to battle:");

        String choice = input.nextLine();

        List<String> validChoices = new ArrayList<>();
        for (int i = 1; i <= opponentList.size(); i++){
            validChoices.add(String.valueOf(i));
        }

        boolean valid = validChoices.contains(choice);

        //this function will loop until a valid input is given
        while (!valid) {
            this.displayText("Please enter a valid choice:");
            choice = input.nextLine();
            valid = validChoices.contains(choice);
        }

        return opponentList.get(Integer.parseInt(choice) - 1);
    }

    /** This function is used to initialize user interface */
    public static UserInterface initializeUI() {
        return new UserInterface();
    }


    /** This following functions are used to save and load data of a game, they are very simple, and will therefore be
     * grouped together */
    public Boolean askLoad(){
        return validChoice("Would you like to load a previously saved game?(Y/N): ");
    }

    /** Ask if user wants a summary of the province attributes */
    public Boolean askSummary() {
        return validChoice("Would you like to get a summary Y/N");
    }

    /**
     * Helper function for askLoad() and askSummary() that returns the valid choices.
     */
    private Boolean validChoice(String s) {
        this.displayText(s);
        String choice = input.nextLine();
        List<String> validChoices = Arrays.asList("Y", "N", "y", "n");
        boolean valid = validChoices.contains(choice);

        //this function will loop until a valid input is given
        while (!valid) {
            this.displayText("Please enter a valid choice (Enter Y/N)");
            choice = input.nextLine();
            valid = validChoices.contains(choice);
        }
        return choice.equalsIgnoreCase("Y");
    }


    /** This function is used to get the save path of an existing save.*/
    public String getFile() {


        Path resourceDirectory = Paths.get("src");
        String filePath = resourceDirectory.toFile().getAbsolutePath();


        if (filePath.endsWith("/") || filePath.endsWith("\\") )
        {
            filePath = filePath + "save.ser";
        }
        else{
            filePath = filePath + "/save.ser";
        }

        return filePath;
    }

}