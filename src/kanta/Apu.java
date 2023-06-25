package kanta;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka sekalaisille aliohjelmille
 * @author eetpatsu@student.jyu.fi
 * @version 0.5, 07.06.2023 Tiedoston synty
 * @version 0.7.4, 25.06.2023 Pvm tarkistaja
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
    
    
    /**
     * Tarkistaa kelpaako annettu päivämäärä ohjelmaan.
     * @param pvm tarkistettava päivämäärä
     * @return virheteksti jos ei kelpaa päivämääräksi
     * @example
     * <pre name="test">
     *   tarkistaPvm("abcdefgh") === "Kiellettyjä merkkejä";
     *   tarkistaPvm("1.1.2023") === "Pvm pitää olla muotoa pp.kk.vv";
     *   tarkistaPvm("00.01.2023") === "Liian pieni pvm";
     *   tarkistaPvm("01.00.2023") === "Liian pieni pvm";
     *   tarkistaPvm("01.01.1999") === "Liian pieni pvm";
     *   tarkistaPvm("32.01.2023") === "Liian suuri pvm";
     *   tarkistaPvm("01.13.2023") === "Liian suuri pvm";
     *   tarkistaPvm("01.01.2100") === "Liian suuri pvm";
     *   tarkistaPvm("09.06.2023") === null;
     *   tarkistaPvm("10.06.2023") === null;
     *   tarkistaPvm("12.06.2023") === null;
     * </pre>
     */
    public static String tarkistaPvm(String pvm) {
        var sb = new StringBuilder(pvm);
        int pp = Mjonot.erotaInt(sb,0);
        Mjonot.erota(sb,'.');
        int kk = Mjonot.erotaInt(sb,0);
        Mjonot.erota(sb,'.');
        int vv = Mjonot.erotaInt(sb,0);
        if (!pvm.matches("[0-9.]*"))
                return "Kiellettyjä merkkejä";
        if (!pvm.matches("[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]"))
            return "Pvm pitää olla muotoa pp.kk.vv";
        if (pp < 1 || kk < 1 || vv < 2000)
            return "Liian pieni pvm";
        if (pp > 31 || kk > 12 || vv > 2099)
            return "Liian suuri pvm";
        return null;
    }
}
