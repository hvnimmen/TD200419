package sample;

public enum TileType {

    Grass("file:grass-block.png", true),
    Dirt("file:dirt-block.png", false),
    Water("file:water-block.png", false);

    String fileName;
    boolean buildable;

    TileType(String fileName, boolean buildable){
        this.fileName = fileName;
        this.buildable = buildable;
    }

}
