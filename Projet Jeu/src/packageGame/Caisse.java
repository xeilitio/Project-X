package packageGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Caisse {
    int x, y;
    String Etat = "Normal";
    Image Caisse;
    public Caisse (int Startx, int Starty) {
        x = Startx;
        y = Starty;
    }
    ImageIcon iCaisseNormal = new ImageIcon("Images/Caisse.PNG");
    boolean CaisseJuste = false;
    


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

    public void setx(int newx) {
        this.x = newx;
    }

    public void sety(int newy) {
        this.y = newy;
    }

    public boolean getJuste() {
        return CaisseJuste;
    }

    public void setJuste(boolean newJuste) {
        this.CaisseJuste = newJuste;
    }
    
    public Image getImage() {
            Caisse = iCaisseNormal.getImage();
            return Caisse;

    }

}
