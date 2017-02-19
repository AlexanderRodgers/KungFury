public class NamedThing {
    private String name;
    private String description;
    
    public NamedThing(String n, String d) {
        name = n;
        description = d;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name + ": " + description;
    }
    
    
    public String getDesc() {
        return description;
    }
}