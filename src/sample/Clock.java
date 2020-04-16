package sample;

public class Clock {

    private static boolean paused = false;
    public static long lastFrame, totalTime;
    public static float d = 0, multiplier = 1;
    public static long initTime = System.currentTimeMillis();

    public static long getTime() {
        return (System.currentTimeMillis() - initTime) * 1000;  // i should find a method that returns time
    }

    public static float getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        if (delta * 0.01f > 1000 * 0.5f)
            return 1000 * 0.5f;
        return delta * 0.01f;
    }

    public static float Delta() {
        if (paused)
            return 0;
        else
            return d * multiplier;
    }

    public static float TotalTime() {
        return totalTime;
    }

    public static float Multiplier() {
        return multiplier;
    }

    public static void Update() {
        d = getDelta();
        totalTime += d;
    }

    public static void ChangeMultiplier(int change) {
        if (multiplier + change < -1 && multiplier + change > 7) {

        } else {
            multiplier += change;
        }
    }

    public static void Pause() {
        if (paused)
            paused = false;
        else
            paused = true;
    }

}
