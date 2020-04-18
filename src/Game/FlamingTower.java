package Game;

import java.util.concurrent.CopyOnWriteArrayList;

import static Game.Game.TILE_SIZE;

public class FlamingTower extends Tower{

    public FlamingTower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies){
        super(type, startTile, enemies);
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new FlamingArrow(super.type.projectileType, target, super.getX(), super.getY(),
                TILE_SIZE/2, TILE_SIZE/2));
    }

}