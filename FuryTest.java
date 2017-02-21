import java.util.*;

public class FuryTest {

    static Place[][] arena1 = new Place[3][3];
    static ArrayList<Item> furyItems = new ArrayList<Item>();
    static Hero p1 = new Hero("Kung fury", "The baddest boy", 20.0, 150.0);
    static String move = "";
    static Scanner scan = new Scanner(System.in);
    static int checkLength = 3;

    static String help = "Commands:\n-North\n-South\n-East\n-West\n-Inv\n-Look\n-Fight\n-Health\n-Info";

    public static void main(String[] args) {

        //Place[][] arena2 = new Place[2][2];

        arena1[0][0] = new Place("the home of Hackerman", "Pardon the cheeto dust.");
        arena1[0][1] = new Place("Hackerman's neighboorhood","Hackerman must be nearby.");
        arena1[0][2] = new Place("Hackerman's neighbor", "Say you're sorry for barging in.");
        arena1[1][0] = new Place("Oak Ridge High School", "Softly, in the distance you hear the cry of children.");
        arena1[1][1] = new Place("the Sheriffs office", "The Führer destroyed the place.");
        //Need to add a heal command
        arena1[1][2] = new Place("the hospital", "Heal up");
        arena1[2][0] = new Place("he home of the president", "Trump");
        arena1[2][1] = new Place("your house", "A lovely little place");
        arena1[2][2] = new Place("the arcade", "It's full of stuff.");
        
        System.out.print("\f");
        boolean isAlive = true;
        p1.setCoords(2, 1);
        
        //Must be a square
        p1.updateMax(arena1.length, arena1[0].length);

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

            System.out.println("You are in " + arena1[p1.getX()][p1.getY()].toString() + "\n");
            System.out.println("Where do you want to go?");
            move = scan.next();
            filterAll(move);
            if(hasEnemy()) {
                //Stopped here? I don't remember.
                System.out.println("You cannot move there are enemies nearby.");
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
                printItems();
            }
            else if(input.substring(0, 3).equalsIgnoreCase("fig")) {
                //Stopped here
                printPeople1();
            }
            else if(input.substring(0, 3).equalsIgnoreCase("hea")) {
                System.out.println("You have: " + p1.getHealth() + " health.");
            }
            else if(input.substring(0, 3).equalsIgnoreCase("inf")) {
                System.out.println(getInfo());
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

    public static void printItems() {
        if(furyItems.size() == 0) {
            System.out.println("You don't have any items.");
        } else {

            String invList = "Your inventory is: ";
            for(int i = 0; i < furyItems.size(); i++){
                invList += "-" + furyItems.get(i).toString() + "\n";
            }

        }
    }

    public static void printPeople1() {
        ArrayList<Person> inRoom = arena1[p1.getX()][p1.getY()].getPeople();

        if(inRoom.size() == 0) {
            System.out.println("There is no one here.");
        } else {

            String peopleInRoom = "People here: ";
            for(int i = 0; i < inRoom.size(); i++) {
                //Check to see if instance of enemy this needs to be revised.
                peopleInRoom += inRoom.get(i).toString();
            }

        }
    }

    public boolean hasEnemy() {
        ArrayList<Person> enemies = arena1[p1.getX()][p1.getY()].getPeople();
        for(int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i) instanceof Enemy) {
                return true;
            }
        }
        return false;
    }
    
    public static String getInfo() {
        String totalInfo = "";
        //This breaks every other time for because p1 doesn't get initialized first?
        totalInfo += "You are in " + arena1[p1.getX()][p1.getY()].toString() + "\n";
        totalInfo += "You have " + p1.getHealth() + " health\n";
        totalInfo += "You have " + furyItems.size() + " items in your inventory.\n";
        return totalInfo;
    }

}