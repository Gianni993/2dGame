package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest(){
        File chest = new File("./res/objects/chest.png");
        
        name = "Chest";

        try{
            image = ImageIO.read(chest);

        }catch(IOException e){e.printStackTrace();}


    }
    
}
