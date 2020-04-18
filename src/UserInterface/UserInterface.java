package UserInterface;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class UserInterface {

    private ArrayList<Button> buttonList;

    public UserInterface() {
        buttonList = new ArrayList<Button>();
    }

    public void addButton(String name, String filename, int x, int y) {
        buttonList.add(new Button(name, filename, x, y));
    }

    public String isButtonPressed(MouseEvent event) {
        for (Button b: buttonList) {
            if (event.getX() > b.getDisplayX() && event.getX() < b.getDisplayX() + b.getWidth() &&
                    event.getY() > b.getDisplayY() && event.getY() < b.getDisplayY() + b.getHeight()) {
                return b.getName();
            }
        }
        return "null";  //temps solution
    }

    private Button getButton(String buttonName) {
        for (Button b: buttonList) {
            if (b.getName().equals(buttonName)){
                return b;
            }
        }
        return null;
    }

    public void draw(GraphicsContext gc) {
        for (Button b : buttonList) {
            gc.drawImage(b.getImage(), b.getDisplayX(), b.getDisplayY());
        }
    }

}
