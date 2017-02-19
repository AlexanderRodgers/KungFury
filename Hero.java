import java.util.ArrayList;

public class Hero extends Person {
    
    public int xcord;
    public int ycord;
    public int xmax;
    public int ymax;
    
    public Hero(String name, String desc, double s, double h, ArrayList<Item> items) {
        super(name, desc, s, h, items);
    }
    
    public Hero(String name, String desc, double s, double h) {
        super(name, desc, s, h);
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
    
    public void setCoords(int x, int y) {
        xcord = x;
        ycord = y;
    }
    
    public int getX() {
        return xcord;
    }
    
    public int getY() {
        return ycord;
    }
    
    public void moveNorth () {
        ycord++;
    }

    public void moveSouth () {
        if (ycord > 0) {
           ycord--;
        } else {
            System.out.println("You can't move that way!");
        }
            
    }

    public void moveWest() {
        if (xcord > 0)
            xcord--;
    }

    public void moveEast() {
        xcord++;
    }
    
    public void updateMax(int x, int y) {
        xmax = x;
        ymax = y;
    }
    
}