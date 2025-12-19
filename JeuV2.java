import java.util.Scanner;

import java.util.Arrays;

public class JeuV2 {
    public static Scanner sc = new Scanner(System.in);

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



    public static char caractereJ1() {
        return '✱';
    }
    public static String jaune() {
        return "\u001B[33m";
    }

    public static char caractereJ2() {
        return '©';
    }

    public static char caractereCase() {
        return '□';
    }

    public static char caractereChoix() {
        return 'X';
    }

    public static char caracterePosible() {
        return '▲';
    }


    public static void afficherTab2D(byte[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.println(Arrays.toString(tab[i]));
        }
        System.out.println("");
    }


    public static byte[][] creerTabPlateau() {
        // en byte psk la ram ça coûte cher !!
        byte[] longueur = {5, 6, 7, 8, 9, 8, 7, 6, 5};
        byte[][] plateau = new byte[9][];
        for (int i = 0; i < 9; i++) {
            plateau[i] = new byte[longueur[i]];
        }
        return plateau;
    }

    public static void remplirTabPlateau(byte[][] plateau) {
        int index = 0;
        // remplir joueur 2

        for (int colonne = 0; colonne < plateau[0].length; colonne++) {
            plateau[0][colonne] = 2;
        }
        for (int colonne = 0; colonne < plateau[1].length; colonne++) {
            plateau[1][colonne] = 2;
        }
        for (int colonne = 2; colonne < (plateau[2].length - 2); colonne++) {
            plateau[2][colonne] = 2;
        }


        // remplir joueur 1
        for (int colonne = 0; colonne < plateau[8].length; colonne++) {
            plateau[8][colonne] = 1;
        }
        for (int colonne = 0; colonne < plateau[7].length; colonne++) {
            plateau[7][colonne] = 1;
        }
        for (int colonne = 2; colonne < (plateau[6].length - 2); colonne++) {
            plateau[6][colonne] = 1;
        }


    }

