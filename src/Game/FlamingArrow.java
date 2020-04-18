package Game;

import javafx.scene.image.Image;

public class FlamingArrow extends Projectile {

    //    public FreezeArrow(Image image, Enemy target, float x, float y, float width, float height, float speed, int damage) {
    public FlamingArrow(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override
    public void damage(){
//        super.getTarget().setSpeed(getTarget().getSpeed()*0.75f);
        super.damage();
    }

}