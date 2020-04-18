package UserInterface;

import Game.StateManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu {

    protected static final int W = 1280;
    protected static final int H = 720;

    private GraphicsContext gc;
    private Image image;
    private UserInterface menuUI;
    private Pane root;

    public MainMenu(){

        root = new Pane(); //maybe try with pane
        Canvas c = new Canvas(W, H);
        root.getChildren().add(c);

        this.gc = c.getGraphicsContext2D();
        this.image = new Image("file:main-menu.png");

        Scene scene = new Scene(root, W, H);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tower Defense");
        stage.show();

        this.menuUI = new UserInterface();
        menuUI.addButton("play", "file:play-button.png", W/2, H/2);
        menuUI.addButton("editor","file:editor-button.png", W/3, H*3/4);
        menuUI.addButton("quit", "file:quit-button.png", W*2/3, H*3/4);
    }

    private void updateButtons() {
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            switch (menuUI.isButtonPressed(event)){
                case "play":
                    StateManager.setState(StateManager.GameState.GAME);
                    break;
                case "editor":
                    StateManager.setState(StateManager.GameState.EDITOR);
                    break;
                case "quit":
                    System.exit(0);
                    break;
            }
        });
    }

    public void update(){
        gc.drawImage(image, 0, 0, W, H);
        menuUI.draw(gc);
        updateButtons();
    }

}
