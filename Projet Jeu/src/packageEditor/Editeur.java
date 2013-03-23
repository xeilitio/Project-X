package packageEditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Editeur extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    String Editeur[][] = new String[20][15];
    String ImageSelect[] = { "Mur", "Caisse", "Perso1", "Objectif", "Bat" };
    String ImageCourante = "Mur";
    int Mx, My;
    int indexInc = 0;
    Image Perso1;
    Image Mur;
    Image Caisse;
    Image Objectif;
    Image Bat;
    FileWriter fw;
    FileReader fr;

    public Editeur() {
        ImageIcon iPerso1 = new ImageIcon("Images/PersoGBas.PNG");
        Perso1 = iPerso1.getImage();

        ImageIcon iCaisse = new ImageIcon("Images/Caisse.PNG");
        Caisse = iCaisse.getImage();

        ImageIcon iMur = new ImageIcon("Images/Mur.PNG");
        Mur = iMur.getImage();

        ImageIcon iObjectif = new ImageIcon("Images/Objectif.Gif");
        Objectif = iObjectif.getImage();

        ImageIcon iBat = new ImageIcon("Images/Bat.gif");
        Bat = iBat.getImage();
        
        setFocusable(true);

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i <= 19; i++) {
            for (int j = 0; j <= 14; j++) {
                if (Editeur[i][j] == "Mur") {
                    g2d.drawImage(Mur, i * 32, j * 32, null);
                }
                if (Editeur[i][j] == "Caisse") {
                    g2d.drawImage(Caisse, i * 32, j * 32, null);
                }
                if (Editeur[i][j] == "Perso1") {
                    g2d.drawImage(Perso1, i * 32, j * 32 - 20, null);
                }
                if (Editeur[i][j] == "Objectif") {
                    g2d.drawImage(Objectif, i * 32, j * 32, null);
                }
                if (Editeur[i][j] == "Bat") {
                    g2d.drawImage(Bat, i * 32, j * 32, null);
                }
            }
        }

        if (ImageCourante == "Mur") {
            g2d.drawImage(Mur, Mx, My, null);
        } else if (ImageCourante == "Caisse") {
            g2d.drawImage(Caisse, Mx, My, null);
        } else if (ImageCourante == "Perso1") {
            g2d.drawImage(Perso1, Mx, My - 10, null);
        } else if (ImageCourante == "Objectif") {
            g2d.drawImage(Objectif, Mx, My, null);
        }else if (ImageCourante == "Bat"){
            g2d.drawImage(Bat, Mx, My, null);
        }
        repaint();  
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 's') {
            try {
                fw = new FileWriter(JOptionPane.showInputDialog(null, "Entrez le chemin de sauvegarde :", "Editeur",JOptionPane.QUESTION_MESSAGE));
                for (int j = 0; j < 15; j++) {
                    for (int i = 0; i < 20; i++) {
                        if (Editeur[i][j] == "Perso1") {
                            fw.write("1");
                        } else if (Editeur[i][j] == "Caisse") {
                            fw.write("2");
                        } else if (Editeur[i][j] == "Objectif") {
                            fw.write("3");
                        }else if (Editeur[i][j] == "Mur") {
                                fw.write("0");
                        } else if (Editeur[i][j] == null) {
                            fw.write(" ");
                        }else if (Editeur[i][j] == "Bat") {
                            fw.write("B");
                        }
                    }
                    fw.write("\r\n");
                }
                fw.close();
            } catch (Exception e1) {}
        } else if (key == '1'){
            try{
                fr = new FileReader(JOptionPane.showInputDialog(null, "Entrez le chemin de lecture :", "Editeur",JOptionPane.QUESTION_MESSAGE));
                int i = 0;
                int x = 0,y = 0;
                
                while ((i = fr.read()) != -1){
                    char strImg = (char) i;
                    
                    if (strImg == '0'){
                        Editeur [x][y] = "Mur";
                    }
                    else if (strImg == '1'){
                        Editeur [x][y] = "Perso1";
                    }
                    else if (strImg == '2'){
                        Editeur [x][y] = "Caisse";
                    }
                    else if (strImg == '3'){
                        Editeur [x][y] = "Objectif";
                    }
                    else if (strImg == 'B'){
                        Editeur [x][y] = "Bat";
                    }
                    else if (strImg == ' '){
                        Editeur [x][y] = null;
                    }
                    else if (strImg == '\r' || strImg == '\n'){
                        x--;
                    }
                    
                    if (x == 19){
                        y++;
                        x = 0;
                    }
                    else {
                        x++;
                    }
                }
                
            }catch (Exception e1){}
        }
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0) {
        int rot = arg0.getWheelRotation();

        if (rot < 0) {
            if (indexInc > 0) {
                indexInc--;
            }
        } else if (rot > 0) {
            if (indexInc < 4)
                indexInc++;
        }
        ImageCourante = ImageSelect[indexInc];
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        Mx = arg0.getX() - 16;
        My = arg0.getY() - 16;
        if (ImageCourante == "Perso1")
            My = arg0.getY() - 20;

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        int x = arg0.getX() / 32;
        int y = arg0.getY() / 32;

        if (arg0.getButton() == MouseEvent.BUTTON1) {
            Editeur[x][y] = ImageCourante;
        } else if (arg0.getButton() == MouseEvent.BUTTON3) {
            Editeur[x][y] = null;
        }
    }
}
