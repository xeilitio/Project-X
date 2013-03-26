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
    String skillSelect[] = {"Explosion", "Eclat", "Implosion"} ;
    Image iSkill;
    int X;
    int width;
    int height;
    boolean affiche;
    int nombreImages[] = {6, 9, 10};
    int Incant[] = {10, 25, 15};
    Pointeur pointeur;
    
    public skillLancer(int Mx, int My){
        pointeur = new Pointeur();
        t = new TimerRun();
        new Thread(t).start();
        iSkill = Toolkit.getDefaultToolkit().getImage("Images/"+ skillSelect[X]+".png");
        width = iSkill.getWidth(this);
        height = iSkill.getHeight(this);
        
    }
   

    public void paint(Graphics g, int x, int y) {
        
        if (affiche) {
            g.setColor(new Color(255, 0, 0, 0));
            g.drawImage(iSkill, x - (width / 2),
                                y - (height / 2), 
                                x - (width / 2) + width,
                                y - (height / 2) + height,
                                width * framex,
                                0,
                                width * framex + width,
                                height, null);
            refresh();
        }
    }
    
    public void affiche()
    {
      //  if(mx - x =< )
       // else{
        affiche = true;
        t.reset();
        t.restart();}
   // }
    
    public void refresh(){
        int time = t.GetTime();
        if (time > 50) {
            framex++;
            counter++;
            t.reset();
            if (counter >= nombreImages[X]){
                framex = counter = 0;
                affiche = false;
            }
        }
    }

    @Override
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        System.out.println(" 1:" + arg1 + " 2:" + arg2 + " 3:" + arg3 + " 4:" + arg4 + " 5:" + arg5);
        width = arg4 / nombreImages[X];
        height = arg5;
        return true;
    }

    public void incSkill() {
        if (X < 9){
        X++;
        iSkill = Toolkit.getDefaultToolkit().getImage("Images/"+ skillSelect[X]+".png");
        width = iSkill.getWidth(this)/ nombreImages[X];;
        height = iSkill.getHeight(this);
        System.out.println(skillSelect[X]);
        System.out.println(width+" "+height);
        }
    }

    public void decSkill() {
        if (X > 0){
            X--;
        iSkill = Toolkit.getDefaultToolkit().getImage("Images/"+ skillSelect[X]+".png");
        width = iSkill.getWidth(this)/ nombreImages[X];;
        height = iSkill.getHeight(this);
        }
    }
}
