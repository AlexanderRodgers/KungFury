import java.util.ArrayList;

public class Enemy extends Person {
    private ArrayList<Item> heldItems;
    
    public Enemy(String name, String desc, double s, double h, ArrayList<Item> items, ArrayList<Item> heldItems) {
        super(name, desc, s, h, items);
        this.heldItems = heldItems; 
    }
    
    public Enemy(String name, String desc, double s, double h) {
        super(name, desc, s, h);
    }
    
    public String itemsInHand() {
        String itemsList = "Items in hand:\n";
        for(int i = 0; i < heldItems.size(); i++)
            itemsList += "-"  + heldItems.get(i) + "\n";
        return itemsList;
    }
    
    
    @Override
    public String hurt(double power) {
        if(power - health < 0)
            return (this.getName() + " is dead!");
        else {
            health -= power;
            return (this.getName() + "\'s health is now: " + this.getHealth());
        }
    }
    
    @Override
    public String heal(double addedHealth) {
        health += addedHealth;
        return ("New health: " + this.getHealth());
    }

    //TODO: Add droppable items when enemies are defeated.
}