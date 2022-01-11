
import  main.java.game_save.SaveLoad;
import  main.java.game_save.GameState;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.junit.*;

/**
 *
 * This file tests the gameState package and makes sure that all methods are working.
 *
 */

public class GameStateTest {
    /**
     * Added a tester to see if the creation of the 4 Ai Provinces is as expected
     * Checks to see if each Ai Province in the array list of AiProvinces is of type Province.
     */
    @Test
    public void testSaveLoad() throws IOException {




        ArrayList<Object> list = new ArrayList<>();
        list.add("test");
        list.add("test2");
        list.add("test3");

        GameState gs = new GameState();
        gs.setSaveState(list);


        Path resourceDirectory = Paths.get("src");
        String filePath = resourceDirectory.toFile().getAbsolutePath();

        if (filePath.endsWith("/") || filePath.endsWith("\\") )
        {
            filePath = filePath + "test.ser";
        }
        else {
            filePath = filePath + "/test.ser";
        }

        SaveLoad.saveGame(filePath, gs);

        GameState gs2 = SaveLoad.loadGame(filePath);

        ArrayList<Object> newList = gs2.getSaveState();

        ArrayList<Object> newList2 = new ArrayList<>();
        newList2.add("test");
        newList2.add("test2");
        newList2.add("test3");

        assert Objects.equals(newList,newList2);

    }
    /**
     * Test to see whether the items are added properly into the
     * game-state list.
     */

    @Test
    public void gameStateSizeTest() {
        ArrayList<Object> list = new ArrayList<>();
        GameState gs = new GameState();
        gs.setSaveState(list);
        gs.addGameStateElement("First");
        gs.addGameStateElement("Second");
        gs.addGameStateElement("Third");

        assert gs.getSize()  == 3;


    }
    /**
     * Test to see whether the saving of the game states
     * are added properly into th game-state list.
     */
    @Test
    public void gameStateSaveListTest() {
        ArrayList<Object> list1 = new ArrayList<>();
        GameState gs = new GameState();
        gs.setSaveState(list1);
        gs.addGameStateElement("First");
        gs.addGameStateElement("Second");
        gs.addGameStateElement("Third");

        ArrayList<Object> list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");
        assert gs.getSaveState().equals(list);


    }

    /**
     * Test to see whether the items are added properly into the
     * game-state list and iterations are working properly.
     */
    @Test
    public void gameStateIterator() {
        ArrayList<Object> list1 = new ArrayList<>();
        GameState gs = new GameState();
        gs.setSaveState(list1);
        gs.addGameStateElement("First");
        gs.addGameStateElement("Second");
        gs.addGameStateElement("Third");

        for (Object x: gs){
            assert x != null;
        }

    }
}
