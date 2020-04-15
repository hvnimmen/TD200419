package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane {

    private int x, y;
    private static final int TILE_SIZE = 40;

    private Image image1 = new Image("file:grass-block.png");
    private Image image2 = new Image("file:dirt-block.png");
    private ImageView iv1 = new ImageView(image1);
    private ImageView iv2 = new ImageView(image2);


    public Tile(int x, int y, String type){
        this.x = x;
        this.y = y;

//        add this if image isn't 40x40
//        iv.setPreserveRatio(true);
//        iv.setFitHeight(TILE_SIZE);

        if(type == "grass"){
            getChildren().add(iv1);
        } else {
            getChildren().add(iv2);
        }

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);
    }
}