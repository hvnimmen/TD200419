package Game;

import javafx.scene.image.Image;

public enum TowerType {

    Archer(new Image("file:bow.png"), ProjectileType.Archer, 5, 1, 10),
    Freeze(new Image("file:freeze-bow.png"), ProjectileType.Freeze, 5, 1, 25),
    Flaming(new Image("file:flaming-bow.png"), ProjectileType.Flaming, 5, 1, 25);

    Image image;
    ProjectileType projectileType;
    int range, cost;
    float cooldown;

    TowerType(Image image, ProjectileType projectileType, int range, float cooldown, int cost){
        this.image = image;
        this.projectileType = projectileType;
        this.range = range;
        this.cooldown = cooldown;
        this.cost = cost;
    }

}
