package Game;

import javafx.scene.image.Image;

public enum ProjectileType {

    Archer(new Image("file:arrow.png"), 10, 5),
    Freeze(new Image("file:freeze-arrow.png"), 10, 5),
    Flaming(new Image("file:flaming-arrow.png"), 10, 5);

    Image image;
    int damage;
    float speed;

    ProjectileType(Image image, int damage, float speed){
        this.image = image;
        this.damage = damage;
        this.speed = speed;
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