package kanta;

/**
 * Luokka sekalaisille aliohjelmille
 * @author Eetu
 * @version 0.5, 07.06.2023 Tiedoston synty
 */
public class Apu {
    
    /**
     * Arvotaan satunnainen kokonaisluku välille [ala,yla]
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen luku väliltä [ala,yla]
     */
    public static int rand(int ala, int yla) {
      double n = (yla-ala)*Math.random() + ala;
      return (int)Math.round(n);
    }
}
