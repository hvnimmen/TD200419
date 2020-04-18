package Game;

import java.util.ArrayList;

public class ArcherTower extends Tower{

    public ArcherTower(Tile startTile, int damage, int range, ArrayList<Enemy> enemies){
        super(TowerType.Archer, startTile, damage, range, enemies);
    }

    @Override
    public void shoot(){
        setTimeSinceLastShot(0);
        getProjectiles().add(new Arrow(getTowerType().projectileName, getTarget(), getX(), getY(), 15, 10));
    }

}
