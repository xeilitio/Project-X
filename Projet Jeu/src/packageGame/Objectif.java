package packageGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Objectif {
    int x, y;
    Image Objectif;
    boolean caisseDessus = false;

    public Objectif(int Startx, int Starty) {
        x = Startx;
        y = Starty;

        ImageIcon iObjectif = new ImageIcon("Images/Objectif.Gif");
        Objectif = iObjectif.getImage();

    }

    public Rectangle getBounds() {
        Rectangle Box = new Rectangle(x, y, 32, 32);
        return Box;
    }
    
    public int gety(){
        return y;
    }
    
    public int getx(){
        return x;
    }
    
    public Image getImage(){
        return Objectif;
    }
}
