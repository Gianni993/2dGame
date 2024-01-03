package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{

    
    
    public OBJ_Key(){
        File chiave = new File("./res/objects/chiave.png");
        
        name = "Key";

        try{
            image = ImageIO.read(chiave);

        }catch(IOException e){e.printStackTrace();}

        //solidArea.x = 5;  valori per determinare il rettangolo delle collisioni esclusivamente per le chiavi
        //solidArea.y = 5;
    }
    
}
