import java.util.*;
import java.util.concurrent.TimeUnit;

public class FuryTest {

    static Place[][] arena1 = new Place[3][3];
    static ArrayList<Item> furyItems = new ArrayList<Item>();
    static Hero p1 = new Hero("Kung fury", "The baddest boy", 20.0, 150.0, furyItems);
    static String move = "";
    static Scanner scan = new Scanner(System.in);
    static int checkLength = 3;
    static String noFight = "Sorry, you can't move while there are enemies in the room!";

    //booleans
    static boolean canMove = true;
    static boolean win = false;
    static boolean firstCheck = true;
    static boolean firstTimeLand = true;
    static boolean hasHacked = false;
    static boolean firstDisplayHere = false;

    public static void main(String[] args) {
        
        Item fists = new Weapon("Fists", "Your most reliable ally", 10.0, 10.0);
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

        System.out.print("\f");

        System.out.println(StringMap.theme);

        System.out.println(StringMap.titleScreen + "\n\n");

        System.out.println(StringMap.intro);

        System.out.println("Here are your commands: " + StringMap.help + "\n");

        System.out.println("TYPE \'BEGIN\' TO BEGIN");

        String loadingScreen = scan.next();

        System.out.print("\f");
        while(isAlive && !win) {

            System.out.println(generateMap());
            System.out.println();

            System.out.println("You are in " + getArena().toString() + "\n");
            System.out.println(StringMap.whereTo);
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
                    System.out.println(StringMap.noMove);
                    move = scan.next();
                }

                filterAll(move);

                if(p1.checkIfDead())
                    isAlive = false;
            }

            if(p1.getX() == 0 && p1.getY() == 0 && firstTimeLand) {
                funnyDialogue();
            }

            System.out.println(p1.getX());
            System.out.println(p1.getY());
            if(p1.getX() == 2 && p1.getY() == 2 && hasHacked) {
                System.out.println(StringMap.fuhrerSeen);
                System.out.println(StringMap.fuhrerMoves);
                System.out.println("Just as you prepare to battle, the Kung Führer tries to do a kick but\n" + 
                    "sprains his leg. You walk over to him and kick him repeatedly\n" +
                    "you win.");
                win = true;
            }

