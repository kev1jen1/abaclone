

import java.util.Arrays;
public class main {
    public static void main(String[] args){
        JeuV2.plateau();
        byte[][] tab = JeuV2.creerTabPlateau();

        JeuV2.remplirTabPlateau(tab);
        JeuV2.afficherTab2D(tab);
    }
}