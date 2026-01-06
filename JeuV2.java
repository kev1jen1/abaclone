import java.util.Scanner;
import java.util.Arrays;

public class JeuV2 {
    public static Scanner sc = new Scanner(System.in);

    // --- AFFICHAGE ET OUTILS ---

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
        return '●';
    }

    public static String jaune() {
        return "\u001B[33m";
    }

    public static char caractereJ2() {
        return '○';
    }

    public static char caractereCase() {
        return '·';
    }

    public static char caractereChoix() {
        return 'X';
    }

    public static char caracterePosible() {
        return '▲';
    }

    // --- GESTION PLATEAU ---

    public static byte[][] creerTabPlateau() {
        byte[] longueur = { 5, 6, 7, 8, 9, 8, 7, 6, 5 };
        byte[][] plateau = new byte[9][];
        for (int i = 0; i < 9; i++) {
            plateau[i] = new byte[longueur[i]];
        }
        return plateau;
    }

    public static void remplirTabPlateau(byte[][] plateau) {
        for (int i = 0; i < plateau.length; i++) {
            Arrays.fill(plateau[i], (byte) 0);
        }

        // Remplir joueur 2 (Haut)
        Arrays.fill(plateau[0], (byte) 2);
        Arrays.fill(plateau[1], (byte) 2);
        for (int colonne = 2; colonne < (plateau[2].length - 2); colonne++) {
            plateau[2][colonne] = 2;
        }

        // Remplir joueur 1 (Bas)
        Arrays.fill(plateau[8], (byte) 1);
        Arrays.fill(plateau[7], (byte) 1);
        for (int colonne = 2; colonne < (plateau[6].length - 2); colonne++) {
            plateau[6][colonne] = 1;
        }
    }

    public static void afficherPlateau(byte[][] tab) {
        System.out.println("");

        for (int ligne = 0; ligne < tab.length; ligne++) {
            System.out.print((ligne + 1) + " |  ");
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
                        System.out.print("\u001B[34m" + caractereJ1() + "\u001B[0m");
                        System.out.print(" ");
                        break;
                    case 2:
                        System.out.print("\u001B[31m" + caractereJ2() + "\u001B[0m");
                        System.out.print(" ");
                        break;
                    case 3:
                        System.out.print(jaune() + caractereChoix() + "\u001B[0m");
                        System.out.print(" ");
                        break;
                    case 4:
                        System.out.print(jaune() + caractereChoix() + "\u001B[0m");
                        System.out.print(" ");
                        break;
                    case 5:
                        System.out.print("\u001B[32m" + caracterePosible() + "\u001B[0m");
                        System.out.print(" ");
                        break;
                    default:
                        break;
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void finChoix(byte[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colonne = 0; colonne < tab[ligne].length; colonne++) {
                if (tab[ligne][colonne] == 3) {
                    tab[ligne][colonne] = 1;
                } else if (tab[ligne][colonne] == 4) {
                    tab[ligne][colonne] = 2;
                }
            }
        }
    }

    public static boolean estSurLePlateau(byte[][] tab, int ligne, int colonne) {
        if (ligne < 0 || ligne >= tab.length) {
            return false;
        }
        if (colonne < 0 || colonne >= tab[ligne].length) {
            return false;
        }
        return true;
    }

    public static void enleverDirection(byte[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colone = 0; colone < tab[ligne].length; colone++) {
                if (tab[ligne][colone] == 5) {
                    tab[ligne][colone] = 0;
                }
            }
        }
    }

    // --- FONCTIONS DE SAISIE ---

    public static int ChoixNombreBille(byte[][] tab, int joueur) {
        int nbBille = 0;
        boolean saisieValide = false;

        do {
            System.out.println("Combien de bille(s) voulez vous bouger (ou -1 pour retour) : ");

            if (sc.hasNextInt()) {
                nbBille = sc.nextInt();
                if (nbBille == -1) {
                    return -1;
                }
                if (nbBille >= 1 && nbBille <= 3) {
                    saisieValide = true;
                } else {
                    System.out.println("Erreur : Le nombre doit être entre 1 et 3.");
                }
            } else {
                sc.next();
                System.out.println("Erreur : Entier attendu.");
            }

        } while (saisieValide == false);

        return nbBille;
    }

    public static boolean Choix1(byte[][] tab, int joueur, int[] coord) {
        int ligne = 0;
        int colonne = 0;
        boolean choixValide = false;

        do {
            System.out.println("Ligne de la bille (-1 pour retour) : ");
            if (sc.hasNextInt()) {
                ligne = sc.nextInt();
            } else {
                sc.next();
                continue;
            }

            if (ligne == -1) {
                return false;
            }

            System.out.println("Colonne de la bille (-1 pour retour) : ");
            if (sc.hasNextInt()) {
                colonne = sc.nextInt();
            } else {
                sc.next();
                continue;
            }

            if (colonne == -1) {
                return false;
            }

            if (estSurLePlateau(tab, ligne - 1, colonne - 1)) {
                if (tab[ligne - 1][colonne - 1] == joueur) {
                    choixValide = true;
                } else {
                    System.out.println("Cette case ne vous appartient pas !");
                }
            } else {
                System.out.println("Case hors du plateau !");
            }

        } while (choixValide == false);

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

        return true;
    }

    public static boolean Choix2(byte[][] tab, int joueur) {
        int[] c1 = new int[2];
        int[] c2 = new int[2];
        boolean valide = false;

        do {
            System.out.println("--- Sélection Bille 1 ---");
            if (Choix1(tab, joueur, c1) == false) {
                return false;
            }
            afficherPlateau(tab);

            System.out.println("--- Sélection Bille 2 (Voisine) ---");
            if (Choix1(tab, joueur, c2) == false) {
                finChoix(tab);
                return false;
            }

            if (sontVoisines(c1[1], c1[0], c2[1], c2[0])) {
                valide = true;
            } else {
                System.out.println("Erreur : Les deux billes doivent être voisines !");
                finChoix(tab);
                afficherPlateau(tab);
            }

        } while (valide == false);

        return true;
    }

    public static boolean Choix3(byte[][] tab, int joueur) {
        int[] c1 = new int[2];
        int[] c2 = new int[2];
        int[] c3 = new int[2];
        boolean valide = false;

        do {
            System.out.println("--- Sélection Bille 1 ---");
            if (Choix1(tab, joueur, c1) == false) {
                return false;
            }
            afficherPlateau(tab);

            System.out.println("--- Sélection Bille 2 ---");
            if (Choix1(tab, joueur, c2) == false) {
                finChoix(tab);
                return false;
            }
            afficherPlateau(tab);

            System.out.println("--- Sélection Bille 3 ---");
            if (Choix1(tab, joueur, c3) == false) {
                finChoix(tab);
                return false;
            }

            if (sontAlignees(c1, c2, c3)) {
                valide = true;
            } else {
                System.out.println("Erreur : Les trois billes doivent former une ligne droite !");
                finChoix(tab);
                afficherPlateau(tab);
            }

        } while (valide == false);

        return true;
    }

    // --- OUTILS GEOMETRIQUES ---

    public static int[][] Decalages(int y) {
        if (y < 4) {
            // PARTIE HAUTE (Lignes grandissantes)
            return new int[][] {
                    { 1, 0 },   // 0: ↙ (Sud-Ouest)
                    { 0, -1 },  // 1: ← (Ouest)
                    { -1, -1 }, // 2: ↖ (Nord-Ouest)
                    { -1, 0 },  // 3: ↗ (Nord-Est)
                    { 0, 1 },   // 4: → (Est)
                    { 1, 1 }    // 5: ↘ (Sud-Est)
            };
        } else if (y == 4) {
            // LIGNE DU MILIEU (Pivot)
            return new int[][] {
                    { 1, -1 },  // 0: ↙ (Sud-Ouest)
                    { 0, -1 },  // 1: ← (Ouest)
                    { -1, -1 }, // 2: ↖ (Nord-Ouest)
                    { -1, 0 },  // 3: ↗ (Nord-Est)
                    { 0, 1 },   // 4: → (Est)
                    { 1, 0 }    // 5: ↘ (Sud-Est)
            };
        } else {
            // PARTIE BASSE (Lignes décroissantes)
            return new int[][] {
                    { 1, -1 },  // 0: ↙ (Sud-Ouest)
                    { 0, -1 },  // 1: ← (Ouest)
                    { -1, 0 },  // 2: ↖ (Nord-Ouest)
                    { -1, 1 },  // 3: ↗ (Nord-Est)
                    { 0, 1 },   // 4: → (Est)
                    { 1, 0 }    // 5: ↘ (Sud-Est)
            };
        }
    }

    public static boolean sontVoisines(int y1, int x1, int y2, int x2) {
        int[][] decalages = Decalages(y1);
        for (int i = 0; i < 6; i++) {
            if (y1 + decalages[i][0] == y2 && x1 + decalages[i][1] == x2) {
                return true;
            }
        }
        return false;
    }

    public static boolean sontAlignees(int[] p1, int[] p2, int[] p3) {
        int dir1_2 = getDirectionEntre(p1[1], p1[0], p2[1], p2[0]);
        int dir2_3 = getDirectionEntre(p2[1], p2[0], p3[1], p3[0]);
        int dir1_3 = getDirectionEntre(p1[1], p1[0], p3[1], p3[0]);

        if (dir1_2 != -1 && dir1_2 == dir2_3) {
            return true;
        }
        if (dir1_2 != -1 && opposer(dir1_2) == dir2_3) {
            return true;
        }
        if (dir1_3 != -1 && dir1_3 == opposer(dir2_3)) {
            return true;
        }

        return false;
    }

    public static int getDirectionEntre(int y1, int x1, int y2, int x2) {
        int[][] d = Decalages(y1);
        for (int i = 0; i < 6; i++) {
            if (y1 + d[i][0] == y2 && x1 + d[i][1] == x2) {
                return i;
            }
        }
        return -1;
    }

    public static int opposer(int dir) {
        return (dir + 3) % 6;
    }

    // --- LOGIQUE DE MOUVEMENT (1 Bille) ---

    public static boolean[] chercherPossiblite1Bille(byte[][] tab, int joueur, int x, int y) {
        boolean[] possibilites = new boolean[6];
        int[][] decalages = Decalages(y);

        for (int i = 0; i < 6; i++) {
            int dy = decalages[i][0];
            int dx = decalages[i][1];
            int newY = y + dy;
            int newX = x + dx;

            if (estSurLePlateau(tab, newY, newX) && tab[newY][newX] == 0) {
                possibilites[i] = true;
            }
        }
        return possibilites;
    }

    public static void PossibilitéTab(byte[][] tab, boolean[] choix, int x, int y) {
        int[][] decalages = Decalages(y);
        for (int i = 0; i < choix.length; i++) {
            if (choix[i]) {
                int dy = decalages[i][0];
                int dx = decalages[i][1];
                if (estSurLePlateau(tab, y + dy, x + dx)) {
                    tab[y + dy][x + dx] = 5;
                }
            }
        }
    }

    public static boolean bouger1Bille(byte[][] tab, int[] posJoueur, boolean[] possibilites) {
        int direction = -1;
        boolean directionValide = false;

        String[] nomsDirections = { "↙", "←", "↖", "↗", "→", "↘" };

        System.out.println("");
        System.out.println("--- Directions possibles ---");

        boolean auMoinsUnChoix = false;
        for (int i = 0; i < possibilites.length; i++) {
            if (possibilites[i]) {
                System.out.println("Tapez " + i + " pour : " + nomsDirections[i]);
                auMoinsUnChoix = true;
            }
        }

        if (auMoinsUnChoix == false) {
            System.out.println("Aucun mouvement possible ici !");
            return false;
        }

        do {
            System.out.println("\nChoisissez une direction (-1 pour retour) : ");
            if (sc.hasNextInt()) {
                direction = sc.nextInt();
                if (direction == -1) {
                    return false;
                }

                if (direction >= 0 && direction <= 5) {
                    if (possibilites[direction]) {
                        directionValide = true;
                    } else {
                        System.out.println("Direction impossible (pas de flèche verte).");
                    }
                } else {
                    System.out.println("Erreur : Chiffre entre 0 et 5 attendu.");
                }
            } else {
                sc.next();
                System.out.println("Erreur : Ce n'est pas un chiffre.");
            }

        } while (directionValide == false);

        int y = posJoueur[0];
        int x = posJoueur[1];
        int[][] decalages = Decalages(y);

        int dy = decalages[direction][0];
        int dx = decalages[direction][1];
        int newY = y + dy;
        int newX = x + dx;

        if (estSurLePlateau(tab, newY, newX)) {
            tab[newY][newX] = tab[y][x];
            tab[y][x] = 0;
        }

        enleverDirection(tab);
        return true;
    }

    // --- LOGIQUE DE MOUVEMENT (Groupe) ET PRÉVISUALISATION ---

    public static boolean[] chercherPossibilitesGroupe(byte[][] tab, int joueur) {
        boolean[] result = new boolean[6];

        // 1. Récupérer la sélection
        int[][] sel = new int[3][2];
        int nb = 0;
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                if (tab[y][x] == 3 || tab[y][x] == 4) {
                    sel[nb][0] = y;
                    sel[nb][1] = x;
                    nb = nb + 1;
                }
            }
        }

        // 2. Tester chaque direction
        for (int dir = 0; dir < 6; dir++) {
            if (estMouvementEnLigne(sel, nb, dir)) {
                result[dir] = testerMouvementLigne(tab, sel, nb, dir, joueur);
            } else {
                result[dir] = testerMouvementLateral(tab, sel, nb, dir);
            }
        }

        return result;
    }

    public static boolean testerMouvementLateral(byte[][] tab, int[][] sel, int nb, int dir) {
        for (int i = 0; i < nb; i++) {
            int y = sel[i][0];
            int x = sel[i][1];
            int[][] d = Decalages(y);
            int ny = y + d[dir][0];
            int nx = x + d[dir][1];

            if (!estSurLePlateau(tab, ny, nx)) {
                return false;
            }
            if (tab[ny][nx] != 0 && tab[ny][nx] != 5) {
                return false;
            }
        }
        return true;
    }

    public static boolean testerMouvementLigne(byte[][] tab, int[][] sel, int nb, int dir, int joueur) {
        // Trouver la tête
        int indexTete = 0;
        for (int i = 0; i < nb; i++) {
            int y = sel[i][0];
            int x = sel[i][1];
            int[][] d = Decalages(y);
            int ny = y + d[dir][0];
            int nx = x + d[dir][1];

            boolean caseDevantEstAmie = false;
            for (int j = 0; j < nb; j++) {
                if (sel[j][0] == ny && sel[j][1] == nx) {
                    caseDevantEstAmie = true;
                }
            }
            if (!caseDevantEstAmie) {
                indexTete = i;
                break;
            }
        }

        int y = sel[indexTete][0];
        int x = sel[indexTete][1];
        int[][] d = Decalages(y);
        int ny = y + d[dir][0];
        int nx = x + d[dir][1];

        if (!estSurLePlateau(tab, ny, nx)) {
            return false;
        }

        // Libre
        if (tab[ny][nx] == 0 || tab[ny][nx] == 5) {
            return true;
        }

        // Ami
        if (tab[ny][nx] == joueur || tab[ny][nx] == (joueur + 2)) {
            return false;
        }

        // Ennemi (Sumito Check)
        int ennemi = (joueur == 1) ? 2 : 1;
        if (tab[ny][nx] == ennemi) {
            int nbEnnemis = 0;
            int cy = ny;
            int cx = nx;

            while (estSurLePlateau(tab, cy, cx) && tab[cy][cx] == ennemi) {
                nbEnnemis = nbEnnemis + 1;
                int[][] dc = Decalages(cy);
                cy = cy + dc[dir][0];
                cx = cx + dc[dir][1];
            }

            if (nb <= nbEnnemis) {
                return false;
            }

            // Derrière les ennemis
            if (estSurLePlateau(tab, cy, cx)) {
                if (tab[cy][cx] != 0 && tab[cy][cx] != 5) {
                    return false; // Bloqué
                }
            }
            return true;
        }

        return false;
    }

    public static void marquerPossibilitesGroupe(byte[][] tab, boolean[] possibilites) {
        int[][] sel = new int[3][2];
        int nb = 0;
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                if (tab[y][x] == 3 || tab[y][x] == 4) {
                    sel[nb][0] = y;
                    sel[nb][1] = x;
                    nb = nb + 1;
                }
            }
        }

        for (int dir = 0; dir < 6; dir++) {
            if (possibilites[dir]) {
                if (estMouvementEnLigne(sel, nb, dir)) {
                    // On marque devant la tête
                    int indexTete = 0;
                    for (int i = 0; i < nb; i++) {
                        int y = sel[i][0];
                        int x = sel[i][1];
                        int[][] d = Decalages(y);
                        int ny = y + d[dir][0];
                        int nx = x + d[dir][1];
                        boolean ami = false;
                        for(int j=0; j<nb; j++) if(sel[j][0]==ny && sel[j][1]==nx) ami=true;
                        if(!ami) { indexTete=i; break; }
                    }
                    int y = sel[indexTete][0];
                    int x = sel[indexTete][1];
                    int[][] d = Decalages(y);
                    int ny = y + d[dir][0];
                    int nx = x + d[dir][1];
                    if(estSurLePlateau(tab, ny, nx) && tab[ny][nx] == 0) tab[ny][nx] = 5;
                } else {
                    for(int i=0; i<nb; i++) {
                        int y = sel[i][0];
                        int x = sel[i][1];
                        int[][] d = Decalages(y);
                        int ny = y + d[dir][0];
                        int nx = x + d[dir][1];
                        if(estSurLePlateau(tab, ny, nx) && tab[ny][nx] == 0) {
                            tab[ny][nx] = 5;
                        }
                    }
                }
            }
        }
    }

    public static boolean bougerGroupe(byte[][] tab, int joueur, boolean[] possibilites) {
        int[][] selection = new int[3][2];
        int nbBilles = 0;

        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                if (tab[y][x] == 3 || tab[y][x] == 4) {
                    selection[nbBilles][0] = y;
                    selection[nbBilles][1] = x;
                    nbBilles = nbBilles + 1;
                }
            }
        }

        int direction = -1;
        boolean directionValide = false;
        String[] noms = { "↙", "←", "↖", "↗", "→", "↘" };

        System.out.println("Directions possibles (si vert) :");
        for (int i = 0; i < 6; i++) {
            if (possibilites[i]) {
                System.out.println(i + ": " + noms[i]);
            }
        }

        do {
            System.out.println("Direction du mouvement (0-5 ou -1 retour) :");
            String entree = sc.next();
            try {
                direction = Integer.parseInt(entree);
                if (direction == -1) {
                    return false;
                }
                if (direction >= 0 && direction <= 5 && possibilites[direction]) {
                    directionValide = true;
                } else {
                    System.out.println("Mouvement impossible ou chiffre invalide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Ce n'est pas un chiffre.");
            }
        } while (directionValide == false);

        boolean mouvementLigne = estMouvementEnLigne(selection, nbBilles, direction);

        if (mouvementLigne) {
            return effectuerDeplacementLigne(tab, selection, nbBilles, direction, joueur);
        } else {
            return effectuerDeplacementLateral(tab, selection, nbBilles, direction);
        }
    }

    public static boolean estMouvementEnLigne(int[][] sel, int nb, int dir) {
        if (nb == 1) {
            return true;
        }
        int dirEntreBilles = getDirectionEntre(sel[0][0], sel[0][1], sel[1][0], sel[1][1]);
        if (dirEntreBilles == dir || dirEntreBilles == opposer(dir)) {
            return true;
        }
        return false;
    }

    public static boolean effectuerDeplacementLateral(byte[][] tab, int[][] sel, int nb, int dir) {
        // Validation déjà faite par testerMouvementLateral
        // On déplace
        int valJoueur = 0;
        if (tab[sel[0][0]][sel[0][1]] == 3) {
            valJoueur = 1;
        } else {
            valJoueur = 2;
        }

        for (int i = 0; i < nb; i++) {
            tab[sel[i][0]][sel[i][1]] = 0;
        }

        for (int i = 0; i < nb; i++) {
            int y = sel[i][0];
            int x = sel[i][1];
            int[][] d = Decalages(y);
            tab[y + d[dir][0]][x + d[dir][1]] = (byte) valJoueur;
        }
        return true;
    }

    public static boolean effectuerDeplacementLigne(byte[][] tab, int[][] sel, int nb, int dir, int joueur) {
        // La validation principale est déjà faite par testerMouvementLigne
        // On doit juste gérer la poussée si elle existe

        // 1. Trouver la tête
        int indexTete = 0;
        for (int i = 0; i < nb; i++) {
            int y = sel[i][0];
            int x = sel[i][1];
            int[][] d = Decalages(y);
            int ny = y + d[dir][0];
            int nx = x + d[dir][1];
            boolean ami = false;
            for(int j=0; j<nb; j++) if(sel[j][0]==ny && sel[j][1]==nx) ami=true;
            if(!ami) { indexTete=i; break; }
        }

        int y = sel[indexTete][0];
        int x = sel[indexTete][1];
        int[][] d = Decalages(y);
        int ny = y + d[dir][0];
        int nx = x + d[dir][1];

        // Est-ce un Sumito ?
        int ennemi = (joueur == 1) ? 2 : 1;
        if (estSurLePlateau(tab, ny, nx) && tab[ny][nx] == ennemi) {
            int cy = ny;
            int cx = nx;
            while (estSurLePlateau(tab, cy, cx) && tab[cy][cx] == ennemi) {
                int[][] dc = Decalages(cy);
                cy = cy + dc[dir][0];
                cx = cx + dc[dir][1];
            }

            // Pousser
            boolean ejection = !estSurLePlateau(tab, cy, cx);
            if (!ejection) {
                tab[cy][cx] = (byte) ennemi;
            } else {
                System.out.println("Bille éjectée !");
            }
        }

        // Déplacer nos billes
        deplacerLigneSimple(tab, sel, nb, dir, joueur);
        return true;
    }

    public static void deplacerLigneSimple(byte[][] tab, int[][] sel, int nb, int dir, int joueur) {
        for (int i = 0; i < nb; i++) {
            tab[sel[i][0]][sel[i][1]] = 0;
        }

        for (int i = 0; i < nb; i++) {
            int y = sel[i][0];
            int x = sel[i][1];
            int[][] d = Decalages(y);
            tab[y + d[dir][0]][x + d[dir][1]] = (byte) joueur;
        }
    }

    // --- VICTOIRE ---

    public static int compterBilles(byte[][] tab, int joueur) {
        int cpt = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == joueur || tab[i][j] == (joueur + 2)) {
                    cpt = cpt + 1;
                }
            }
        }
        return cpt;
    }

    public static boolean verifierVictoire(byte[][] tab) {
        int j1 = compterBilles(tab, 1);
        int j2 = compterBilles(tab, 2);

        if (j1 <= 8) {
            System.out.println("VICTOIRE DU JOUEUR 2 !");
            return true;
        }
        if (j2 <= 8) {
            System.out.println("VICTOIRE DU JOUEUR 1 !");
            return true;
        }
        return false;
    }
}
