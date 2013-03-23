package packageGame;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Fenetre extends JFrame {
    public Fenetre(){
        this.setTitle("Project X : level "+ Plateau.Level);
        this.setSize(614, 508);
        this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
    	this.add(new Plateau());
    	this.setVisible(true);
    	
	}    
}
