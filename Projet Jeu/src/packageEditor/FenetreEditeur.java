package packageEditor;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FenetreEditeur extends JFrame{
    public FenetreEditeur(){
        this.setTitle("WorldEditor");
        this.setSize(614, 508);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new Editeur());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
