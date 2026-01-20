import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JeuV2Test{
    @Test
    void estSurPlateau() {
        byte[][] plateau = JeuV2.creerTabPlateau();
        assertFalse(JeuV2.estSurLePlateau(plateau, 10, 3), "cas ligne superieur au tableau");
        assertFalse(JeuV2.estSurLePlateau(plateau, -10, 5), "cas ligne inferieur au tableau");
        assertFalse(JeuV2.estSurLePlateau(plateau, 2, 7), "cas colonne superieur au tableau");
        assertFalse(JeuV2.estSurLePlateau(plateau, 4, -1), "cas colonne inferieur au tableau");

        assertTrue(JeuV2.estSurLePlateau(plateau, 4, 5), "cas sur le plateau");
        assertTrue(JeuV2.estSurLePlateau(plateau, 2, 3), "cas sur le plateau");
        assertTrue(JeuV2.estSurLePlateau(plateau, 5, 7), "cas sur le plateau");
    }
    @Test
    void verifierScore() {
        byte[][]plateau = {
                {2, 0, 2, 2, 2},
                {1, 2, 2, 2, 2, 0},
                {1, 0, 2, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {2, 2, 2, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        assertEquals(5,JeuV2.score(plateau, 2), "cas reste 9 bille du joueur 1");
        assertEquals(2,JeuV2.score(plateau, 1), "cas reste 12 bille du joueur 2");


    }
    @Test
    void verifierVictoire() {
        byte[][]plateau = {
                {2, 0, 2, 2, 2},
                {1, 2, 2, 2, 2, 0},
                {1, 0, 2, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {2, 2, 2, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        byte[][]plateau2 = {
                {2, 0, 2, 2, 2},
                {0, 2, 2, 2, 2, 0},
                {1, 0, 2, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {2, 2, 2, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        assertFalse(JeuV2.verifierVictoire(plateau), "cas score = 5");
        assertFalse(JeuV2.verifierVictoire(plateau), "cas score = 6");

    }
    @Test
    void verifiercomptage() {
        byte[][]plateau = {
                {2, 0, 2, 2, 2},
                {1, 2, 2, 2, 2, 0},
                {1, 0, 2, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {2, 2, 2, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

    }


}
