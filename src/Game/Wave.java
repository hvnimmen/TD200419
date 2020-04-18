package Game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static Game.Clock.Delta;

public class Wave {

    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private int enemiesPerWave, enemiesSpawned;
    private boolean waveCompleted;

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime * 1000;  //convert into milliseconds
        this.enemiesPerWave = enemiesPerWave;
        this.enemiesSpawned = 0;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<>();
        this.waveCompleted = false;

        spawn();
    }

    public void update(GraphicsContext gc) {
        boolean allEnemiesDead = true;
        if (enemiesSpawned < enemiesPerWave) {
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Enemy e : enemyList) {
            if (e.isAlive()){
                allEnemiesDead = false;
                e.update(gc);
            } else {
                enemyList.remove(e);
            }
        }
        if (allEnemiesDead){
            waveCompleted = true;
        }
    }

    private void spawn() {
        enemyList.add(new Enemy(enemyType.getStartTile(), enemyType.getSpeed(), enemyType.getGrid(),
                (Math.random() > 0.5), 50));
        enemiesSpawned++;
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

    public CopyOnWriteArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(CopyOnWriteArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public boolean isCompleted() { return waveCompleted; }
}
