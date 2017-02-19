import java.util.ArrayList;

public class Hero extends Person {
    
    public int xcord;
    public int ycord;
    
    public Hero(String name, String desc, double s, double h, ArrayList<Item> items) {
        super(name, desc, s, h, items);
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
    
    public void getX() {
        return xcord;
    }
    
    public void voidY() {
        return ycord;
    }
    
    public void moveNorth () {
        ycord++;
    }

    public void moveSouth () {
        if (ycord > 0)
            ycord--;
    }

    public void moveWest() {
        if (xcord > 0)
            xcord--;
    }

    public void moveEast() {
        xcord++;
    }
}