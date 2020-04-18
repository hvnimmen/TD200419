package Game;

public enum TileType {

    Grass("file:grass-block-3.png", true),
    Dirt("file:dirt-block-3.png", false),
    Water("file:water-block-3.png", false),
    NULL("file:water-block-2.png", false);

    String fileName;
    boolean buildable;

    TileType(String fileName, boolean buildable){
        this.fileName = fileName;
        this.buildable = buildable;
    }

}
