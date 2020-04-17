package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

import static sample.Clock.Delta;

public class Wave {

    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemyList;
    private int enemiesPerWave;
    private boolean waveCompleted;

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime * 1000;  //convert into milliseconds
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new ArrayList<>();
        this.waveCompleted = false;

        Spawn();
    }

    public void Update(GraphicsContext gc) {
        boolean allEnemiesDead = true;
        if (enemyList.size() < enemiesPerWave) {
            timeSinceLastSpawn += Delta();
            if (timeSinceLastSpawn > spawnTime) {
                Spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Enemy e : enemyList) {
            if (e.isAlive()){
                allEnemiesDead = false;
                e.Update();
                e.Draw(gc);
            }
        }
        if (allEnemiesDead){
            waveCompleted = true;
        }
    }

    private void Spawn() {
        Enemy e = new Enemy(enemyType.getStartTile(), enemyType.getSpeed(), enemyType.getGrid(), (Math.random() > 0.5));
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

    public boolean isCompleted() { return waveCompleted; }
}
