package main.java.usecases;

import main.java.province_construction.Province;

/**
 *  This file contains the implementation for the Battle Class.
 *  Responsibility: This file is to start the Battle between
 *  the two given
 */

public class Battle {
    // Instance Variable
    private final ProcessValues processValues;

    // Constructor to create a new instance of the Process Values
    public Battle() {
        this.processValues = new ProcessValues();
    }

    /**
     * Initiates a battle between two provinces object and then begins the battle
     * between the two provinces through ratios and returns the winner of the battle.
     * @param userProvince represents the Object for the Province.
     * @param aiProvince represents the second Object for the Province.
     */
    public String startsBattle(Province userProvince, Province aiProvince) {

        int player1_SoldierCount = processValues.getBattleSoldiers(userProvince);
        int player2_SoldierCount = processValues.getBattleSoldiers(aiProvince);
        // There will be a total of three rounds
        int battleRounds = 2;
        //using process values
        while (battleRounds >= 0) {
            // 1. Get the ratios for both the provinces
            // 2. Compare the ratios in each case: the higher ratios wins that round,
            // and causes the other province to lose number of soldiers.
            // 3. Update each soldier counts
            float player1_Ratio = 5 * (float) player1_SoldierCount / player2_SoldierCount;
            float player2_Ratio = 5 * (float) player2_SoldierCount / player1_SoldierCount;



            if (player1_Ratio == player2_Ratio) {
                int deaths = (int) Math.round(Math.random() * (player2_Ratio + player1_Ratio)) * 10;

                player1_SoldierCount -= deaths;
                player2_SoldierCount -= deaths;

                processValues.battleRoundProcess(userProvince, player1_SoldierCount);
                processValues.battleRoundProcess(aiProvince, player2_SoldierCount);

            } else if (player1_Ratio > player2_Ratio) {
                float deaths = (float) Math.random() * player2_Ratio * 10;
                player2_SoldierCount -= deaths;

                processValues.battleRoundProcess(userProvince, player1_SoldierCount);
                processValues.battleRoundProcess(aiProvince, player2_SoldierCount);
            } else {
                float deaths = (float) Math.random() * player1_Ratio * 10;
                player1_SoldierCount -= deaths;

                processValues.battleRoundProcess(userProvince, player1_SoldierCount);
                processValues.battleRoundProcess(aiProvince, player2_SoldierCount);
            }
            battleRounds -= 1;

        }
        // determining winner of the battle based on which province has
        // more soldiers by the end of the 3 rounds
        if (player1_SoldierCount > player2_SoldierCount){
            return processValues.battleWinner(userProvince);
        }
        return processValues.battleWinner(aiProvince);
    }
}