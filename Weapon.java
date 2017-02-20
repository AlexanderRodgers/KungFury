public class Weapon extends Item {
    private double defense;
    private double attackStrength;
    private double sStrength = 1.0;
    private double sDefense = 1.0;
    
    public Weapon(String name, String desc, double defense, double attackStrength) {
        super(name, desc);
        this.defense = defense;
        this.attackStrength = attackStrength;
    }
    
    //Items with special stats.
    public Weapon(String name, String desc, double defense, double attackStrength, double sStrength, double sDefense) {
        super(name, desc);
        this.defense = defense;
        this.attackStrength = attackStrength;
        this.sStrength = sStrength;
        this.sDefense = sDefense;
    }
    
    public double getDefense() {
        return defense * sDefense;
    }
    
    public double getAttackStrength() {
        return attackStrength * sStrength;
    }
}