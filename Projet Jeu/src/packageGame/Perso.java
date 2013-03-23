package packageGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Perso {

    public final static int HAUT_GAUCHE = 1;
    final static int HAUT_DROITE = 2;
    final static int HAUT = 3;
    final static int BAS = 4;
    final static int GAUCHE = 5;
    final static int DROITE = 6;
    final static int BAS_GAUCHE = 7;
    final static int BAS_DROITE = 8;
    final static int COL_B = 9;
    final static int COL_D = 10;
    final static int COL_G = 11;
    final static int COL_H = 12;
    final static int No_COL = 13;

    int hp = 100;
    int persoCol;
    int timeF=0;
    TimerRun t;
    int x = 0, y = 0;
    int framex = 0, framey = 0;
    int width = 32;
    int height = 48;
    int counter = 0;
    int PersoMov = BAS;
    Image PersoImg;
   
    public Perso(int Startx, int Starty) {
        t = new TimerRun();
        x = Startx;
        y = Starty;
        PersoImg = Toolkit.getDefaultToolkit().getImage("Images/none.PNG");
        new Thread(t).start();
    }

    public int getHP(){
        return hp;
    }
    public void setHP(int newHP){
        hp = newHP;
    }
    public Rectangle getBounds() {
        Rectangle Box = new Rectangle(x, y +18 , 32, 30);
        return Box;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int getMov() {
        return PersoMov;
    }

    public void setx(int newx) {
        x = newx;
    }

    public void sety(int newy) {
        y = newy;
    }

    public void setMov(int newMov) {
        if (PersoMov != newMov) {
            PersoMov = newMov;
            framex = 0;
        }
    }


    public void setCol(int newCol) {
        persoCol = newCol;
    }
    
    public int getCol(){
        return persoCol;
    }
    
    public void paint(Graphics g) {
        g.setColor(new Color(255, 0, 0, 0));
        g.drawImage(PersoImg, x, y, x + width, y + height, width * framex, height * framey, width * framex + width, height * framey + height,null);
        refresh();
    }
    

    public void refresh(){
       
        timeF = t.GetTimeF();
   
        if (timeF > 500) {
            framex = 0;
            timeF = 0;
            t.timeF = 0;
        }
    }
    public void Move() {
        //Collision();
        
        if (PersoMov == BAS) {
            if(persoCol != COL_B){
            y += 8;
            }
            framey = 0;
            framex++;
            
        } else if (PersoMov == HAUT) {
            if(persoCol != COL_H){
            y -= 8;
            }
            framey = 3;
            framex++;
            
        } else if (PersoMov == GAUCHE) {
            if(persoCol != COL_G){
            x -= 8;
            }
            framey = 1;
            framex++;
            
        } else if (PersoMov == DROITE) {
            if(persoCol != COL_D){
            x += 8;
            }
            framey = 2;
            framex++;
            
        } else if (PersoMov == HAUT_DROITE) {
            if(persoCol != COL_H && persoCol != COL_D){
            y -= 8;
            x += 8;
            }
            framey = 3;
            framex++;
            
        } else if (PersoMov == HAUT_GAUCHE) {
            if(persoCol != COL_H && persoCol != COL_G){
            y -= 8;
            x -= 8;
            }
            framey = 3;
            framex++;
            
        } else if (PersoMov == BAS_DROITE) {
            if(persoCol != COL_D && persoCol != COL_B){
            y += 8;
            x += 8;
            }
            framey = 0;
            framex ++;
            
        } else if (PersoMov == BAS_GAUCHE) {
            if(persoCol != COL_B && persoCol != COL_G){
            y += 8;
            x -= 8;
            }
            framey = 0;
            framex++;
            
        }
            if (framex == 4)
            framex = 0;
    }

}
