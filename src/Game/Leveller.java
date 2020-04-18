package Game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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

    public static String getTileID(Tile t){
        String ID = "E";
        switch (t.getType()){
            case Dirt:
                ID = "0";
                break;
            case Grass:
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
