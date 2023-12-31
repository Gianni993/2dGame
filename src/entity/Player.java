package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    File f1 = new File("./res/player/napoUp1.png");
    File f2 = new File("./res/player/napoUp2.png");
    File f3 = new File("./res/player/napoBack1.png");
    File f4 = new File("./res/player/napoBack2.png");
    File f5 = new File("./res/player/napoSxDown.png");
    File f6 = new File("./res/player/napoSxUp.png");
    File f7 = new File("./res/player/napoDxDown.png");
    File f8 = new File("./res/player/napoDxUp.png");

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int hasKey = 0; //numero di chavi disponibile al giocatore
    
    public Player (GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
                           
        solidArea = new Rectangle(); //poligono per le collisioni
        solidArea.x = 8; //offset artendo da in alto a sx  per le x (sempre in px)
        solidArea.y = 16; //offset artendo da in alto a sx  per le y
        solidAreaDefaultX = solidArea.x; //valori default per poligono collisoni object
        solidAreaDefaultY = solidArea.y; //valori default per poligono collisoni object
        solidArea.width = 32; //larghezza poligono
        solidArea.height = 32; //altezza


        setDefaultValues();
        getPlayerimage();

    }
    public void setDefaultValues(){
        //posizione iniziale di player 
        worldX = gp.tileSize*8;
        worldY = gp.tileSize*6;
        speed = 4 ; //velocita 4px/frame
        direction = "down"; //direzione iniziale
    }

    public void getPlayerimage(){

        try {
            
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);
            
        }catch(IOException e){
            e.printStackTrace();

        }

    }

    public void update() {
        //richiamo funzioni all handler e si collegano tramite le variabili player per far muovere il player
        if(keyH.upPressed == true){    
            direction = "up";
            if(collisionOn == false){//se non ci sono collisioni entra nel true e accelera
                worldY -= speed;}
        }
             //java parte da x:0 y:0 in alto a sx quindi gli assi sono invertiti
            
            else if (keyH.downPressed == true) {
                direction = "down";
                if(collisionOn == false){
                worldY += speed;}
               
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
                if(collisionOn == false){
                worldX -= speed;}
              
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
                if(collisionOn == false){
                worldX += speed;    } 
            }
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);


            spriteCounter++;
            //cambia l immagine ogni 25 fotogrammi, per non far muovere sempre il personaggio
            if(spriteCounter >gp.FPS/3) {  //inserire una if ad inizio metodo update
                if(spriteNum == 1){  // con condizione tutte e 4 le key == true con peratore or ||
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
    }
    public void pickUpObject(int i){

        if(i != 999){

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey ++;
                    gp.obj[i] = null;
                    System.out.println("Key"+ hasKey);
                    break;

                case "Door":
                    if(hasKey > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey --;
                    }
                    System.out.println("Key"+ hasKey);
                    break;
                
                case "Boots":
                    gp.playSE(2);
                    speed += 3;
                    gp.obj[i] = null;
                    break;
            
            
            }

        }


    }


    public void draw(Graphics2D g2){

      //  g2.setColor(Color.green);
      //  g2.fillOval(x, y, gp.tileSize,gp.tileSize);  //disegna un rettangolo sullo schermo(coordinate, grandezza)

      BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1){image = up1;}
                if (spriteNum == 2){image = up2;}       
                break;
        
            case "down":
                if (spriteNum == 1){image = down1;}
                if (spriteNum == 2){image = down2;}
                break;
            
            case "left":
                if (spriteNum == 1){image = left1;}
                if (spriteNum == 2){image = left2;}                
                break;

            case "right":
                if (spriteNum == 1){image = right1;}
                if (spriteNum == 2){image = right2;}                
                break;

            default:
                break;
        }
        //disegna immagine con posizione x,y grandezza gp
        g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize, null); //https://stackoverflow.com/questions/19212990/what-is-an-imageobserver
    }

}
