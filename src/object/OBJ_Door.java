package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {

    public OBJ_Door(){
        File porta = new File("./res/objects/porta.png");
        
        name = "Door";

        try{
            image = ImageIO.read(porta);

        }catch(IOException e){e.printStackTrace();}


    }
    
}
