package packageGame;

import Jeu.Splash;

public class Main {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Splash splash = new Splash();
        try {
       //     Thread.sleep(3500);
            splash.dispose();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Fenetre jeu = new Fenetre();
    }

}
