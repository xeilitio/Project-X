package packageGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class BarreHP extends JPanel implements Runnable {
    Perso perso;
    JPanel panelPerso = new JPanel();
    JPanel panelPrincipal = new JPanel();
    int hp;
    JProgressBar HP_BarPerso;
    JProgressBar HP_Bar;
    private Dimension d;
    Plateau pl;
    boolean VisiBar = true;
    
    public BarreHP(Perso p, Plateau p2)
    {   pl = p2;
        perso = p;
        HP_BarPerso = new JProgressBar(0, perso.getHP());
        HP_Bar = new JProgressBar(0, perso.getHP());
        d = new Dimension(60,5);
    }

    public void run() {
        panelPerso.add(HP_BarPerso, BorderLayout.CENTER);
        panelPrincipal.add(HP_Bar);
        HP_BarPerso.setSize(d);
        HP_BarPerso.setPreferredSize(d);
        panelPrincipal.setBackground(Color.white);
        panelPrincipal.setLocation(0,0);
        pl.add(panelPrincipal, FlowLayout.LEFT);
        pl.add(panelPerso);
        while (true){
        HP_BarPerso.setValue(perso.getHP());
        HP_Bar.setValue(perso.getHP());
        panelPerso.setLocation(perso.getx() - 20, perso.gety() - 15);
        if(VisiBar == false){
            HP_BarPerso.setVisible(false);
        }
        }
    }
    
    public void setVisiBar(boolean v){
        VisiBar = v;
    }
    
}
