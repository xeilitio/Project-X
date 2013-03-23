package Jeu;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class Splash extends JWindow {
    
    public Splash() {
        JPanel panel = new JPanel();
        Icon img = new ImageIcon("Images/Splash");
        JLabel image = new JLabel();
        image.setIcon(img);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(image);
        panel.add(Box.createHorizontalGlue());
        setSize(img.getIconWidth(), img.getIconHeight());
        setLocationRelativeTo(null);
        getContentPane().add(panel);
        setVisible(true);
        Color alpha = new Color(255,0,0,0);
        panel.setBackground(alpha);
        panel.setForeground(alpha);
        repaint();

                    }
}
