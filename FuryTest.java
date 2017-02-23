import java.util.*;

public class FuryTest {

    static Place[][] arena1 = new Place[3][3];
    static ArrayList<Item> furyItems = new ArrayList<Item>();
    static Hero p1 = new Hero("Kung fury", "The baddest boy", 20.0, 150.0);
    static String move = "";
    static boolean canMove = true;
    static Scanner scan = new Scanner(System.in);
    static int checkLength = 3;
    static String noFight = "Sorry, you can't move while there are enemies in the room!";
    static boolean firstCheck = true;

    static String help = "Commands:\n-North\n-South\n-East\n-West\n-Inv\n-Look\n-Fight\n-Health\n-Info";

    public static void main(String[] args) {

        //Place[][] arena2 = new Place[2][2];

        ArrayList<Item> sheriffItems = new ArrayList<Item>();
        ArrayList<Person> sheriffPeople = new ArrayList<Person>();
        Person sMike = new Enemy("Mike", "A friend of the Führer", 15.0, 50.0);
        sheriffPeople.add(sMike);

        arena1[0][0] = new Place("the home of Hackerman", "Pardon the cheeto dust.");
        arena1[0][1] = new Place("Hackerman's neighboorhood","Hackerman must be nearby.");
        arena1[0][2] = new Place("Hackerman's neighbor", "Say you're sorry for barging in.");
        arena1[1][0] = new Place("Oak Ridge High School", "Softly, in the distance you hear the cry of children.");
        arena1[1][1] = new Place("the Sheriffs office", "The Führer destroyed the place.", sheriffItems, sheriffPeople);
        //Need to add a heal command
        arena1[1][2] = new Place("the hospital", "Heal up");
        arena1[2][0] = new Place("he home of the president", "Trump");
        arena1[2][1] = new Place("your house", "A lovely little place");
        arena1[2][2] = new Place("the arcade", "It's full of stuff.");

        System.out.print("\f");
        boolean isAlive = true;
        p1.setCoords(2, 1);

        //Must be a square
        p1.updateMax(arena1.length-1, arena1[2].length-1);

        String titleScreen = "Kung Fury: The video game";
        System.out.print("\f");

        System.out.println(titleScreen + "\n\n");

        System.out.println("Hello Kung Fury, the best cop in the world! You're our\n" + 
            "only hope at defeating the Kung Führer. You must hack your way\n" + 
            "back in time to Nazi Germany to defeat him!\n\n" + 
            "To do this you must find hackerman so he can hack you back in time\nYou'll" +
            " know what to do when you get there.\n\nYou can type \"help\" at any time to see a list of moves.");

        while(isAlive) {

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
            
            if(p1.checkIfDead())
                isAlive = false;
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
                //Stopped here
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

    //The fight method is not currently working.
    
    public static void fight() {
        //Edited this to work with place
        Enemy inRoom = ((Enemy)getArena().enemyInRoom());

        if(inRoom.getName() == null) {
            System.out.println("There is no one here to fight.");
        } else {
            System.out.println("What are you going to use?\n");
            
            System.out.println(p1.getWepNames());

            boolean moveValid = false;
            move = scan.next();

            for(int i = 0; i < wepNames.size(); i++) {

                if(move.equalsIgnoreCase(wepNames.get(i).getName())) {
                    //Here is where I stopped.
                    moveValid = true;
                    System.out.println(wepNames.get(i).getName());
                }

            }

            if(!moveValid) {
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

    //Need look method

    public static String getInfo() {
        String totalInfo = "";
        //This breaks every other time for because p1 doesn't get initialized first?
        totalInfo += "You are in " + arena1[p1.getX()][p1.getY()].toString() + "\n";
        totalInfo += "You have " + p1.getHealth() + " health\n";
        totalInfo += "You have " + furyItems.size() + " items in your inventory.\n";
        return totalInfo;
    }

    public void enemyAttackPlayer() {
        //This doesn't relate but I want to fix the method that calls the weapons you can use to fight and gives back all their stats.
        Person guy = getArena().enemyInRoom();
        p1.hurt(guy.getStrength());
        System.out.println("You were attacked by " + guy.getName());
        System.out.println("Your health is now: " + p1.getHealth());
    }

}