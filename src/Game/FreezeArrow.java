package Game;

public class FreezeArrow extends Projectile {

    public FreezeArrow(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override
    public void damage(){
        super.getTarget().setSpeed(getTarget().getSpeed()*0.75f);
        super.damage();
    }

}
