

import java.util.Arrays;

public class JeuV2 {
    public static void titre() {

        System.out.println(" _______  ______   _______  _______  _        _______  _        _______ \n" +
                "(  ___  )(  ___ \\ (  ___  )(  ____ \\( \\      (  ___  )( (    /|(  ____ \\\n" +
                "| (   ) || (   ) )| (   ) || (    \\/| (      | (   ) ||  \\  ( || (    \\/\n" +
                "| (___) || (__/ / | (___) || |      | |      | |   | ||   \\ | || (__    \n" +
                "|  ___  ||  __ (  |  ___  || |      | |      | |   | || (\\ \\) ||  __)   \n" +
                "| (   ) || (  \\ \\ | (   ) || |      | |      | |   | || | \\   || (      \n" +
                "| )   ( || )___) )| )   ( || (____/\\| (____/\\| (___) || )  \\  || (____/\\\n" +
                "|/     \\||/ \\___/ |/     \\|(_______/(_______/(_______)|/    )_)(_______/\n" +
                "                                                                        ");
    }

    public static void plateau() {
        System.out.println(
                "    ┌─────────┐    \n" +
                        "   ┌┘© © © © ©└┐   \n" +
                        "  ┌┘□ © © © © □└┐  \n" +
                        " ┌┘□ □ © © © □ □└┐ \n" +
                        "┌┘□ □ □ □ □ □ □ □└┐\n" +
                        "│□ □ □ □ □ □ □ □ □│\n" +
                        "└┐□ □ □ □ □ □ □ □┌┘\n" +
                        " └┐□ □ ✱ ✱ ✱ □ □┌┘ \n" +
                        "  └┐□ ✱ ✱ ✱ ✱ □┌┘  \n" +
                        "   └┐✱ ✱ ✱ ✱ ✱┌┘   \n" +
                        "    └─────────┘    ");
    }
    public static char caractereJ1() {
        return '✱';
    }


    public static char caractereJ2() {
        return '©';
    }



    public static char caractereCase() {
        return '□' ;
    }

    public static void afficherTab2D(byte[][] tab){
        for(int i = 0; i < tab.length; i++){
            System.out.println(Arrays.toString(tab[i]));
        }
    }


    public static byte[][] creerTabPlateau(){
        // en byte psk la ram ça coûte cher !!
        byte[] longueur = {5, 6, 7, 8, 9, 8, 7, 6, 5};
        byte[][] plateau = new byte[9][];
        for (int i = 0; i < 9; i++) {
            plateau[i] = new byte[longueur[i]];
        }
        return plateau;
    }
    public static void remplirTabPlateau(byte[][] plateau){
        int index = 3;
        // remplir joueur 2
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = ligne; colonne < (plateau[ligne].length - ligne) ; colonne++) {
                plateau[ligne][colonne] = 2;
            }
        }
        // remplir joueur 1
        for (int ligne = plateau.length - 1; ligne > 3; ligne--) {
            index--;
            for (int colonne = ligne; colonne < (plateau[ligne].length + ligne); colonne++) {
                plateau[ligne][colonne] = 1;
            }
        }

    }


    public static void afficherPlateau(byte[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            System.out.println("");
            for (int colonne = 0; colonne < tab[ligne].length; colonne++) {
                switch (tab[ligne][colonne]) {
                    case 0:
                        System.out.print(caractereCase());
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print(caractereJ1());
                        System.out.print(" ");
                        break;
                    case 2:
                        System.out.print(caractereJ2());
                        System.out.print(" ");
                }
            }
        }
    }
}

