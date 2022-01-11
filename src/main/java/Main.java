package main.java;
import main.java.game_manager.GameEngine;
import java.io.IOException;

/**
 *  This file contains the implementation for the Main Class.
 *  Responsibility: This file is responsible for running the program
 *  and calls on the Game Engine to start the game.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        GameEngine engine = new GameEngine();
        engine.loopGame();
    }
}
