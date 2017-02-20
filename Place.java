import java.util.ArrayList;

public class Place extends NamedThing {
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Person> people = new ArrayList<Person>();
    
    public Place(String name, String desc, ArrayList<Item> i, ArrayList<Person> p) {
        super(name, desc);
        items = i;
        people = p;
    }
    
    public Place(String name, String desc) {
        super(name, desc);
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public ArrayList<Person> getPeople() {
        return people;
    }
    
}