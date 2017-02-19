import java.util.*;

public class FuryTest {
    
    static Place[][] arena1 = new Place[2][2];
    static Hero p1 = new Hero("Kung fury", "The baddest boy", 20.0, 150.0);
    
    
    public static void main(String[] args) {
        
        
        //Place[][] arena2 = new Place[2][2];
        
        arena1[0][0] = new Place("home", "skyscraper");
        arena1[0][1] = new Place("Place1","hell");
        arena1[1][0] = new Place("Place2", "heaven");
        arena1[1][1] = new Place("place3", "shit");
        
        System.out.print("\f");
        boolean playAgain = true;
        Scanner scan = new Scanner(System.in);
        
        p1.setCoords(0, 0);
        String move = "";
        
        
        
        String help = "Commands:\n-North\n-South\n-East\n-West-Inv\n-Look\n-Fight-HP\n";
        
        String titleScreen = "Kung Fury: The video game";
        
        System.out.print("\f");
        
        while(playAgain) {
            System.out.println(titleScreen + "\n\n");
            System.out.println(generateMap());
            System.out.println("Hello Kung Fury, the Kung-Fu cop! You're our\n" + 
                "only hope at defeating the Kung Führer. You must hack your way\n" + 
                "back in time to Nazi Germany to defeat him!\n\n" + 
                "To do this you must find hackerman so he can hack you back in time\nYou'll" +
                " know what to do when you get there.");
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
        
        return map;
    }
}