    public static void finChoix(byte[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colonne = 0; ligne < tab[ligne].length; colonne++) {
                // si dans le tableau la case est en choix pour le joueur 1 (3) alors il le remet normalement (1)
                if (tab[ligne][colonne] == 3) {
                    tab[ligne][colonne] = 1;
                }
                // si dans le tableau la case est en choix pour le joueur 2 (4) alors il le remet normalement (2)
                else if (tab[ligne][colonne] == 4) {
                    tab[ligne][colonne] = 2;
                }

            }
        }
    }

    public static int ChoixNombreBille(byte[][] tab, int joueur) {
        int nbBille;
        do {
            System.out.println("Combien de bille(s) voulez vous bouger : ");
            nbBille = sc.nextInt();
        } while (nbBille < 1 || nbBille > 3);
        return nbBille;
    }

    public static boolean estSurLePlateau(byte[][] tab, int ligne, int colonne) {
        if (ligne < 1 || ligne > 9 || colonne < 1 || colonne > tab[ligne].length) return false;
        return true;
    }

    public static void Choix1(byte[][] tab, int joueur, int[] coord) {
        int ligne, colonne;
        do {
            System.out.println("Ligne de la bille que vous voulez bouger : ");
            ligne = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la bille que vous voulez bouger : ");
            colonne = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, ligne, colonne) || tab[ligne - 1][colonne - 1] != joueur);
        switch (joueur) {
            case 1:
                tab[ligne - 1][colonne - 1] = 3;
                break;
            case 2:
                tab[ligne - 1][colonne - 1] = 4;
                break;
            default:
                break;
        }
        coord[0] = colonne - 1;
        coord[1] = ligne - 1;
    }
   /* public static boolean PeutBouger(byte[][] tab, int joueur, int ligne, int colonne) {
        boolean peutJouer = false;
        boolean[] possibilitees = chercherPossiblite1Bille(tab, colonne, ligne);
        for (int i = 0; i < possibilitees.length; i++) {
            if (possibilitees[i]) {
                possibilitees[i] = true;
            }
        }
        return peutJouer;
    }*/


    public static void Choix2(byte[][] tab, int joueur) {
        int l1, c1, l2, c2;
        do {
            System.out.println("Ligne de la 1ere bille que vous voulez bouger : ");
            l1 = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la 1ere bille que vous voulez bouger : ");
            c1 = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, l1, c1) || tab[l1 - 1][c1 - 1] != joueur);
        do {
            System.out.println("Ligne de la 2e bille que vous voulez bouger : ");
            l2 = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la 2e bille que vous voulez bouger : ");
            c2 = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, l2, c2) || tab[l2 - 1][c2 - 1] != joueur || (l2 == l1 && c2 == c1));

        switch (joueur) {
            case 1:
                tab[l1 - 1][c1 - 1] = 3;
                tab[l2 - 1][c2 - 1] = 3;
                break;
            case 2:
                tab[l1 - 1][c1 - 1] = 4;
                tab[l2 - 1][c2 - 1] = 4;
                break;
            default:
                break;
        }

    }

    public static void Choix3(byte[][] tab, int joueur) {
        int l1, c1, l2, c2, l3, c3;
        do {
            System.out.println("Ligne de la 1ere bille que vous voulez bouger : ");
            l1 = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la 1ere bille que vous voulez bouger : ");
            c1 = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, l1, c1) || tab[l1 - 1][c1 - 1] != joueur);
        do {
            System.out.println("Ligne de la 2e bille que vous voulez bouger : ");
            l2 = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la 2e bille que vous voulez bouger : ");
            c2 = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, l1, c2) || tab[l2 - 1][c2 - 1] != joueur || (l2 == l1 && c2 == c1));
        do {
            System.out.println("Ligne de la 3e bille que vous voulez bouger : ");
            l3 = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la 3e bille que vous voulez bouger : ");
            c3 = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, l1, c3) || tab[l3 - 1][c3 - 1] != joueur || (l3 == l2 && c3 == c2));

        switch (joueur) {
            case 1:
                tab[l1 - 1][c1 - 1] = 3;
                tab[l2 - 1][c2 - 1] = 3;
                tab[l3 - 1][c3 - 1] = 3;
                break;
            case 2:
                tab[l1 - 1][c1 - 1] = 4;
                tab[l2 - 1][c2 - 1] = 4;
                tab[l3 - 1][c3 - 1] = 4;
                break;
            default:
                break;
        }
    }

    public static int coordPourIndex(int position) {
        return position - 1;
    }

    public static int IndexPourCoord(int position) {
        return position + 1;
    }


    public static void afficherPlateau(byte[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            System.out.println("");
            for (int espace = 0; espace < 9 - tab[ligne].length; espace++) {
                System.out.print(" ");
            }
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
                        break;
                    case 3:
                        System.out.print(caractereChoix());
                        System.out.print(" ");
                        break;
                    case 4:
                        System.out.print(caractereChoix());
                        System.out.print(" ");
                        break;

                    case 5:
                        System.out.print(caracterePosible());
                        System.out.print(" ");
                        break;


                    default:
                        break;

                }
            }
        }
        System.out.println("");
    }

    public static byte[][] Decalages(byte[][] tab, int y) {
        byte[][] decalages;
        if (y < 4) {
            decalages = new byte[][] {{-1, -1}, {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {0, -1}};
        } else if (y == 4) {
            decalages = new byte[][] {{-1, -1}, {-1, 0}, {0, 1}, {1, 0}, {1, -1}, {0, -1}};
        } else {
            decalages = new byte[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {1, -1}, {0, -1}};
        }
        return decalages;
    }

    public static boolean[] chercherPossiblite1Bille(byte[][] tab, int joueur, int x, int y) {

        // [0] = 🡷, [1] = 🡰 , [2] = 🡴 , [3] = 🡵 , [4] = 🡲 , [5] = 🡶 .
        boolean[] possibilites = new boolean[6];
        byte[][] decalages = Decalages(tab, y);
        for (int i = 0; i < 6; i++) {
            int dy = decalages[i][0];
            int dx = decalages[i][1];
            int newY = y + dy;
            int newX = x + dx;
            // Si la case voisine est valide et vide (0)
            if (estSurLePlateau(tab, newY, newX) && tab[newY][newX] == 0 || tab[newY][newX] != joueur) {
                possibilites[i] = true;
            }
        }
        return possibilites;
    }
    /*
    public static boolean[] chercherPossiblite1Bille(byte[][] tab, int x, int y) {

    // [0] = 🡷, [1] = 🡰 , [2] = 🡴 , [3] = 🡵 , [4] = 🡲 , [5] = 🡶 .
    boolean[] possibilites = new boolean[6];

    int maxY = tab.length;
    int maxX = tab[0].length;

    // Bas-gauche
    if (y - 1 >= 0 && tab[y - 1][x] == 0) {
    possibilites[0] = true;
    }

    // Gauche
    if (x - 1 >= 0 && tab[y][x - 1] == 0) {
    possibilites[1] = true;
    }

    // Bas
    if (y + 1 < maxY && tab[y + 1][x] == 0) {
    possibilites[2] = true;
    }

    // Bas-droite
    if (y + 1 < maxY && x + 1 < maxX && tab[y + 1][x + 1] == 0) {
    possibilites[3] = true;
    }

    // Droite
    if (x + 1 < maxX && tab[y][x + 1] == 0) {
    possibilites[4] = true;
    }

    // Haut-droite
    if (y - 1 >= 0 && x + 1 < maxX && tab[y - 1][x + 1] == 0) {
    possibilites[5] = true;
    }

    return possibilites;
    }



    public static void PossibilitéTab(byte[][] tab, boolean[] choix, int x , int y) {
    for (int index = 0 ; index < choix.length; index++) {
    if (choix[index]) {
    switch (index) {
    case 0:
    tab[y-1][x] = 5;
    break;
    case 1:
    tab[y][x-1] = 5;
    break;
    case 2:
    tab[y+1][x] = 5;
    break;
    case 3:
    tab[y+1][x+1] = 5;
    break;
    case 4:
    tab[y][x+1] = 5;
    break;
    case 5:
    tab[y+1][x-1] = 5;
    break;


    }
    }
    }
    }*/

    public static void PossibilitéTab(byte[][] tab, boolean[] choix, int x, int y) {
        byte[][] decalages;
        decalages = Decalages(tab, y);
        for (int i = 0; i < choix.length; i++) {
            if (choix[i]) {
                int dy = decalages[i][0];
                int dx = decalages[i][1];
                // On place le symbole ▲ (5)
                if (estSurLePlateau(tab, x + dx, y + dy)) {
                    tab[y + dy][x + dx] = 5;
                }
            }
        }
    }

    public static int[] choisirBouger(byte[][] tab, int joueur) {
        int l, c;
        int[] coord = new int[2];
        do {
            System.out.println("Ligne de la direction : ");
            l = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la direction : ");
            c = sc.nextInt(); // choix de la colonne du joueur
        } while ((tab[l - 1][c - 1] == 3 && joueur == 1) || (tab[l - 1][c - 1] == 4 && joueur == 2) || !estSurLePlateau(tab, l, c));

        coord[0] = l;
        coord[1] = c;
        return coord;
    }

    public static void bouger1Bille(byte[][] tab, int[] posJoueur /*int[] target*/ ) {
        /* tab[target[0]][target[1]] = tab[posJoueur[0]][posJoueur[1]];
        tab[posJoueur[1]][posJoueur[0]] = 0;
        enleverDirection(tab);*/
        int direction;
        do {
            System.out.println("\nChoisissez une direction : ");
            direction = sc.nextInt();
        } while (direction < 0 || direction > 5);
        int y = posJoueur[0];
        int x = posJoueur[1];
        int[][] decalages;
        if (y < 4) {
            decalages = new int[][] {{1, 0}, {0, -1}, {-1, -1}, {-1, 0}, {0, 1}, {1, 1}};
        }
        else if (y == 4) {
            decalages = new int[][]{{1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {0, 1}, {1, 0}};
        }
        else {
            decalages = new int[][] {{1, -1}, {0, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 0}};
        }
        int dy = decalages[direction][0];
        int dx = decalages[direction][1];
        int newY = y + dy;
        int newX = x + dx;

        if (estSurLePlateau(tab, newY, newX)) {
            tab[newY][newX] = tab[y][x];
            tab[y][x] = 0;
        }

        enleverDirection(tab);
    }




    public static void enleverDirection(byte[][] tab) {
        // enleve la prévisualisation des possiblitées
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colone = 0; colone < tab[ligne].length; colone++) {
                if (tab[ligne][colone] == 5) {
                    tab[ligne][colone] = 0;
                }
            }
        }
    }


    public static int nbPossibilitées(boolean[] tab) {
        // renvois le nombre de possibilitées d'un tableau d'actions
        int nb = 0;
        for (boolean possibilitées: tab) {
            if (possibilitées) {
                nb++;
            }

        }
        return nb;
    }
    public static void afficherChoix() {

    }

    public static boolean peutPousser2(byte[][] tab, int[] target, int index) {
        tab[target[1]][target[0]] = 4;
        switch (index) {
            case 0:
                if (target[1] == 7) {
                    return true;
                } else {
                    return tab[target[1] + 1][target[0] - 1] == 0;
                }

            case 1:
                if (target[0] == 0 /* || target[0] == 0*/ ) {
                    return true;
                } else {
                    return tab[target[1]][target[0] - 1] == 0;
                }
            case 2:
                if (target[1] == 0 || target[0] == 0) {
                    return true;
                } else {
                    return tab[target[1] - 1][target[0] - 1] == 0;
                }
            case 3:
                if (target[1] == 0 || target[0] == tab[target[1] + 1].length - 1) {
                    return true;
                } else {
                    return tab[target[1] - 1][target[0]] == 0;
                }
            case 4:
                if (target[0] == tab[target[1] + 1].length - 1) {
                    return true;
                } else {
                    return tab[target[1]][target[0] + 1] == 0;
                }
            case 5:
                if (target[1] == 7 || target[0] == tab[target[1]].length - 1) {
                    return true;
                } else {
                    return tab[target[1] + 1][target[0]+ 1] == 0;
                }



        }

        return false;
    }
    public static boolean peutPousser3(byte[][] tab, int[] target, int index , int player) {
        //vérifie si l'action pousser est possible pour 3 billes fortnite.
        int enemie;
        switch (player) {
            case 1:
                enemie = 2;
                break;
            case 2:
                enemie = 1;
            default:
                break;
        }
        tab[target[1]][target[0]] = 4;

        switch (index) {
            case 0:

                return tab[target[1] + 1][target[0] - 1] == 0;

            case 1:
                if (target[0] == 0 || target[0] == 0) {
                    return true;
                } else {
                    return tab[target[1]][target[0] - 1] == 0;
                }
            case 2:
                if (target[1] == 0 || target[0] == 0) {
                    return true;
                } else {
                    return tab[target[1] - 1][target[0] - 1] == 0;
                }
            case 3:
                if (target[1] == 0 || target[0] == tab[target[1] + 1].length - 1) {
                    return true;
                } else {
                    return tab[target[1] - 1][target[0]] == 0;
                }
            case 4:
                if (target[0] == tab[target[1] + 1].length - 1) {
                    return true;
                } else {
                    return tab[target[1]][target[0] + 1] == 0;
                }
            case 5:
                if (target[1] == 7 || target[0] == tab[target[1]].length - 1) {
                    return true;
                } else {
                    return tab[target[1] + 1][target[0]+ 1] == 0;
                }



        }

        return false;
    }

}
