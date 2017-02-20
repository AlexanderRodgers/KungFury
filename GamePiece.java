public class GamePiece extends Item {
    
    String key;
    
    public GamePiece(String name, String desc, String key) {
        super(name, desc);
        this.key = key;
    }
    
    public void setKey(String newKey) {
        key = newKey;
    }
}