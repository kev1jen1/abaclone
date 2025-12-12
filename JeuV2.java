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
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = ligne; colonne < (plateau[ligne].length - ligne); colonne++) {
                plateau[ligne][colonne] = 2;
            }
        }
        // remplir joueur 1
        for (int ligne = plateau.length - 1; ligne >= 6; ligne--) {

            for (int colonne = index; colonne < (plateau[ligne].length - index); colonne++) {
                plateau[ligne][colonne] = 1;
            }
            index++;
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

    public static void Choix1(byte[][] tab, int joueur) {
        int ligne, colonne;
        do {
                    System.out.println("Ligne de la bille que vous voulez bouger : ");
                    ligne = sc.nextInt(); // choix de la ligne du joueur
                    System.out.println("Colonne de la bille que vous voulez bouger : ");
                    colonne = sc.nextInt(); // choix de la colonne du joueur
                } while (!estSurLePlateau(tab, ligne, colonne) || tab[ligne-1][colonne-1] != joueur);
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
    }

    public static void Choix2(byte[][] tab, int joueur) {
        int l1, c1, l2, c2;
        do {
            System.out.println("Ligne de la 1ere bille que vous voulez bouger : ");
            l1 = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la 1ere bille que vous voulez bouger : ");
            c1 = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, l1, c1) || tab[l1-1][c1-1] != joueur);
        do {
            System.out.println("Ligne de la 2e bille que vous voulez bouger : ");
            l2 = sc.nextInt(); // choix de la ligne du joueur
            System.out.println("Colonne de la 2e bille que vous voulez bouger : ");
            c2 = sc.nextInt(); // choix de la colonne du joueur
        } while (!estSurLePlateau(tab, l1, c2) || tab[l2-1][c2-1] != joueur ||tab[l2-1][c2-1] == tab[l1-1][c1-1]);

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

    public static boolean[] chercherPossiblité1Bille(byte[][] tab, int x, int y) {
        // [0] = 🡷, [1] = 🡰 , [2] = 🡴 , [3] = 🡵 , [4] = 🡲 , [5] = 🡶 .
        boolean[] possibilitées = new boolean[5];
        for (int index = 0; index < tab.length; index++) {
            possibilitées[index] = false;
        }
        if (y < 8 && tab[y - 1][x] == 0) {
            possibilitées[0] = true;
        }
        if (x > 0 && tab[y][x - 1] == 0) {
            possibilitées[1] = true;
        }
        if (y > 0 && tab[y + 1][x] == 0) {
            possibilitées[2] = true;
        }
        if (y > 0 && x + 1 <= tab[y].length && tab[y + 1][x + 1] == 0) {
            possibilitées[3] = true;
        }
        if (x < 8 && tab[y][x + 1] == 0) {
            possibilitées[4] = true;
        }
        if (y < 8 && x + 1 <= tab[y].length && tab[y - 1][x + 1] == 0) {
            possibilitées[5] = true;
        }

        return possibilitées;
    }
    public static void PossibilitéTab(byte[][] tab, boolean[] choix, int x , int y) {
      for (int index = 0 ; index < choix.length; index++) {
          if (choix[index]) {
              switch (index) {
                  case 0:
                      tab[y-1][y] = 5;
                      break;
                  case 1:
                      tab[y][x-1] = 5;
                      break;
                  case 2:
                      tab[y-1][y] = 5;
                      break;
                  case 3:
                      tab[y-1][y] = 5;
                      break;
                  case 4:
                      tab[y-1][y] = 5;
                      break;
                  case 5:
                      tab[y-1][y] = 5;
                      break;


              }
          }
      }
    }


}

