

import java.util.Arrays;
public class main {
    public static void main(String[] args){
        JeuV2.plateau();
        byte[][] tab = JeuV2.creerTabPlateau();
        JeuV2.afficherPlateau(tab);
        JeuV2.remplirTabPlateau(tab);
        JeuV2.afficherTab2D(tab);
        JeuV2.afficherPlateau(tab);
        switch(JeuV2.ChoixNombreBille(tab, 2)){
            case 1:
                JeuV2.Choix1(tab, 2);
                break;
            case 2:
                JeuV2.Choix2(tab, 2);
                break;
            case 3:
                break;
            default: break;
        }
        JeuV2.afficherTab2D(tab);
        JeuV2.afficherPlateau(tab);
    }
}