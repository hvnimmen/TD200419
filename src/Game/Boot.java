package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Boot extends Application {

    public void start(Stage window){

        new AnimationTimer() {
            public void handle(long l) {
                Clock.Update();
                StateManager.update();
            }
        }.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
