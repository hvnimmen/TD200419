package Game;

import javafx.scene.image.Image;

public enum ProjectileType {

    Archer(new Image("file:arrow.png"), 10, 10),
    Freeze(new Image("file:freeze-arrow.png"), 10, 10),
    Flaming(new Image("file:flaming-arrow.png"), 10, 10);

    Image image;
    int damage;
    float speed;

    ProjectileType(Image image, int damage, float speed) {
        this.image = image;
        this.damage = damage;
        this.speed = speed;
    }
}