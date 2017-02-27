import java.util.ArrayList;

public abstract class Person extends NamedThing {
    protected double strength;
    protected double health;
    protected ArrayList<Item> items = new ArrayList<Item>();
    
    public Person(String name, String desc, double s, double h, ArrayList<Item> items) {
        super(name, desc);
        strength = s;
        health = h;
        this.items = items;
    }
    
    public Person(String name, String desc, double s, double h) {
        super(name, desc);
        strength = s;
        health = h;
    }
    
    public double getHealth() {
        return health;
    }
    
    public double getStrength() {
        return strength;
    }
    
    public String getItems() {
        String itemsList = "Your items:\n";
        for(int i = 0; i < items.size(); i++) {
            itemsList += "-" + items.get(0).getName() + "\n";
        }
        
        return itemsList;
    }
    
    public void setAttackPwr(double power) {
        strength = power;
    }
    
    public void hurt(double power) {
        if(health - power < 0) {
            health = 0;
        } else {
            health -= power;
        }
    }
    
    public void heal(double addedHealth) {
        health += addedHealth;
    }

}