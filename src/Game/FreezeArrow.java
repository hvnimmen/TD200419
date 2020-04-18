package Game;

public class FreezeArrow extends Projectile {

    public FreezeArrow(String fileName, Enemy target, float x, float y, float speed, int damage) {
        super(fileName, target, x, y, speed, damage);
    }

    @Override
    public void damage(){
        super.damage();
        super.getTarget().setSpeed(getTarget().getSpeed()*0.75f);
    }

}
