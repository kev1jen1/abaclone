import java.util.Arrays;
public class main {
    public static void main(String[] args){
        Jeu.plateau();
        byte[][] tab = Jeu.creerTabPlateau();
        Jeu.afficherTab2D(tab);
    }
}