            if(p1.checkIfDead())
                isAlive = false;
        }

        if(win) {
            System.out.println(StringMap.gameOverMessage);
        }
    }

    public static void pause(int x) {
        try {
            Thread.sleep(x);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void funnyDialogue() {
        firstTimeLand = false;
        System.out.println(StringMap.hackermanFound + "\n");
        pause(2000);
        System.out.println(StringMap.hackermanIntro);
        pause(5000);
        System.out.println(StringMap.willSeePlayer);

        System.out.println(StringMap.hackermanQ1);

        System.out.println(StringMap.q1Answer);

        int choice = scan.nextInt();

        if(choice == 1) {
            System.out.println(StringMap.q1Correct);
            p1.heal(10.0);
            System.out.println(StringMap.healthBack + "\n");
        } else {
            System.out.println(StringMap.q1Wrong + "\n");
        }

        System.out.println(StringMap.hackermanQ2);

        System.out.println(StringMap.q2Answer);

        choice = scan.nextInt();

        if(choice == 1 || choice == 2) {
            System.out.println(StringMap.q1Wrong +"\n");

        } else {
            System.out.println(StringMap.q1Correct + "\n");
            p1.heal(10.0);
            System.out.println(StringMap.healthBack + "\n");
        }

        System.out.println(StringMap.hackermanQ3);
        System.out.println(StringMap.q3Answer);
        choice = scan.nextInt();
        if(choice == 1) {
            System.out.println(StringMap.q3Choice1);
            p1.heal(10.0);
            System.out.println(StringMap.healthBack + "\n");
        } else if(choice == 2) {
            System.out.println(StringMap.q3Choice2);
            p1.heal(10.0);
            System.out.println(StringMap.healthBack + "\n");
        } else if(choice == 3) {
            System.out.println(StringMap.q3Choice3);
            p1.heal(10.0);
            System.out.println(StringMap.healthBack + "\n");
        } else if(choice == 4) {
            System.out.println(StringMap.q3Choice3);
            p1.heal(10.0);
            System.out.println(StringMap.healthBack + "\n");
        } else {
            System.out.println(StringMap.q3Fail + "\n");
        }

        System.out.println(StringMap.hackermanReady);

        System.out.println(StringMap.readyToHack);

        //James edited this. I don't know if it'll work.
        while (scan.hasNextDouble()){
            scan.next();
        }
        String hackChoice = scan.next();

        if(hackChoice.charAt(0) == 'y') {
            hackTime();
        } else {
            System.out.println(StringMap.hackRejection);
            hackTime();
        }
    }

    public static void hackTime() {
        //Guard at tank
        ArrayList<Item> guardItems = new ArrayList<Item>();
        ArrayList<Person> guardPeople = new ArrayList<Person>();
        Person gEnemy = new Enemy("Oscar", "A German soldier", 15.0, 50.0);
        guardPeople.add(gEnemy);
        Item sword = new Weapon("A big ol\' sword.", "Yeah, this would hurt.", 30.0, 30.0);
        guardItems.add(sword);

        //Old people
        ArrayList<Item> oldPeopleItems = new ArrayList<Item>();
        ArrayList<Person> oldPeople = new ArrayList<Person>();
        Person oPeople = new Enemy("Hansel", "A German man who's pretty good at Karate.", 20.0, 50.0);
        guardPeople.add(gEnemy);
        Item slippers = new Weapon("A pair of slippers", "Cause why not.", 5.0, 5.0);
        guardItems.add(slippers);

        //Headquarter's henchman
        ArrayList<Item> henchItems = new ArrayList<Item>();
        ArrayList<Person> henchPeople = new ArrayList<Person>();
        Person hPeople = new Enemy("Tom", "He just needed a job, he's pretty cool after work.", 20.0, 50.0);
        henchPeople.add(gEnemy);
        Item axe = new Weapon("A dull axe", "Just try it.", 15.0, 15.0);
        henchItems.add(axe);

        hasHacked = true;
        System.out.println("\n" + StringMap.hackingTime +"\n");
        pause(3000);

        arena1[0][0] = new Place("Germany", "This is the time period of the Führer!");
        arena1[0][1] = new Place("The guard post", "Tread lightly.", guardItems);
        arena1[0][2] = new Place("The tank", "It\'s big.", guardItems, guardPeople);
        arena1[1][0] = new Place("A bus station", "A bus that comes every hour, on the hour");
        arena1[1][1] = new Place("Berlin", "You\'re getting closer.");

        //paused here.
        arena1[1][2] = new Place("Somewhere close to Berlin", "You see a cow, but it wanders off.");
        arena1[2][0] = new Place("The home of an elderly couple", "You see furniture wrapped in plastic", oldPeopleItems, oldPeople);
        arena1[2][1] = new Place("Out side the Führer\'s headquarters", "So close!", henchItems, henchPeople);
        arena1[2][2] = new Place("The Führer\'s room", "It\'s a battle to the death.");
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
            System.out.println(StringMap.whereTo);
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
                System.out.println(StringMap.help);
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
                System.out.println(StringMap.choiceInvalid);
                move = scan.next();
                filterAll(move); 
            }
            //more else

        } else {
            System.out.println(StringMap.whereToMiss);
            move = scan.next();
            filterAll(move);
        }
    }

    public static void fight() {
        Person inRoom = getArena().enemyInRoom();
        if(inRoom.getName() == null) {
            System.out.println(StringMap.noFight);
        } else {
            System.out.println(StringMap.weaponChoices + "\n");
            firstDisplayHere = true;
            System.out.println(p1.printWepNames());

            boolean moveValid = false;
            int itemMove = scan.nextInt();

            ArrayList<Weapon> totalWeps = p1.getWeapons();
            System.out.println(totalWeps.size());

            for(int i = 0; i < totalWeps.size(); i++) {

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

        firstDisplayHere = false;
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
            System.out.println(StringMap.noItemsPickup);
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

    public static void playerAttackEnemy(Weapon weapon) {
        //This doesn't relate but I want to fix the method that calls the weapons you can use to fight and gives back all their stats.
        Person guy = getArena().enemyInRoom();
        guy.hurt(weapon.getAttackStrength());
        if(guy.getHealth() == 0) {
            System.out.println(guy.getName() + " is dead!");
            pause(2000);
            getArena().removePerson();
        } else {
            //System.out.println("You were attacked by " + guy.getName());
            System.out.println("Your health is now: " + p1.getHealth());
            System.out.println(guy.getName() + "\'s health is now: " + guy.getHealth());
            System.out.println();
        }
    }

}