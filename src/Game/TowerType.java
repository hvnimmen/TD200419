package Game;

import javafx.scene.image.Image;

public enum TowerType {

    Archer(new Image("file:bow.png"), ProjectileType.Archer, 5, 1),
    Freeze(new Image("file:freeze-bow.png"), ProjectileType.Freeze, 5, 1),
    Flaming(new Image("file:flaming-bow.png"), ProjectileType.Flaming, 5, 1);

    Image image;
    ProjectileType projectileType;
    int range;
    float cooldown;

    TowerType(Image image, ProjectileType projectileType, int range, float cooldown){
        this.image = image;
        this.projectileType = projectileType;
        this.range = range;
        this.cooldown = cooldown;
    }

//    Archer("file:bow.png", "file:arrow.png"),
//    Freeze("file:freeze-bow.png", "file:freeze-arrow.png"),
//    Flaming("file:flaming-bow.png", "file:flaming-arrow.png");
//
//    String baseName, projectileName;
//
//    TowerType(String baseName, String projectileName) {
//        this.baseName = baseName;
//        this.projectileName = projectileName;
//    }

}
