

import java.util.Arrays;
public class main {
public static void main(String[] args){
    int[] coord, target;
    coord = new int[2];
    byte[][] tab = JeuV2.creerTabPlateau();
    JeuV2.afficherPlateau(tab);
    JeuV2.remplirTabPlateau(tab);
    JeuV2.afficherTab2D(tab);
    JeuV2.afficherPlateau(tab);
    switch(JeuV2.ChoixNombreBille(tab, 2)){
        case 1:
            JeuV2.Choix1(tab, 2, coord);
            JeuV2.PossibilitéTab(tab, JeuV2.chercherPossiblite1Bille(tab,coord[0], coord[1]), coord[0], coord[1]);
            JeuV2.afficherPlateau(tab);
           // JeuV2.afficherTab2D(tab);
            /*target = JeuV2.choisirBouger(tab, 2);
            JeuV2.bouger1Bille(tab, coord, target);*/
            System.out.println("[0] = \uD83E\uDC77, [1] = \uD83E\uDC70 , [2] = \uD83E\uDC74 , [3] = \uD83E\uDC75 , [4] = \uD83E\uDC72 , [5] = \uD83E\uDC76");
            JeuV2.bouger1Bille(tab, coord);
            JeuV2.afficherPlateau(tab);
            break;
        case 2:
            JeuV2.Choix2(tab, 2);
            break;
        case 3:
            JeuV2.Choix3(tab, 2);
            break;
        default: break;
    }
    JeuV2.afficherTab2D(tab);
    JeuV2.afficherPlateau(tab);
}
