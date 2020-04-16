package sample;

import java.util.ArrayList;

import static sample.Clock.Delta;

public class Wave {

    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemyList;

    public Wave(float spawnTime, Enemy enemyType) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        timeSinceLastSpawn = spawnTime + 1;
        enemyList = new ArrayList<Enemy>();
    }

    public void Update() {
        timeSinceLastSpawn += Delta();
        if (timeSinceLastSpawn > spawnTime) {
            Spawn();
            timeSinceLastSpawn = 0;
        }

        for (Enemy e : enemyList) {
            e.Move();
            e.Update();
        }
    }

    private void Spawn() {
        Enemy e = new Enemy(enemyType.getStartTile(), enemyType.getSpeed(), enemyType.getType());
        enemyList.add(e);
    }

    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
        this.timeSinceLastSpawn = timeSinceLastSpawn;
    }

    public float getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(float spawnTime) {
        this.spawnTime = spawnTime;
    }

    public Enemy getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(Enemy enemyType) {
        this.enemyType = enemyType;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
    }
}
