package Game;

import UserInterface.MainMenu;

public class StateManager {

    public static enum GameState{
        MAIN_MENU, GAME, EDITOR
    }

    public static GameState gameState = GameState.MAIN_MENU;
    public static MainMenu mainMenu;
    public static Game game;
    public static Editor editor;

    private static int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {2,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public static void update() {
//        switch (gameState) {
//            case MAIN_MENU:
//                if (mainMenu == null)
//                    mainMenu = new MainMenu();
//                mainMenu.update();
//                break;
//            case GAME:
//                if (game == null)
//                    game = new Game(map);
//                game.update();
//                break;
//            case EDITOR:
//                if (editor == null)
//                    editor = new Editor();
//                editor.update();
//                break;
//        }

        //isolated testing
        if (game == null)
            game = new Game(map);
        game.update();
    }

    public static void setState(GameState newState) {
        gameState = newState;
    }

}
