import java.util.ArrayList;

public class Enemy extends Person {
    private ArrayList<Item> heldItems;
    
    public Enem(String name, String desc, double s, double h, ArrayList<Item> items, ArrayList<Item> heldItems) {
        super(name, desc, s, h, items);
        this.heldItems = heldItems; 
    }
    
    public String itemsInHand() {
        String itemsList = "Items in hand:\n";
        for(int i = 0; i < heldItems.size(); i++)
            itemsList += "-"  + heldItems.get(i) + "\n";
        return itemsList;
    }
    
    //TODO: Add droppable items when enemies are defeated.
}