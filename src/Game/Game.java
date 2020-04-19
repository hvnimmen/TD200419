package Game;

import UserInterface.UserInterface;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Game.Boot.*;

public class Game {

    protected static final int TILE_SIZE = 64;

    protected static final int X_TILES = 20;
    protected static final int Y_TILES = 12;

    protected static final int W = TILE_SIZE * X_TILES;
    protected static final int H = TILE_SIZE * Y_TILES;

    private int mouseX, mouseY;

    private TileGrid grid;
    private Wave wave;
    private WaveManager waveManager;
    private Player player;
    private UserInterface selectUI;

    private VBox root;
    private GraphicsContext gc;
    private Stage window;
    private Canvas c;
    private Scene scene;

    public Game(int[][] map) {

        grid = new TileGrid(map);
//        waveManager = new WaveManager(new Enemy(EnemyType.Zombie, grid.getTile(0, 10), grid), 2, 1);
//        waveManager = new WaveManager(new Enemy(EnemyType.Spider, grid.getTile(0, 10), grid), 2, 3);
        waveManager = new WaveManager(new Enemy(EnemyType.Random, grid.getTile(0, 10), grid), 2, 3);

        player = new Player(grid, waveManager);
        player.setup();
        window = new Stage();
        showWindow();

        setupUI();
    }

    private void setupUI(){
        selectUI = new UserInterface();
        selectUI.addButton("Archer", "file:bow.png", TILE_SIZE/2, TILE_SIZE/2);
        selectUI.addButton("Freeze", "file:freeze-bow.png", TILE_SIZE/2, TILE_SIZE * 3 / 2);
        selectUI.addButton("Flaming", "file:flaming-bow.png", TILE_SIZE/2, TILE_SIZE * 5 / 2);
    }

    public void showWindow() {

        root = new VBox(); //maybe try with pane
        c = new Canvas(W, H);
        gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

//        root.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
//            int newX = (int)Math.floor(event.getX()/TILE_SIZE);
//            int newY = (int)Math.floor(event.getY()/TILE_SIZE);
//            player.addTower(newX, newY);
//        });
//
//        root.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
//            int newDisplayX = (int)Math.floor(event.getX()/TILE_SIZE)*TILE_SIZE;
//            int newDisplayY = (int)Math.floor(event.getY()/TILE_SIZE)*TILE_SIZE;
//            gc.drawImage(new Image("file:crosshair.png"), newDisplayX, newDisplayY);
//            System.out.println("displaying crosshairs");
//        });

        root.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (player.isHoldingTower()){
                player.addTower(mouseX/TILE_SIZE, mouseY/TILE_SIZE);
            } else {
                switch (selectUI.isButtonPressed(event)){
                    case "Archer":
                        player.selectTower(new ArcherTower(TowerType.Archer, grid.getTile(0, 0),
                                waveManager.getCurrentWave().getEnemyList()));
                        break;
                    case "Freeze":
                        player.selectTower(new FreezeTower(TowerType.Freeze, grid.getTile(0, 0),
                                waveManager.getCurrentWave().getEnemyList()));
                        break;
                    case "Flaming":
                        player.selectTower(new FlamingTower(TowerType.Flaming, grid.getTile(0, 0),
                                waveManager.getCurrentWave().getEnemyList()));
                        break;
                }
            }
        });

        scene = new Scene(root, W, H);

        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
            switch (key.getCode()) {
                case F:
                    Clock.changeMultiplier(0.2f);
                    break;
                case B:
                    Clock.changeMultiplier(-0.2f);
                    break;
//                case SPACE:
//                    System.out.println("handling space event");
//                    player.switchTower();
//                    break;
            }
        });

        window.setScene(scene);
        window.setTitle("Tower Defense");
        window.show();

    }

//    public void updateUI() {
//        towerPickerUI.draw(gc);
//
//        root.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            switch (towerPickerUI.isButtonPressed(event)){
//                case "Archer":
//                    player.pickTower(new FreezeTower(TowerType.Freeze, grid.getTile(0, 0),
//                            waveManager.getCurrentWave().getEnemyList()));
//            }
//        });
//    }

    public void updateUI() {

        selectUI.draw(gc);

        if (player.isHoldingTower()){
            player.getTempTower().setX(mouseX/TILE_SIZE);
            player.getTempTower().setY(mouseY/TILE_SIZE);
            player.getTempTower().draw(gc);
        }

    }

    public void updateMousePos() {

        root.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            mouseX = (int)Math.floor(event.getX()/TILE_SIZE)*TILE_SIZE;
            mouseY = (int)Math.floor(event.getY()/TILE_SIZE)*TILE_SIZE;
        });

    }

    public void displayCrosshairs(){
        gc.drawImage(new Image("file:crosshair.png"), mouseX, mouseY);
    }

    public void update() {

        grid.draw(gc);
        waveManager.update(gc);
        player.update(gc);

        updateMousePos();
        displayCrosshairs();
        updateUI();

    }

}
