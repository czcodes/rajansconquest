# Rajan's Conquest

#### Video Demo: https://user-images.githubusercontent.com/83887991/149045008-c007a0a0-c444-4622-b72c-7a6413777639.mp4

## Description
Rajan's Conquest is a text-based adventure game based in ancient India. The player joins one of five provinces in the nation, 
and is tasked with conquering all the other provinces. Each province receives strategic options each game to strengthen
their province, and is given the opportunity to battle other provinces to conquer them as well.

 Provinces are conquered when they run out of any resource. 
 If the player conquers all five provinces before the game ends, they win. Otherwise, the province with the most amount 
 of land conquered will win the game. Alternatively, if they run out of any resource, they lose.

## Instructions To Run: 

First, you will first be prompted if you would like to load a previous save (Input: Y/N) <br />

- In a new game state: [Selecting N for the first tbe Previous Load Game State] <br />
    &nbsp; Then you will be prompted to type your name <br />
    &nbsp; After you type your name, you will then be able to type the Province name <br />
    &nbsp; As you play the game, the gameState will automatically save <br />
    <br />
    
- If you load a game state: [Selecting Y for the first tbe Previous Load Game State] <br />
    &nbsp; The program will automatically load a save.ser in the projects src file <br />
    &nbsp; After loading the file, the console should welcome you back to the game <br />
    <br />

After the game is saved or loaded, you will now be able to play the game.
The objective of the game is for your province is to keep all resource values above zero while trying to defeat the
other provinces by reducing their values to zero.

Each round you can either encounter a random story event where the input is Y/N 
or a decision event, where you choose from 3 options listed where the input is 1,2, or 3.
During the turns, the user can generate a summary of the States that happened which 
basically displays the attributes for their province. 
  
