package Game;

import java.io.*;

public class Leveller {

    public static void saveMap(String mapName, TileGrid grid){
        String mapData = "";
        for (int i = 0; i < grid.getxTiles(); i++){
            for (int j = 0; j < grid.getyTiles(); j++){
                mapData += getTileID(grid.GetTile(i, j));
            }
        }
        try {
            File file = new File(mapName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapData);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TileGrid loadMap(String mapName) {
        TileGrid grid = new TileGrid();
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapName));
            String data = br.readLine();
            for (int i = 0; i < grid.getxTiles(); i++) {
                for (int j = 0; j < grid.getyTiles(); j++) {
                    grid.SetTile(i, j, getTileType(data.substring(i * grid.getyTiles() + j, i * grid.getyTiles() + j + 1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return grid;
    }

    public static TileType getTileType(String ID){
        TileType type = TileType.NULL;
        switch(ID){
            case "0":
                type = TileType.Grass;
                break;
            case "1":
                type = TileType.Dirt;
                break;
            case "2":
                type = TileType.Water;
                break;
            case "3":
                type = TileType.NULL;
                break;
        }
        return type;
    }

    public static String getTileID(Tile t){
        String ID = "E";
        switch (t.getType()){
            case Grass:
                ID = "0";
                break;
            case Dirt:
                ID = "1";
                break;
            case Water:
                ID = "2";
                break;
            case NULL:
                ID = "3";
                break;
        }

        return ID;
    }

}
