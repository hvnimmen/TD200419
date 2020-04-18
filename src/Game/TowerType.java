package Game;

public enum TowerType {

    Archer("file:bow.png", "file:arrow.png"),
    Freeze("file:freeze-bow.png", "file:freeze-arrow.png"),
    Flaming("file:flaming-bow.png", "file:flaming-arrow.png");

    String baseName, projectileName;

    TowerType(String baseName, String projectileName) {
        this.baseName = baseName;
        this.projectileName = projectileName;
    }

}
