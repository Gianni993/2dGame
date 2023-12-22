package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;                    //dimensione originale personaggi npc e tessere
    final int scale = 3;                                //fattore per resize

    public final int tileSize = originalTileSize * scale;      //scala finale personaggi/tessera 48x48px
    public final int maxScreenCol = 16;                        // numero di colonne
    public final int maxScreenRow = 12;                        // numero di righe (rapporto 4:3)
    public final int screenWidth = tileSize * maxScreenCol;    // larghezza schermo 48px tessera x 16 tessere 768px
    public final int screenHeight = tileSize * maxScreenRow;   // altezza schermo 48px tessera x 12 tessere 576px

    //WORLD SETTINGS
    public final int maxWorldCol = 25;          //far concidere con numero di righe e colonne della mappa sul txt
    public final int maxWorldRow = 25;          // gradezza mondo Col * Row
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;



    //FPS
    public int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;                                  //clock del gioco per creazione game loop
    public Player player = new Player(this, keyH);

    public GamePanel() {                                //funzione costruttrice

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);                //colore background
        this.setDoubleBuffered(true);             //www.tutorialspoint.com/what-is-double-buffering-in-java
        this.addKeyListener(keyH);                      //serve per riconoscere l input da tastiera
        this.setFocusable(true);              //https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/misc/focus.html
    }

    public void startGameThread(){                      //metodo start

        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0,01666sec
        double delta = 0;
        long lastTime = System.nanoTime();   //prende il primo orario 
        long currentTime;
        //Fps display
        long timer = 0;
        int drawCount = 0;


                //LOOP GAME (delta)
            while (gameThread != null){ 

                currentTime = System.nanoTime();        //prende l ora corrente all inizio del ciclo
                delta += (currentTime - lastTime) / drawInterval; //si calcola la differenza tra l ultimo orario e quello appena preso e la sottrae al tempo degli fps targhet incrementando il valore di delta stesso
                timer += (currentTime - lastTime);
                lastTime = currentTime;// scambio dell ora corrente a ultima ora
                
                
                if(delta >= 1){ //stampa qaundo delta è maggiore di 1
                    //UPDATE AND DRAW
                    //1)aggiornare le coordinate del personaggio 
                    update();
                     //2)stamapre a video 
                    repaint();//chiamata strana al metodo paintComponent
                    delta--; //toglie 1 a delta
                    drawCount++;//fps

            }   //Fps display
                if(timer >=1000000000 ){
                    System.out.println("Fps:" + drawCount);
                    drawCount = 0;
                    timer = 0;
                }

            }
        }
    public void update(){
        
        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //sfera bianca
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2);//prima lo sfondo
        player.draw(g2);// e dopo i player

        g2.dispose();           //utilizzato per rilasciare le risorse mantenute da una finestra o da un componente GUI.
    }


}
