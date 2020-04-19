package Game;

import javafx.scene.image.Image;

public enum EnemyType {

    Zombie(new Image("file:zombie-face.png"), 1, 50, 2),
    Spider(new Image("file:spider-face.png"), 2, 30, 1),
    Random(new Image("file:spider-face.png"), 0, 0, 0);

    Image image;
    float speed;
    int health, damage;

    EnemyType(Image image, float speed, int health, int damage){
        this.image = image;
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

}
