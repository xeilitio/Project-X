package packageGame;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Mur {
    int x, y;
    Image Mur;

    public Mur(int Startx, int Starty) {
        x = Startx;
        y = Starty;
        ImageIcon iMur = new ImageIcon("Images/Mur.PNG");
        Mur = iMur.getImage();
        
    }

    public Rectangle getBounds() {
        Rectangle Box = new Rectangle(x, y, 32, 32);
        return Box;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }
    
    public Image getImage(){
        return Mur;
    }
    
}
