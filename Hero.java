import java.util.ArrayList;

public class Hero extends Person {

    public int xcord;
    public int ycord;
    public int xmax;
    public int ymax;
    public boolean isDead = false;

    public Hero(String name, String desc, double s, double h, ArrayList<Item> items) {
        super(name, desc, s, h, items);
    }

    public Hero(String name, String desc, double s, double h) {
        super(name, desc, s, h);
    }

    @Override
    public String hurt(double power) {
        if(power - health < 0) {
            isDead = true;
            return (this.getName() + " is dead!");
        } else {
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

    public void moveNorth() {
        if (xcord > 0) {
            xcord--;
        } else {
            System.out.println(outOfArena());
        }
    }

    public void moveSouth() {
        if(xcord < xmax) {
            xcord++;
        } else {
            System.out.println(outOfArena());
        }

    }

    public void moveEast() {
        if(ycord < ymax) {
            ycord++;
        } else {
            System.out.println(outOfArena());
        }

    }

    public void moveWest() {
        if (ycord > 0) {
            ycord--;
        } else {
            System.out.println(outOfArena());
        }

    }

    public void updateMax(int x, int y) {
        xmax = x;
        ymax = y;
    }

    public boolean checkIfDead() {
        return isDead;
    }

    public String outOfArena() {
        return "You can't move that way!\n";
    }

    public String displayDirections() {
        return "-North\n-South\n-East\n-West";
    }

    
    //NOT WORKING WANTED TO UPDATE TO AN ARRAYLIST TO CHECK TO SEE IF PLAYER CHOICE IS VALID
   
    public String getWepNames() {
        ArrayList<String> wepNames = "Your Weapons: \n";
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) instanceof Weapon) {
                wepNames += (items.get(i));
            }
        }
        
        return wepNames;
    }
}