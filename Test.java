import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void printMap(int length, int width, ArrayList<ArrayList<Cell>> map){
        for(int i = 0; i < length; i++){
            for (int j = 0; j < width; j++) {
                if(map.get(i).get(j).myCell == Cell.cellType.ENEMY ) {
                    if(map.get(i).get(j).visited == 1 || map.get(i).get(j).visited == 3){
                        if(map.get(i).get(j).visited == 1)
                            System.out.print("E ");
                        else
                            System.out.print("e ");
                    }
                    else{
                        System.out.print("* ");
                    }
                }
                if(map.get(i).get(j).myCell == Cell.cellType.EMPTY ) {
                    if(map.get(i).get(j).visited == 1 || map.get(i).get(j).visited == 3) {
                        if(map.get(i).get(j).visited == 1)
                            System.out.print("N ");
                        else
                            System.out.print("n ");
                    }
                    else{
                        System.out.print("* ");
                    }
                }
                if(map.get(i).get(j).myCell == Cell.cellType.SHOP ) {
                    if(map.get(i).get(j).visited == 1 || map.get(i).get(j).visited == 3) {
                        if(map.get(i).get(j).visited == 1)
                            System.out.print("S ");
                        else
                            System.out.print("s ");
                    }
                    else{
                        System.out.print("* ");
                    }
                }
                if(map.get(i).get(j).myCell == Cell.cellType.FINISH ) {
                    if(map.get(i).get(j).visited == 1 || map.get(i).get(j).visited == 3) {
                        if(map.get(i).get(j).visited == 1)
                            System.out.print("F ");
                        else
                            System.out.print("f ");
                    }
                    else{
                        System.out.print("* ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.run();
        Grid g = Grid.getInstance();
        //generate map
        //the length and the width can be changed
        ArrayList<ArrayList<Cell>> map = Grid.mapGenerator(5,6);
        Scanner sc= new Scanner(System.in);
        System.out.println("Welcome to the game! Please enter 1 for terminal or 2 for GUI");
        int a = sc.nextInt();
        try {
            System.out.println("Great! Now choose an account. (Number from 1 to 8)");
            int accountNumber = sc.nextInt() - 1;
            System.out.println("Awesome choice! With what character would you like to play? (Number from 1 to 3)");
            int characterNumber = sc.nextInt() - 1;
            sc.nextLine();
            System.out.println("You have chosen " + game.accounts.get(accountNumber).characters.get(characterNumber).characterName);
            System.out.println("Stats: ");
            System.out.println("Profession: " + game.accounts.get(accountNumber).characters.get(characterNumber).getClass().getSimpleName());
            System.out.println("Level: " + game.accounts.get(accountNumber).characters.get(characterNumber).level);
            System.out.println("Experience: " + game.accounts.get(accountNumber).characters.get(characterNumber).experience);
        printMap(5,6,map);
        g.currentCell = map.get(0).get(0);
        Inventory inventory = new Inventory();
        //the game runs unless the character reached the finish cell, or it died
        while(g.currentCell.myCell != Cell.cellType.FINISH && game.accounts.get(accountNumber).characters
                .get(characterNumber).currentLife > 0) {
            System.out.println("Where would you like to go?(N/ S/ E/ W)");
            String direction = sc.nextLine();
            if (direction.compareTo("S") == 0)
                g.goSouth(5, 6, map);
            else if (direction.compareTo("N") == 0)
                g.goNorth(5, 6, map);
            else if (direction.compareTo("W") == 0)
                g.goWest(5, 6, map);
            else if (direction.compareTo("E") == 0)
                g.goEast(5, 6, map);
            else throw new InvalidCommandException("Wrong command");
            printMap(5, 6, map);
            //random if the character finds coins or not
            Random r = new Random();
            int lu = r.nextInt(5);
            if(lu == 1 && g.currentCell.visited != 1){
                Random coin = new Random();
                int coins = coin.nextInt(10);
                inventory.money = inventory.money + coins;
                System.out.println("You received " + coins + "$");
            }
            //series of if to see on what type of cell is the character on
            if(g.currentCell.myCell ==  Cell.cellType.EMPTY){
                //display story only if it has not been visited
                if(g.currentCell.visited != 1){
                    System.out.println(game.storiesMap.get(Cell.cellType.EMPTY).get(0));
                    game.storiesMap.get(Cell.cellType.EMPTY).remove(0);
                }
                g.currentCell.visited = 1;
            }
            if(g.currentCell.myCell ==  Cell.cellType.SHOP){
                g.currentCell.visited = 1;
                Shop shop = new Shop();
                System.out.println(game.storiesMap.get(Cell.cellType.SHOP).get(0));
                game.storiesMap.get(Cell.cellType.SHOP).remove(0);
                System.out.print("1." + shop.potions.get(0).getClass().getName());
                System.out.println(" price: " + shop.potions.get(0).price() + " weight: " + shop.potions.get(0).weight());
                System.out.print("2." + shop.potions.get(1).getClass().getName());
                System.out.println(" price: " + shop.potions.get(1).price() + " weight: " + shop.potions.get(1).weight());
                System.out.print("3." + shop.potions.get(2).getClass().getName());
                System.out.println(" price: " + shop.potions.get(2).price() + " weight: " + shop.potions.get(2).weight());
                System.out.println("You have: " + inventory.money + "$ and " + inventory.spaceLeft() + " space left");
                System.out.println("Would you like to buy something? (Y/N)");
                String answer = sc.nextLine();
                if(answer.compareTo("Y") == 0)
                {
                    System.out.println("What potion would you like to buy? (1/2/3)");
                    int c = sc.nextInt();
                    sc.nextLine();
                    Potion p = shop.deletePotion(c-1);
                    inventory.addPotion(p);
                    System.out.println("Done! :) ");
                }
                else if(answer.compareTo("N") != 0)
                    throw new InvalidCommandException("You should have entered Y or N");
            }
            if(g.currentCell.myCell ==  Cell.cellType.FINISH){
                g.currentCell.visited = 1;
                System.out.println(game.storiesMap.get(Cell.cellType.FINISH).get(0));
                game.storiesMap.get(Cell.cellType.FINISH).remove(0);
                System.out.println("You won! Congratulations!!");
            }
            if(g.currentCell.myCell ==  Cell.cellType.ENEMY && g.currentCell.visited != 1){
                g.currentCell.visited = 1;
                System.out.println(game.storiesMap.get(Cell.cellType.ENEMY).get(0));
                game.storiesMap.get(Cell.cellType.ENEMY).remove(0);
                Enemy enemy = new Enemy();
                while(enemy.currentLife > 0 && game.accounts.get(accountNumber).characters
                        .get(characterNumber).currentLife > 0) {
                    System.out.println("Your enemy has: " + enemy.currentLife + "(life)");
                    //the character's turn
                    System.out.println("What do you want to do? :0");
                    System.out.println("1.Attack enemy\n2.Use ability\n3.Use potion");
                    int choice = sc.nextInt();
                    if (choice != 1 && choice != 2 && choice != 3)
                        throw new InvalidCommandException("You should have entered a number from 1 to 3");
                    //if it chose to attack normally
                    if (choice == 1) {
                        int d = game.accounts.get(accountNumber).characters.get(characterNumber)
                                .getDamage();
                        enemy.receiveDamage(d);
                        if(enemy.currentLife <= 0){
                            System.out.println("!Enemy defeated!");
                            game.accounts.get(accountNumber).characters.get(characterNumber).plusExperience();
                        }
                    }
                    //if it chose to use ability
                    if (choice == 2) {
                        System.out.println("What ability do you want to use");
                        System.out.println("1.Ice\n2.Fire\n3.Earth");
                        int ability = sc.nextInt();
                        if (ability != 1 && ability != 2 && ability != 3)
                            throw new InvalidCommandException("You should have entered a number from 1 to 3");
                        if(ability == 1){
                            Ice ice = new Ice();
                            enemy.acceptVisitor(ice);}
                        if(ability == 2){
                            Fire ice = new Fire();
                            enemy.acceptVisitor(ice);}
                        if(ability == 3){
                            Earth ice = new Earth();
                            enemy.acceptVisitor(ice);}
                        if(enemy.currentLife <= 0) {
                            System.out.println("!Enemy defeated!");
                            Random rr = new Random();
                            int luc = rr.nextInt(5);
                            if(lu != 1){
                                Random coin = new Random();
                                int coins = coin.nextInt(10);
                                inventory.money = inventory.money + coins;
                                System.out.println("You received " + coins + "$ for defeating the monster");
                            }
                        }
                    }
                    //if it chose to use potion
                    if (choice == 3) {
                        System.out.println("Which potion would you like to use? You currently have " +
                                inventory.potions.size());
                        for(int j = 0;j < inventory.potions.size(); j++){
                            System.out.println(inventory.potions.get(j).getClass().getSimpleName());}
                        System.out.println("Enter 1 for Health potion or 2 for Mana potion" );
                        int abc = sc.nextInt();
                        if (abc != 1 && abc != 2)
                            throw new InvalidCommandException("You should have entered a number 1 or 2");
                        for(int i = 0;i < inventory.potions.size(); i++) {
                            if(inventory.potions.get(i) instanceof HealthPotion && abc == 1){
                                game.accounts.get(accountNumber).characters.get(characterNumber)
                                    .regenerateLife(((HealthPotion) inventory.potions.get(i)).regenerationValue);
                                System.out.println("Your life was regenerated to " + game.accounts.get(accountNumber)
                                        .characters.get(characterNumber).currentLife);
                            }
                            else{
                                game.accounts.get(accountNumber).characters.get(characterNumber)
                                    .regenerateMana(((ManaPotion) inventory.potions.get(i)).regenerationValue);
                            }
                        }
                    }
                    //the enemy's turn
                    Random random = new Random();
                    int luck = random.nextInt(2);
                    int k = 1;
                    //ability
                    if( enemy.abilities.size() == 0 )
                        k = 0;
                    if (luck == 1 && enemy.abilities.size() > 0 && enemy.currentLife > 0) {
                        if(enemy.abilities.get(0).compareTo("Ice") == 0){
                            Ice ice = new Ice();
                            if(ice.manaCost > enemy.currentMana)
                                k = 0;
                            game.accounts.get(accountNumber).characters.get(characterNumber).acceptVisitor(ice);}
                        if(enemy.abilities.get(0).compareTo("Fire") == 0){
                            Fire ice = new Fire();
                            if(ice.manaCost > enemy.currentMana)
                                k = 0;
                            game.accounts.get(accountNumber).characters.get(characterNumber).acceptVisitor(ice);}
                        if(enemy.abilities.get(0).compareTo("Earth") == 0){
                            Earth ice = new Earth();
                            if(ice.manaCost > enemy.currentMana)
                                k = 0;
                            game.accounts.get(accountNumber).characters.get(characterNumber).acceptVisitor(ice);}
                        if(k == 0)
                            System.out.println("He tried to used an ability but failed");
                        else{
                            System.out.println("He used an ability! You have: " + game.accounts.get(accountNumber)
                                    .characters.get(characterNumber).currentLife + "(life)");
                            enemy.abilities.remove(0);
                        }
                    }
                    //normal attack
                    if ((luck == 0 || k == 0) && enemy.currentLife > 0) {
                        int ed = enemy.getDamage();
                        game.accounts.get(accountNumber).characters.get(characterNumber)
                                .receiveDamage(ed);
                        System.out.println("He attacked! You have: " + game.accounts.get(accountNumber)
                                .characters.get(characterNumber).currentLife + "(life)");
                    }
                    sc.nextLine();
                }
            }
        }
        if(game.accounts.get(accountNumber).characters.get(characterNumber).currentLife < 0)
            System.out.println("Your game life is OVER");}
        catch(IndexOutOfBoundsException e){
            System.out.println("You entered a number that does not represent an account/character");
            //e.printStackTrace();
        }
        catch(InvalidCommandException e){
            e.printStackTrace();
        }
        catch(InputMismatchException e){
            System.out.println("You did not enter a number");
        }
    }
}
