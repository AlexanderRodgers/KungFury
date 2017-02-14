import java.util.ArrayList;

public abstract class Entity {
    private double strength;
    private double health;
    //does nothing currently.
    private ArrayList<Item> items = new ArrayList<Item>();
    
    public Entity(double s, double h) {
        strength = s;
    }
    
    public double getHealth();
}