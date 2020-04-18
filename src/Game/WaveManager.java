package Game;

import javafx.scene.canvas.GraphicsContext;

public class WaveManager {

    private float timeSinceLastWave, spawnTime;
    private int waveNumber, enemiesPerWave;
    private Enemy enemyType;
    private Wave currentWave;

    public WaveManager(Enemy enemyType, float spawnTime, int enemiesPerWave){
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;

        this.currentWave = null;

        newWave();
    }

    public void update(GraphicsContext gc) {
        if (!currentWave.isCompleted())
            currentWave.update(gc);
        else
            newWave();
    }

    public void newWave() {
        currentWave = new Wave(enemyType, spawnTime, enemiesPerWave);
        waveNumber++;
        enemiesPerWave += 2;
        spawnTime *= 0.75;
        System.out.println("Deploying Wave no. " + waveNumber);
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

}
