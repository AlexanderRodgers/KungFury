public abstract class Item extends NamedThing {
    private String name;
    private String description;
    private double defense;
    private double attackStrength;
    
    public Item(String name, String desc, double defense, double attackStrength) {
        super(name, desc);
        this.defense = defense;
        this.attackStrength = attackStrength;
    }
    
    public double getDefense() {
        return defense;
    }
    
    public double getAttackStrength() {
        return attackStrength;
    }
    
    //TODO: Add special item/weapon abilities.
}