public class main {
    public static void main(String[] args) {
        // Initialisation du jeu
        JeuV2.titre();
        //byte[][] plateau = JeuV2.creerTabPlateau();
        byte[][]plateau = {
                {2, 0, 2, 2, 2},
                {1, 2, 2, 1, 2, 2},
                {1, 0, 2, 1, 0, 2, 2},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        //JeuV2.remplirTabPlateau(plateau);

        boolean partieEnCours = true;
        int joueurActuel = 1;


        int etape = 0;
        int nbBilles = 0;

        while (partieEnCours) {

            if (etape == 0) {
                JeuV2.afficherPlateau(plateau);
                System.out.println("--- score Joueur  1 : " + JeuV2.score(plateau, 1));
                System.out.println("--- score Joueur  2 : " + JeuV2.score(plateau, 2));

                if (JeuV2.verifierVictoire(plateau)) {
                    partieEnCours = false;
                    break;
                }

                System.out.println("--- Tour du Joueur " + joueurActuel + " ---");
                nbBilles = JeuV2.ChoixNombreBille(plateau, joueurActuel);

                etape = 1;

            }


            else if (etape == 1) {
                boolean selectionOK = false;

                if (nbBilles == 1) {
                    int[] billePos = new int[2];
                    selectionOK = JeuV2.Choix1(plateau, joueurActuel, billePos);
                } else if (nbBilles == 2) {
                    selectionOK = JeuV2.Choix2(plateau, joueurActuel);
                } else if (nbBilles == 3) {
                    selectionOK = JeuV2.Choix3(plateau, joueurActuel);
                }

                if (selectionOK) {

                    JeuV2.afficherPlateau(plateau);
                    etape = 2;
                } else {
                    System.out.println("Retour au choix du nombre...");
                    etape = 0;
                }
            }


            else if (etape == 2) {
                boolean[] possibles;


                if (nbBilles == 1) {

                    int px = 0;
                    int py = 0;
                    for (int y = 0; y < plateau.length; y++) {
                        for (int x = 0; x < plateau[y].length; x++) {
                            if (plateau[y][x] == 3 || plateau[y][x] == 4) {
                                py = y;
                                px = x;
                            }
                        }
                    }
                    possibles = JeuV2.chercherPossiblite1Bille(plateau, joueurActuel, px, py);

                    JeuV2.PossibilitéTab(plateau, possibles, px, py);
                } else {

                    possibles = JeuV2.chercherPossibilitesGroupe(plateau, joueurActuel);

                    JeuV2.marquerPossibilitesGroupe(plateau, possibles);
                }


                JeuV2.afficherPlateau(plateau);


                boolean mouvementEffectue;

                if (nbBilles == 1) {
                    int[] pos = new int[2];
                    for (int y = 0; y < plateau.length; y++) {
                        for (int x = 0; x < plateau[y].length; x++) {
                            if (plateau[y][x] == 3 || plateau[y][x] == 4) {
                                pos[0] = y;
                                pos[1] = x;
                            }
                        }
                    }
                    mouvementEffectue = JeuV2.bouger1Bille(plateau, pos, possibles);
                } else {
                    mouvementEffectue = JeuV2.bougerGroupe(plateau, joueurActuel, possibles);
                }


                if (mouvementEffectue) {

                    JeuV2.finChoix(plateau);
                    JeuV2.enleverDirection(plateau);


                    if (joueurActuel == 1) {
                        joueurActuel = 2;
                    } else {
                        joueurActuel = 1;
                    }

                    etape = 0;
                } else {

                    System.out.println("Retour à la sélection...");
                    JeuV2.finChoix(plateau);
                    JeuV2.enleverDirection(plateau);
                    etape = 1;
                }
            }
        }
    }
}
