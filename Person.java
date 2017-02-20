import java.util.ArrayList;

public abstract class Person extends NamedThing {
    protected double strength;
    protected double health;
    protected double sStrength;
    protected double sHealth;
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
    
    public abstract String hurt(double power);
    
    public abstract String heal(double addedHealth);
    
}