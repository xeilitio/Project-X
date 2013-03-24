package packageGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class Pointeur {
    int index = 0, nbImgs = 10;
    int ImageCourante = 0;
    int Mx, My;
    int skill;
    List<Image> pointeurs;
    
    public Pointeur(){
       pointeurs = new ArrayList<Image>();
       for (int i = 0; i < nbImgs; i++){
           pointeurs.add(Toolkit.getDefaultToolkit().getImage("Images/Pointeur"+(i+1)+".png"));
       }
    }
    public int getSkill(){
        return index;
    }
    public void paint(Graphics g, int Mx, int My){
        g.setColor(new Color(255, 0, 0, 0));
        g.drawImage(pointeurs.get(index), Mx, My, null);
    }
    
   public void incImg() {
       if (index < nbImgs - 1)
           index++;
   }
   
   public void decImg() {
       index--;
       if ( index < 0)
           index = 0;
   }
}
