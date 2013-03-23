package packageGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Bat {
    
    TimerRun t = new TimerRun();
    int x = 0,y = 0;
    Image BatImg;
    int width = 45;
    int height = 32;
    int framex = 0;
    int counter = 0;
    
    public Bat(int Startx, int Starty){
        new Thread(t).start();
        x = Startx;
        y = Starty;
        BatImg = Toolkit.getDefaultToolkit().getImage("Images/Bat.png");
    }
    
    public void refresh(){
        int timeR = t.GetTimeR();
        if (timeR > 100) {
            framex++;
            counter++;
            t.timeR = 0;
            if (counter >= 3){
                framex = 0;
                counter = 0;
            }
        }
    }
    
    public Rectangle getBounds() {
        Rectangle Box = new Rectangle(x, y , width, height);
        return Box;
    }
    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }
    
    public Image getImage(){
        return BatImg;
    }
    
    public void paint(Graphics g) {
        g.setColor(new Color(255, 0, 0, 0));
        g.drawImage(BatImg, x, y, x + width, y + height, width * framex, 0, width * framex + width, height, null);
        refresh();
    }

}
