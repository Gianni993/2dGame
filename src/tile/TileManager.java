package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile [] tile;
    int mapTileNum[][];

    File acqua = new File("./res/tiles/acqua.png");
    File erba = new File("./res/tiles/erba.png");
    File muro = new File("./res/tiles/muro.png");
    File albero = new File("./res/tiles/albero.png");
    File sabbia = new File("./res/tiles/sabbia.png");
    File terra = new File("./res/tiles/terra.png");
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //variabile appoggio per map1.txt

        getTileImage();
        loadMap("./src/maps/map1.txt");
    }
    // associa il numero all imagine della tessera
    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(acqua);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(erba);

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(muro);

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(albero);

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(sabbia);

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(terra);







        }catch(IOException e){
            e.printStackTrace(); 
        }

    }//fine

    //caricate mappa da fil testo
    public void loadMap(String filePath){

        try{
            InputStream is =  new FileInputStream(filePath); //comando per importare il file txt
            BufferedReader br = new BufferedReader(new InputStreamReader(is));          //bufferedReader per leggere il contenuto del txt

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();            //readLine legge una riga alla volta solo come stringa

                while(col < gp.maxWorldCol) {
                    
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e) {}
    }//fine


    public void draw(Graphics2D g2){

        int worldCol = 0;           //0 0 iniziale è la parte in alto a sinistra della mappa che coincide con la tessera al posto 0 0 della matrice del file di testo
        int worldRow = 0;
     
                        //16 tessere            //12 tessere
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow ) {   

            int tileNum = mapTileNum[worldCol][worldRow]; //estrae dalla mappa il numero da stampare
            
            //posizioni della mappa world
            int worldX = worldCol * gp.tileSize;   //mltiplica il posto della tessera es (0) della mappa x i pixel di gp.tilesize
            int worldY = worldRow * gp.tileSize;

            // punto dove disegnamo screen
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //ottimizzazione: permette di disegnare solo le tessere intorno al giocatore e non tutta la mappa
            if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&       //rimuovere i
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX&&       //gp.tilesize
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&       //per vedere 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ){      //l effetto bordo

            g2.drawImage(tile[tileNum].image, screenX ,screenY ,gp.tileSize,gp.tileSize, null);
            }
            //fine

            worldCol++;
          

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
            
                worldRow++;
    



               
            }
        }
    }

}
