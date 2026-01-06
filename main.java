public class main {
    public static void main(String[] args) {
        // Initialisation du jeu
        JeuV2.titre();
        byte[][] plateau = JeuV2.creerTabPlateau();
        JeuV2.remplirTabPlateau(plateau);

        boolean partieEnCours = true;
        int joueurActuel = 1;

        
        int etape = 0;
        int nbBilles = 0;

        while (partieEnCours) {

            if (etape == 0) {
                JeuV2.afficherPlateau(plateau);

                // Vérifier la victoire avant de commencer le tour
                if (JeuV2.verifierVictoire(plateau)) {
                    partieEnCours = false;
                    break;
                }

                System.out.println("--- Tour du Joueur " + joueurActuel + " ---");
                nbBilles = JeuV2.ChoixNombreBille(plateau, joueurActuel);

                if (nbBilles == -1) {
                    System.out.println("Impossible de reculer plus.");
                } else {
                    etape = 1;
                }
            }

            
            else if (etape == 1) {
                boolean selectionOK = false;

                if (nbBilles == 1) {
                    int[] dummy = new int[2];
                    selectionOK = JeuV2.Choix1(plateau, joueurActuel, dummy);
                } else if (nbBilles == 2) {
                    selectionOK = JeuV2.Choix2(plateau, joueurActuel);
                } else if (nbBilles == 3) {
                    selectionOK = JeuV2.Choix3(plateau, joueurActuel);
                }

                if (selectionOK) {
                    // On affiche le plateau avec la sélection finale (X)
                    JeuV2.afficherPlateau(plateau);
                    etape = 2;
                } else {
                    System.out.println("Retour au choix du nombre...");
                    etape = 0;
                }
            }

            
            else if (etape == 2) {
                boolean[] possibles;

                // 1. Calcul des possibilités (Simulation)
                if (nbBilles == 1) {
                    // Pour 1 bille, on cherche sa position pour calculer les voisins
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
                    // Marquage visuel (▲)
                    JeuV2.PossibilitéTab(plateau, possibles, px, py);
                } else {
                    // Pour groupe : calcul complexe (Sumito, Latéral)
                    possibles = JeuV2.chercherPossibilitesGroupe(plateau, joueurActuel);
                    // Marquage visuel (▲)
                    JeuV2.marquerPossibilitesGroupe(plateau, possibles);
                }

                // 2. Affichage des possibilités sur le plateau
                JeuV2.afficherPlateau(plateau);

                // 3. Demande de mouvement
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

                // 4. Traitement du résultat
                if (mouvementEffectue) {
                    // Nettoyage
                    JeuV2.finChoix(plateau);
                    JeuV2.enleverDirection(plateau);

                    // Changement de joueur
                    if (joueurActuel == 1) {
                        joueurActuel = 2;
                    } else {
                        joueurActuel = 1;
                    }
                    // Retour au début
                    etape = 0;
                } else {
                    // Annulation (Retour en arrière)
                    System.out.println("Retour à la sélection...");
                    JeuV2.finChoix(plateau);
                    JeuV2.enleverDirection(plateau);
                    etape = 1;
                }
            }
        }
    }
}
