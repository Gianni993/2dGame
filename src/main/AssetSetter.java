package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){

        this.gp = gp;

    }
    public void setObject(){

        //key1
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 10 * gp.tileSize;
        gp.obj[0].worldY = 2 * gp.tileSize;

        //key2
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 5 * gp.tileSize;
        gp.obj[1].worldY = 21 * gp.tileSize;

         //door1
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 20 * gp.tileSize;

         //door2
        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 16 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;

         //chest1
        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 20 * gp.tileSize;

    }
}
