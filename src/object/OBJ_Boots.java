package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{

    public OBJ_Boots(){
        File scarpa = new File("./res/objects/scarpa.png");
        
        name = "Boots";

        try{
            image = ImageIO.read(scarpa);

        }catch(IOException e){e.printStackTrace();}

    }
}
