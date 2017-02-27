import java.util.*;
import java.util.concurrent.TimeUnit;

public class FuryTest {

    static Place[][] arena1 = new Place[3][3];
    static Place[][] arena2 = new Place[4][4];
    static ArrayList<Item> furyItems = new ArrayList<Item>();
    static Hero p1 = new Hero("Kung fury", "The baddest boy", 20.0, 150.0, furyItems);
    static String move = "";
    static boolean canMove = true;
    static boolean win = false;
    static Scanner scan = new Scanner(System.in);
    static int checkLength = 3;
    static String noFight = "Sorry, you can't move while there are enemies in the room!";
    static boolean firstCheck = true;

    static String help = "Commands:\n-North\n-South\n-East\n-West\n-Inv\n-Look\n-Fight\n-Health\n-Info\n-Grab";

    public static void main(String[] args) {

        Item fists = new Weapon("Fists", "Your most reliable ally", 20.0, 20.0);
        furyItems.add(fists);

        //Sheriff
        ArrayList<Item> sheriffItems = new ArrayList<Item>();
        ArrayList<Person> sheriffPeople = new ArrayList<Person>();
        Person sMike = new Enemy("Mike", "A friend of the Führer", 15.0, 50.0);
        sheriffPeople.add(sMike);

        //Hackerman's neighboorhood
        ArrayList<Item> nItems = new ArrayList<Item>();
        Item keyboard = new Weapon("Keyboard", "You can probably hurt someone with it", 5.0, 5.0);
        nItems.add(keyboard);

        //The Arcade
        ArrayList<Item> arcadeItems = new ArrayList<Item>();
        Item rollOfQuarters = new Weapon("A roll of Quarters", "Why would you even try to use this on someone?", 1.0, 1.0);
        arcadeItems.add(rollOfQuarters);

        //Oak Ridge
        ArrayList<Item> oakItems = new ArrayList<Item>();
        Item brick = new Weapon("A brick", "Not bad.", 25.0, 25.0);
        oakItems.add(brick);
        ArrayList<Person> oakPeople = new ArrayList<Person>();
        Person oakPerson = new Enemy("A student who is sagging", "That's not allowed here, you must stop him.", 10.0, 10.0);
        oakPeople.add(oakPerson);

        arena1[0][0] = new Place("the home of Hackerman", "Pardon the cheeto dust.");
        arena1[0][1] = new Place("Hackerman's neighboorhood","Hackerman must be nearby.", nItems);
        arena1[0][2] = new Place("Hackerman's neighbor", "Say you're sorry for barging in.");
        arena1[1][0] = new Place("Oak Ridge High School", "Softly, in the distance you hear the cry of children.", oakItems, oakPeople);
        arena1[1][1] = new Place("the Sheriffs office", "The Führer destroyed the place.", sheriffItems, sheriffPeople);

        arena1[1][2] = new Place("Town Center", "");
        arena1[2][0] = new Place("he home of the president", "you hear snoring going on in the background.");
        arena1[2][1] = new Place("your house", "A lovely little place");
        arena1[2][2] = new Place("the arcade", "It's full of stuff.", arcadeItems);

        System.out.print("\f");
        boolean isAlive = true;
        p1.setCoords(2, 1);

        //Must be a square
        p1.updateMax(arena1.length-1, arena1[2].length-1);

        String titleScreen = "Kung Fury: The video game";
        System.out.print("\f");

        System.out.println(titleScreen + "\n\n");

        System.out.println("Hello Kung Fury, the best cop in the world! You're our\n" + 
            "only hope at defeating the Kung Führer. He's been terrorizing the entire country\n" + 
            "The only explanation is He must have come from the past to destroy the place, you must hack" + 
            "back in time to Nazi Germany to defeat him!\n\n" + 
            "To do this you must find hackerman so he can hack you back in time\nYou'll" +

            " know what to do when you get there.\n\nYou can type \"help\" at any time to see a list of moves.");

        System.out.println("Here are your commands: " + help + "\n");

        while(isAlive && !win) {

            System.out.println(generateMap());
            System.out.println();

            System.out.println("You are in " + getArena().toString() + "\n");
            System.out.println("Where do you want to go?");
            move = scan.next();
            filterAll(move);
            while(hasEnemy()) {

                //Stopped here? I don't remember.
                firstCheck = true;
                if(firstCheck)
                    System.out.println("You run into " + getArena().enemyInRoom().toString() + "\nWhat do you do?");
                firstCheck = false;
                move = scan.next();
                while(move.equalsIgnoreCase("north") ||
                move.equalsIgnoreCase("south") ||
                move.equalsIgnoreCase("west") ||
                move.equalsIgnoreCase("east") && !canMove) {
                    System.out.println("You can't move while enemies are nearby!");
                    move = scan.next();
                }

                filterAll(move);

                if(p1.checkIfDead())
                    isAlive = false;
            }

            boolean firstTimeLand = true;

            if(p1.getX() == 0 && p1.getY() == 0 && firstTimeLand) {
                System.out.println("You have found hackerman, he can help!\n");
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("[HACKERMAN]: Thank goodness your here Kung Fury! Look I know I'm supposed to be a hacker\n" +
                    "but I need your help. I know that you have taken AP Comp Science\n" +
                    "I'm trying to hack you back in time but I don't understand the vocab, can you help me out?\n" +
                    "By the way, the better you do, the more health you can get back!");
                try {
                    Thread.sleep(5000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("[YOU]: I'll see what I can do.\n");

                System.out.println("[HACKERMAN]: ");

                System.out.println("1 - Yes\n2 - No");

                int choice = scan.nextInt();

                if(choice == 1) {
                    System.out.println("[HACKERMAN]: It looks like that was it!");
                    p1.heal(10.0);
                    System.out.println("You got 10 health back!");
                } else {
                    System.out.println("[HACKERMAN]: That didn't seem right...");
                }

                System.out.println("[HACKERMAN]: What can be accessed from any static method in a class?");

                System.out.println("1 - Local Variable\n2 - Instance Variable\n3 - Static Variable");

                int choice = scan.nextInt();

                if(choice == 1 || choice == 2) {
                    System.out.println("[HACKERMAN]: That wasn't it");

                } else {
                    System.out.println("[HACKERMAN]: You got it!");
                    p1.heal(10.0);
                    System.out.println("You got 10 health back!");
                }

                System.out.println("[HACKERMAN]: Which Shrek movie was the best?");
                System.out.println("1 - Shrek 1\n2 - Shrek 2\n3 - Shrek 3\n4 - Shrek 4\n5 - None");
                int choice = scan.nextInt();
                if(choice == 1) {
                    System.out.println("[HACKERMAN]: Ah, the classic.");
                    p1.heal(10.0);
                    System.out.println("You got 10 health back!");
                } else if(choice == 2) {
                    System.out.println("[HACKERMAN]: You're right, the sequels aren't always worse!");
                    p1.heal(10.0);
                    System.out.println("You got 10 health back!");
                } else if(choice == 3) {
                    System.out.println("[HACKERMAN]: That one is pretty good.");
                    p1.heal(10.0);
                    System.out.println("You got 10 health back!");
                } else if(choice == 4) {
                    System.out.println("[HACKERMAN]: That one is pretty good.");
                    p1.heal(10.0);
                    System.out.println("You got 10 health back!");
                } else {
                    System.out.println("They're all good what are you talking about?");
                }
                
                System.out.println("[HACKERMAN]: It works! I'm ready to hack you back in time!");
                
                System.out.println("You are about to hack back in time, continue? [y/n]");
                String hackChoice = scan.next();
                
                if(hackChoice.charAt(0) == 'y') {
                    
                } else {
                    System.out.println("Too bad, you're going back in time.");
                }
            }

            if(p1.checkIfDead())
                isAlive = false;
        }

        if(win) {
            System.out.println("Congratulations!!!");
        }
    }

    public static String generateMap() {
        String map = "";

        for(int i = 0; i < arena1.length; i++) {

            for(int j = 0; j < arena1[i].length; j++) {

                if(i == p1.getX() && j == p1.getY()) {
                    map += "? ";
                } else {
                    map += "X ";
                }

            }

            map +="\n";
        }
        map += "You are here: \"?\"";
        return map;
    }

    
    
    public static void changeLoc(String input) {

        if(input.substring(0, 3).equalsIgnoreCase("nor")) {
            p1.moveNorth();
        }
        else if(input.substring(0, 3).equalsIgnoreCase("sou")) {
            p1.moveSouth();
        }
        else if(input.substring(0, 3).equalsIgnoreCase("wes")) {
            p1.moveWest();
        }
        else if(input.substring(0, 3).equalsIgnoreCase("eas")) {
            p1.moveEast();
        }
        else {
            //Now useless, saving for cleanup.
            System.out.println("Where do you want to go again?");
            move = scan.next();
            changeLoc(move);
        }
    }

    public static boolean askHelp(String input) {
        if(input.substring(0, 3).equalsIgnoreCase("hel")) {
            return true;
        }
        return false;
    }

    public static Place getArena() {
        return arena1[p1.getX()][p1.getY()];
    }

    public static void filterAll(String input) {
        if(input.length() >= checkLength) {
            if(askHelp(input)) {
                System.out.println(help);
            }
            else if(
            (input.substring(0, 3).equalsIgnoreCase("nor")) ||
            (input.substring(0, 3).equalsIgnoreCase("sou")) ||
            (input.substring(0, 3).equalsIgnoreCase("eas")) ||
            (input.substring(0, 3).equalsIgnoreCase("wes"))
            ) {
                changeLoc(input);
            }

            else if(input.substring(0,3).equalsIgnoreCase("inv")) {
                arena1[p1.getX()][p1.getY()].printItems();
            }
            else if(input.substring(0, 3).equalsIgnoreCase("fig")) {
                fight();
            }
            else if(input.substring(0, 3).equalsIgnoreCase("hea")) {
                System.out.println("You have: " + p1.getHealth() + " health.");
            }
            else if(input.substring(0, 3).equalsIgnoreCase("inf")) {
                System.out.println(getInfo());
            }
            else if(input.substring(0, 3).equalsIgnoreCase("loo")) {
                System.out.println(getArena().look());
            }
            else if(input.substring(0, 3).equalsIgnoreCase("gra")) {
                pick();
            }
            else {
                System.out.println("What do you want to do?");
                move = scan.next();
                filterAll(move); 
            }
            //more else

        } else {
            System.out.println("Can you enter the command again?");
            move = scan.next();
            filterAll(move);
        }
    }

    public static void fight() {
        //Edited this to work with place
        Person inRoom = getArena().enemyInRoom();

        if(inRoom.getName() == null) {
            System.out.println("There is no one here to fight.");
        } else {
            System.out.println("What are you going to use?\n");

            System.out.println(p1.printWepNames());

            boolean moveValid = false;
            int itemMove = scan.nextInt();

            ArrayList<Weapon> totalWeps = p1.getWeapons();
            System.out.println(totalWeps.size());

            for(int i = 0; i < totalWeps.size(); i++) {
                System.out.println(totalWeps.get(i));
                if(itemMove == i+1) {
                    System.out.println("Attack Successful.");
                    System.out.println();
                    moveValid = true;
                    playerAttackEnemy(totalWeps.get(i));
                    enemyAttackPlayer();
                }
            }

            if(moveValid == false) {
                fight();
            }

        }
    }

    public static boolean hasEnemy() {
        ArrayList<Person> enemies = getArena().getPeople();
        for(int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i) instanceof Enemy) {
                canMove = false;
                return true;
            }
        }
        canMove = true;
        return false;
    }

