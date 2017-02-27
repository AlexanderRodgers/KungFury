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

    public String printWepNames() {
        String names = "Your weapons: \n";
        ArrayList<Weapon> wepNames = getWeapons();
        if(wepNames.size() == 0) {
            return "You have no weapons.\n";
        }
        for(int i = 0; i < wepNames.size(); i++) {
            names += i+1 + " " + wepNames.get(i).toString() + " it does " + 
                wepNames.get(i).getAttackStrength() + " damage\n";
        }
        return names;
    }
    
    public void addToInventory(Item x) {
        items.add(x);
    }
    
    public void removeFromInventory(int i) {
        items.remove(i);
    }
   
    public ArrayList<Weapon> getWeapons() {
        ArrayList<Weapon> wepNames = new ArrayList<Weapon>();
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) instanceof Weapon) {
                wepNames.add(((Weapon)items.get(i)));
            }
        }
        
        return wepNames;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
}