package main;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Tastino X per chiudere
        window.setResizable(false);                  // Finestra Ridimensionabile: no
        window.setTitle("Napo Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();


        window.setLocationRelativeTo(null);                 //la finestra verra sempre puntata a centro schermo
        window.setVisible(true);                           //finestra visibile
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
   
    }
}
