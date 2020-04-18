package UserInterface;

import javafx.scene.image.Image;

public class Button {

    private String name;
    private int x, y, width, height, displayX, displayY;
    private Image image;

    public Button(String name, String fileName, int x, int y, int width, int height){
        this.name = name;
        this.image = new Image(fileName);
        this.x = x;
        this.y = x;
        this.width = width;
        this.height = height;
        this.displayX = x - width/2;
        this.displayY = y - height/2;
    }

    public Button(String name, String fileName, int x, int y){
        this.name = name;
        this.image = new Image(fileName);
        this.x = x;
        this.y = y;
        this.width = (int)image.getWidth();
        this.height = (int)image.getHeight();
        this.displayX = x - width/2;
        this.displayY = y - height/2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getDisplayX() {
        return displayX;
    }

    public void setDisplayX(int displayX) {
        this.displayX = displayX;
    }

    public int getDisplayY() {
        return displayY;
    }

    public void setDisplayY(int displayY) {
        this.displayY = displayY;
    }
}
