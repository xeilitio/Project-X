package packageGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Plateau extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener, KeyListener {
    String Map[][] = new String[20][15];
    static int Level = 1;
    int x,y;
    private static ArrayList<Mur> Murs;
    private static ArrayList<Objectif> Objectifs;
    private static ArrayList<Caisse> Caisses;
    private static ArrayList<Bat> Bats;
    boolean keypressed = false;
    int old_key = 0;
    Perso perso;
    Objectif objectif;
    Caisse caisse;
    Mur mur;
    Bat bat;
    Font levelFont = new Font("SansSerif", Font.BOLD, 15);
    Font Screen = new Font("Sans", Font.BOLD, 50);
    FileReader fr;
    final static int HAUT_GAUCHE = 1;
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
    int vitesse = 5;
    int time = 0, timeR = 0;
    TimerRun t;
    BarreHP barHP;
    boolean enVie = true;
    skillLancer skill;
    Pointeur pointeur;
    int Mx,My;
    int image=1;
    int indexInc = 0;
    
    public Plateau() {
        skill = new skillLancer();
        t = new TimerRun();
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        ChargementLevel();
        barHP = new BarreHP(perso, this);
        new Thread(barHP).start();
        pointeur = new Pointeur();
    }

    public void ChargementLevel() {
        try {
            fr = new FileReader("Maps/level" + Level + ".lvl");
            int x = 0, y = 0, i = 1;
            Murs = new ArrayList<Mur>();
            Caisses = new ArrayList<Caisse>();
            Objectifs = new ArrayList<Objectif>();
            Bats = new ArrayList<Bat>();

            while ((i = fr.read()) != -1) {
                char StrImg = (char) i;

                if (StrImg == '0') {
                    Map[x][y] = "Mur";
                    Murs.add(new Mur(x * 32, y * 32));
                } else if (StrImg == '1') {
                    Map[x][y] = "Perso1";
                    perso = new Perso(x * 32, y * 32);
                } else if (StrImg == '2') {
                    Map[x][y] = "Caisse";
                    caisse = new Caisse(x * 32, y * 32);
                    Caisses.add(caisse);
                } else if (StrImg == '3') {
                    Map[x][y] = "Objectif";
                    objectif = new Objectif(x * 32, y * 32);
                    Objectifs.add(objectif);
                } else if (StrImg == ' ') {
                    Map[x][y] = null;
                } else if (StrImg == 'B') {
                    Map[x][y] = "Bat";
                    bat = new Bat(x * 32, y * 32);
                    Bats.add(bat);
                } else if (StrImg == '\r' || StrImg == '\n') {
                    x--;
                }
                if (x == 19) {
                    y++;
                    x = 0;
                } else {
                    x++;
                }
            }
            perso.hp = 100;
            new Thread(t).start();
            t.restart();
        } catch (Exception e) {}
        new Thread(barHP).start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        checkVie();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      
        
        for (Mur mur : Murs) {
            g2d.drawImage(mur.getImage(), mur.getx(), mur.gety(), null);
        }
        for (Objectif objectif : Objectifs) {
            g2d.drawImage(objectif.getImage(), objectif.getx(), objectif.getx(), null);
        }
        for (Caisse caisse : Caisses) {
            g2d.drawImage(caisse.getImage(), caisse.getx(), caisse.gety(), null);
        }
        for (Bat bat : Bats) {
            bat.paint(g);
        }
        if (enVie){
        perso.paint(g);
        }
        pointeur.paint(g, Mx, My);
        skill.paint(g, x - 64, y - 64);
        g.setColor(Color.BLACK);
        g.setFont(levelFont);
        g.drawString("Level : " + Level, 500, 25);
        
        if (enVie == false){
            g.fillRect(0,0, getWidth(),getHeight());
            g.setFont(Screen);
            g.setColor(Color.white);
            g.drawString("Vous êtes mort", 120, getHeight()/2);
            mort();
        }
        else
        {
        deplacement();
        }
        // refresh();
        callRepaint();
    }

    
    public void mort() {
        // TODO Auto-generated method stub
        
    }

    public void checkVie(){
        if (perso.hp <= 0){
            enVie = false;
            barHP.setVisiBar(false);
        }
    }
    
    public void deplacement() {
        time = t.GetTime();
        checkCollisions();
        if (time >= 10 * vitesse) {
            movePersos();
            t.time = 0;
        }
    }

    public void callRepaint() {
        for (int i = 0; i < 1000; i++)
            repaint();
    }

    public void refresh() {
        timeR = t.GetTimeR();
        if (timeR >= 25) {
            System.out.println(timeR);
            repaint();
            t.timeR = 0;
        }
    }

    public void movePersos() {
        if (keypressed) {
            perso.Move();
        }
    }
    
    public void checkCollisions() {
        Rectangle persoBox = perso.getBounds();
        for (int i = 0; i < Murs.size(); i++) {
            mur = (Murs.get(i));
            Rectangle murRec = mur.getBounds();
                                                                                      // ///// Collisions ///////
            if (persoBox.intersects(murRec)) {
                if (perso.getMov() == BAS || perso.getMov() == BAS_DROITE || perso.getMov() == BAS_GAUCHE) {
                    if (Map[(persoBox.x) / 32][(persoBox.y + 14) / 32] == "Mur") {
                        perso.setCol(COL_B);
                    } else {
                        perso.setCol(No_COL);
                    }
                }
                if (perso.getMov() == HAUT || perso.getMov() == HAUT_GAUCHE || perso.getMov() == HAUT_DROITE) {
                    if (Map[(persoBox.x) / 32][(persoBox.y - 50) / 32] == "Mur") {
                        perso.setCol(COL_H);
                    } else {
                        perso.setCol(No_COL);
                    }
                }
                if (perso.getMov() == GAUCHE || perso.getMov() == HAUT_GAUCHE || perso.getMov() == BAS_GAUCHE) {
                    if (Map[(persoBox.x - 32) / 32][(persoBox.y) / 32] == "Mur") {
                        perso.setCol(COL_G);
                    } else {
                        perso.setCol(No_COL);
                    }

                }
                if (perso.getMov() == DROITE || perso.getMov() == HAUT_DROITE || perso.getMov() == BAS_DROITE) {
                    if (Map[(persoBox.x + 32) / 32][(persoBox.y) / 32] == "Mur") {
                        perso.setCol(COL_D);
                    } else {
                        perso.setCol(No_COL);
                    }
                }
            }

        }
                                                                                        // /////*** Collisions ***////////
        for (int i = 0; i < Caisses.size(); i++) {
            caisse = (Caisse) Caisses.get(i);
            Rectangle caisseRec = caisse.getBounds();

            if (persoBox.intersects(caisseRec)) {
                if (perso.getMov() == BAS) {
                    if (Map[caisseRec.x / 32][(caisseRec.y + 32) / 32] != "Mur") {
                        caisse.sety(caisse.gety() + 8);
                    } else {
                        perso.setCol(COL_B);
                    }
                }

                if (perso.getMov() == HAUT) {
                    if (Map[caisseRec.x / 32][(caisseRec.y - 32) / 32] != "Mur") {
                        caisse.sety(caisse.gety() - 8);
                    } else {
                        perso.setCol(COL_H);
                    }
                }

                if (perso.getMov() == GAUCHE) {
                    if (Map[(caisseRec.x - 32) / 32][(caisseRec.y) / 32] != "Mur") {
                        caisse.setx(caisse.getx() - 8);
                    } else {
                        perso.setCol(COL_G);
                    }
                }

                if (perso.getMov() == DROITE) {
                    if (Map[(caisseRec.x - 32) / 32][(caisseRec.y + 32) / 32] != "Mur") {
                        caisse.setx(caisse.getx() + 8);
                    } else {
                        perso.setCol(COL_D);
                    }
                }
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
        final int keyup = KeyEvent.VK_UP;
        final int keyup2 = KeyEvent.VK_W;
        final int keydown = KeyEvent.VK_DOWN;
        final int keydown2 = KeyEvent.VK_S;
        final int keyL = KeyEvent.VK_LEFT;
        final int keyL2 = KeyEvent.VK_A;
        final int keyR = KeyEvent.VK_RIGHT;
        final int keyR2 = KeyEvent.VK_D;
        if (keypressed) {
            switch (old_key) {
                case keyup:
                    if (Key == keyL ||Key == keyL2)
                        perso.setMov(HAUT_GAUCHE);
                    else if (Key == keyR || Key == keyR2)
                        perso.setMov(HAUT_DROITE);
                    break;
                case keydown:
                    if (Key == keyL || Key == keyL2)
                        perso.setMov(BAS_GAUCHE);
                    else if (Key == keyR || Key == keyR2)
                        perso.setMov(BAS_DROITE);
                    break;
                case keyL:
                    if (Key == keyup || Key == keyup2)
                        perso.setMov(HAUT_GAUCHE);
                    else if (Key == keydown || Key == keydown2)
                        perso.setMov(BAS_GAUCHE);
                    break;
                case keyR:
                    if (Key == keyup || Key == keyup2)
                        perso.setMov(HAUT_DROITE);
                    else if (Key == keydown || Key == keydown2)
                        perso.setMov(BAS_DROITE);
                    break;
                case keyup2:
                    if (Key == keyL ||Key == keyL2)
                        perso.setMov(HAUT_GAUCHE);
                    else if (Key == keyR || Key == keyR2)
                        perso.setMov(HAUT_DROITE);
                    break;
                case keydown2:
                    if (Key == keyL || Key == keyL2)
                        perso.setMov(BAS_GAUCHE);
                    else if (Key == keyR || Key == keyR2)
                        perso.setMov(BAS_DROITE);
                    break;
                case keyL2:
                    if (Key == keyup || Key == keyup2)
                        perso.setMov(HAUT_GAUCHE);
                    else if (Key == keydown || Key == keydown2)
                        perso.setMov(BAS_GAUCHE);
                    break;
                case keyR2:
                    if (Key == keyup || Key == keyup2)
                        perso.setMov(HAUT_DROITE);
                    else if (Key == keydown || Key == keydown2)
                        perso.setMov(BAS_DROITE);
                    break;
                case KeyEvent.VK_SHIFT:
                    vitesse = 2;
                    System.out.println(vitesse);
            }

        } else {
            if (Key == keydown || Key == keydown2) {
                perso.setMov(BAS);
            } else if (Key == keyup || Key == keyup2) {
                perso.setMov(HAUT);
            } else if (Key == keyR || Key == keyR2) {
                perso.setMov(DROITE);
            } else if (Key == keyL || Key == keyL2) {
                perso.setMov(GAUCHE);
            }
            old_key = Key;
        }
        keypressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int Key = e.getKeyCode();
        keypressed = false;
        if (Key == KeyEvent.VK_SHIFT) {
            vitesse = 5;
        }
        if (Key == KeyEvent.VK_R) {
            t.shutdown();
            ChargementLevel();
        }
        if (Key == KeyEvent.VK_SPACE) {
            Level++;
            t.shutdown();
            ChargementLevel();
        }
        if (Key == KeyEvent.VK_T) {
            perso.setHP(perso.getHP() - 10);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (e.getButton() == MouseEvent.BUTTON1) {
            skill.affiche();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Mx = e.getX() - 20;
        My = e.getY() - 30;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rot = e.getWheelRotation();
        if (rot < 0)
            pointeur.incImg();
        else if (rot > 0)
            pointeur.decImg();
    }
  
}
