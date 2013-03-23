package Jeu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Choix extends JFrame{

    private JLabel l1 = new JLabel();
    private JPanel pan = new JPanel();
    private JButton jeu = new JButton("Jeu");
    private JButton editeur = new JButton("Editeur");
   


    public Choix(){

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Project X");
        this.setSize(450, 250);
        this.setResizable(false);

        Font police = new Font("SansSerif", Font.BOLD, 30);
        Font police2 = new Font("SansSerif", Font.BOLD, 20);
        jeu.setFont(police2);
        editeur.setFont(police2);

        pan.setLayout(new GridLayout());
        pan.add(jeu);
        pan.add(editeur);
        this.add(pan, BorderLayout.CENTER);
        this.add(l1, BorderLayout.NORTH);

        l1.setFont(police);
        l1.setText("Menu");
        l1.setHorizontalAlignment(JLabel.CENTER);
        jeu.addActionListener(new BoutonListener());
        editeur.addActionListener(new Bouton2Listener());
    }         
    class BoutonListener implements ActionListener{
        //Redéfinition de la méthode actionPerformed()
        @SuppressWarnings("unused")
        public void actionPerformed(ActionEvent arg0) {
            dispose();
            packageGame.Fenetre jeu = new packageGame.Fenetre();
        }
    }

    //Classe écoutant notre second bouton
    class Bouton2Listener implements ActionListener{
        //Redéfinition de la méthode actionPerformed()
        @SuppressWarnings("unused")
        public void actionPerformed(ActionEvent e) {
            dispose();
            packageEditor.FenetreEditeur editeur = new packageEditor.FenetreEditeur();
        }
    }      
}
//