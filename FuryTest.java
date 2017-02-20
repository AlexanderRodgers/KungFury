import java.util.*;

public class FuryTest {

    static Place[][] arena1 = new Place[2][2];
    static ArrayList<Item> furyItems = new ArrayList<Item>();
    static Hero p1 = new Hero("Kung fury", "The baddest boy", 20.0, 150.0);
    static String move = "";
    static Scanner scan = new Scanner(System.in);
    static int checkLength = 3;

    static String help = "Commands:\n-North\n-South\n-East\n-West\n-Inv\n-Look\n-Fight-HP\n-Moves-Info";

    public static void main(String[] args) {

        //Place[][] arena2 = new Place[2][2];

        arena1[0][0] = new Place("home", "skyscraper");
        arena1[0][1] = new Place("Place1","hell");
        arena1[1][0] = new Place("Place2", "heaven");
        arena1[1][1] = new Place("place3", "shit");

        System.out.print("\f");
        boolean playAgain = true;

        p1.setCoords(0, 0);
        
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

        while(playAgain) {

            System.out.println(generateMap());
            System.out.println();

            System.out.println("Where do you want to go?");
            move = scan.next();
            filterAll(move);
            System.out.println(generateMap());

            playAgain = false;
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
            
        }
        
        else {
        System.out.println("What do you want to do?");
        move = scan.next();
        filterAll(move); 
        }
        //more else
        
    } else {
        System.out.println("What do you want to do?");
        move = scan.next();
        filterAll(move);
    }
    }
}
