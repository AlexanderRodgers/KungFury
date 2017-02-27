public class Health extends Item {
    
    private double health;
    
    public Health(String name, String desc, double health) {
        super(name, desc);
        this.health = health;
    }
    
    public void changeHealth(double newHealth) {
        health = newHealth;
    }
}