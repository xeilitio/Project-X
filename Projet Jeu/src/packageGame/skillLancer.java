package packageGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class skillLancer implements ImageObserver {
    TimerRun t;
    int framex = 0;
    int counter = 0;
    String skillSelect[] = {"Explosion", "Eclat"} ;
    Image iSkill;
    int x;
    int width;
    int height;
    boolean affiche;
    int nombreImages[] = {6, 9};
    Pointeur pointeur;
    
    public skillLancer(){
        pointeur = new Pointeur();
        x = pointeur.getSkill();
        iSkill = Toolkit.getDefaultToolkit().getImage("Images/"+ skillSelect[x]+".png");
        width = iSkill.getWidth(this);
        height = iSkill.getHeight(this);
        t = new TimerRun();
        new Thread(t).start();
        
    }
    
    public void paint(Graphics g, int x, int y) {
        if (affiche) {
            g.setColor(new Color(255, 0, 0, 0));
            g.drawImage(iSkill, x,
                                y, 
                                x + width,
                                y + height,
                                width * framex,
                                0,
                                width * framex + width,
                                height, null);
            refresh();
        }
    }
    
    public void affiche()
    {
        affiche = true;
        t.reset();
        t.restart();
    }
    
    public void refresh(){
        int time = t.GetTime();
        if (time > 50) {
            framex++;
            counter++;
            t.reset();
            if (counter >= 5){
                framex = counter = 0;
                affiche = false;
            }
        }
    }

    @Override
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        System.out.println(" 1:" + arg1 + " 2:" + arg2 + " 3:" + arg3 + " 4:" + arg4 + " 5:" + arg5);
        width = arg4 / nombreImages[x];
        height = arg5;
        return true;
    }
}
