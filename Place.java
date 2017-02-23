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

    public void printItems() {
        if(items.size() == 0) {
            System.out.println("You don't have any items.");
        } else {
            String invList = "Your inventory is: ";
            for(int i = 0; i < items.size(); i++){
                invList += "-" + items.get(i).toString() + "\n";
            }
            System.out.println(invList);
        }
    }

    public Person enemyInRoom() {
        //ArrayList<Person> enemies = getPeople();

        if(people.size() != 0) {
            for(int i = 0; i < people.size(); i++) {
                if(people.get(i) instanceof Enemy)
                    return people.get(i);
            }
        }
        //Why does this do this.
        return people.get(0);

    }

    public String look() {
        String things = "People in room: \n";
        if(people.size() == 0) {
            things = "There is no one in this room.\n";
        }
        for(int i = 0; i < people.size(); i++) {
            things += "-" + people.get(i) + "\n";
        }
        if(items.size() == 0) {
            things += "There are no items here.\n";
        } else {
            things += "Items in room: \n";
        }
        for(int i = 0; i < items.size(); i++) {
            things += "-" + people.get(i) + "\n";
        }
        
        return things;
    }
    
}