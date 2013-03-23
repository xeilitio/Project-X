package Jeu;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainJeu {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        Splash splash = new Splash();
        try {
           // Thread.sleep(1500);
            splash.dispose();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Choix choix = new Choix();
    }

}
