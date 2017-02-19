import java.util.ArrayList;

public class Place extends NamedThing {
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Person> people = new ArrayList<Person>();
    int[][] arena;
    
    public Place(String name, String desc, ArrayList<Item> i, ArrayList<Person> p) {
        super(name, desc);
        items = i;
        people = p;
    }
    
    public Place(String name, String desc) {
        super(name, desc);
    }
    
    public void setArena(int width, int height) {
        arena[][] = new int[width][height];
    }
    
}