    public static void pick() {
        if(getArena().getItems().size() != 0) {
            pickUpItem(getArena().giveItem());
            System.out.println("You picked up: " + getArena().giveItem().toString());
            getArena().removeItem();
        }
        else {
            System.out.println("There are no items to pick up!");
        }
    }

    public static void pickUpItem(Item item) {
        p1.addItem(item);
    }

    public static String getInfo() {
        String totalInfo = "";
        //This breaks every other time for because p1 doesn't get initialized first?
        totalInfo += "You are in " + arena1[p1.getX()][p1.getY()].toString() + "\n";
        totalInfo += "You have " + p1.getHealth() + " health\n";
        totalInfo += "You have " + furyItems.size() + " items in your inventory.\n";
        return totalInfo;
    }

    public static void enemyAttackPlayer() {
        //This doesn't relate but I want to fix the method that calls the weapons you can use to fight and gives back all their stats.
        Person guy = getArena().enemyInRoom();
        p1.hurt(guy.getStrength());
        System.out.println("You were attacked by " + guy.getName());
        System.out.println("Your health is now: " + p1.getHealth());
        System.out.println();
    }

    public static void enemyAttackPlayer(Item item) {
        Person guy = getArena().enemyInRoom();
        p1.hurt(guy.getStrength());
        System.out.println("You were attacked by " + guy.getName());
        System.out.println("Your health is now: " + p1.getHealth());
    }

    //     public static void playerAttackEnemy() {
    // 
    //         Person guy = getArena().enemyInRoom();
    //         guy.hurt(p1.getStrength());
    //         System.out.println("You were attacked by " + guy.getName());
    //         System.out.println("Your health is now: " + p1.getHealth());
    //         System.out.println(guy.getName() + "\'s health is now: " + guy.getHealth());
    //     }

    public static void playerAttackEnemy(Weapon weapon) {
        //This doesn't relate but I want to fix the method that calls the weapons you can use to fight and gives back all their stats.
        Person guy = getArena().enemyInRoom();
        guy.hurt(weapon.getAttackStrength());
        if(guy.getHealth() == 0) {
            System.out.println(guy.getName() + " is dead!");
            getArena().removePerson();
        } else {
            System.out.println("You were attacked by " + guy.getName());
            System.out.println("Your health is now: " + p1.getHealth());
            System.out.println(guy.getName() + "\'s health is now: " + guy.getHealth());
            System.out.println();
        }
    }

}