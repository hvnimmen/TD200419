package Game;

import java.util.ArrayList;

public class FlamingTower extends Tower{

    public FlamingTower(Tile startTile, int damage, int range, ArrayList<Enemy> enemies){
        super(TowerType.Flaming, startTile, damage, range, enemies);
    }

    @Override
    public void shoot(){
        setTimeSinceLastShot(0);
        getProjectiles().add(new FlamingArrow(getTowerType().projectileName, getTarget(), getX(), getY(), 15, 10));
    }

}