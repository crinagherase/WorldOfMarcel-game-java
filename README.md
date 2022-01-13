# WorldOfMarcel-game-java
Text adventure game based on a matrix where each cell may contain enemies, stores etc; the player can choose between characters and level up.
Implementation: 
Method run from class Game (created with the use of Singleton design pattern) reads the input from two JSON files and stores the story of each cell in a hash map that uses the type of cells as keys. The game class also contains a list of accounts (implemented class encapsulating Information class and Credentials class;  created with the use of Builder design pattern). The accounts, informations, credentials, characters, levels and experiences are read from the second input file.
Class Grid represents the game map and is an array of array lists of cells (class). There are: a method for creating a map with minimum 4 enemies and 3 shops, that accepts input for length and width and 4 methods for method for moving through the grid.
Warrior, mage and rogue extend class Character that implements Entity. They each have different abilities, space in their inventory, etc. This classes define two important methods get/receive damage. They have a special mathematic formula that calculates the damage and also a random possibility to not take damage (that depends on different factors).
Class inventory keeps the money and potions (health and mana).
To test all classes I implemented a class test, that contains the main method. Using a scanner, a user can actually play the game from terminal. Everything that the player needs to know appears there.
