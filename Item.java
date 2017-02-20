public class Item extends NamedThing {
    protected double defense;
    protected double attackStrength;
    protected double sStrength;
    protected double sHealth;
    
    public Item(String name, String desc, double defense, double attackStrength) {
        super(name, desc);
        this.defense = defense;
        this.attackStrength = attackStrength;
    }
    
    
    //Items with special stats.
    public Item(String name, String desc, double defense, double attackStrength, double sStrength, double sHealth) {
        super(name, desc);
        this.defense = defense;
        this.attackStrength = attackStrength;
        this.sStrength = sStrength;
        this.sHealth = sHealth;
    }
    
    public double getDefense() {
        return defense;
    }
    
    public double getAttackStrength() {
        return attackStrength;
    }
    
    //TODO: Add special item/weapon abilities.
}