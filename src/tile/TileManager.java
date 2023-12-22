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
    
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; //variabile appoggio per map1.txt

        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(acqua);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(erba);

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(muro);

        }catch(IOException e){
            e.printStackTrace(); 
        }

    }

    public void loadMap(){

        try{
            InputStream is =  new FileInputStream("./src/maps/map1.txt"); //comando per importare il file txt
            BufferedReader br = new BufferedReader(new InputStreamReader(is));          //bufferedReader per leggere il contenuto del txt

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){

                String line = br.readLine();            //readLine legge una riga alla volta solo come stringa

                while(col < gp.maxScreenCol) {
                    
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;

                }

            }
            br.close();

        }catch(Exception e) {}


    }


    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
                        //16 tessere            //12 tessere
        while (col < gp.maxScreenCol && row < gp.maxScreenRow ) {   

            int tileNum = mapTileNum[col][row]; //estrae dalla mappa il numero da stampare
            
            g2.drawImage(tile[tileNum].image, x,y ,gp.tileSize,gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;



               
            }
        }
    }

}
