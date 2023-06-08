package punttisalimuistio;

import java.util.*;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Liikkeet                            | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | - Liike           | 
 * | - pitää yllä varsinaista liikerekisteriä, eli      |                   | 
 * |   osaa lisätä ja poistaa liikkeitä                 |                   | 
 * | - lukee ja kirjoittaa liikkeet.dat -tiedostoon     |                   | 
 * | - osaa etsiä ja lajitella                          |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |-------------------------------------------------------------------------
 * Punttisalimuistion Liikkeet-luokka
 * @author Eetu
 * @version 07.06.2023
 */
public class Liikkeet implements Iterable<Liike>  {
    private String                      tiedostoNimi = "";
    private final Collection<Liike>     alkiot        = new ArrayList<Liike>();
    
    
    /**
     * Oletusmuodostaja
     */
    public Liikkeet() {
        // Vielä ei tarvita mitään
    }
    
    
    /**
     * Palauttaa punttisalimuistion liikkeiden lukumäärän
     * @return liikkeiden lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Lisää uuden liikkeen tietorakenteeseen.  Ottaa liikkeen omistukseensa.
     * @param lii lisättävä liike.  Huom tietorakenne muuttuu omistajaksi
     */
    public void lisaa(Liike lii) {
        alkiot.add(lii);
    }
    
    
    /**
     * Lukee liikkeet tiedostosta.  
     * TODO Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostoNimi = hakemisto + "/liikkeet.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostoNimi);
    }
    
    
    /**
     * Tallentaa liikkeet tiedostoon.  
     * TODO Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostoNimi);
    }
    
    
    /**
     * Iteraattori kaikkien liikkeiden läpikäymiseen
     * @return liikeiteraattori
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii21 = new Liike(2); liikkeet.lisaa(lii21);
     *   Liike lii11 = new Liike(1); liikkeet.lisaa(lii11);
     *   Liike lii22 = new Liike(2); liikkeet.lisaa(lii22);
     *   Liike lii12 = new Liike(1); liikkeet.lisaa(lii12);
     *   Liike lii23 = new Liike(2); liikkeet.lisaa(lii23);
     * 
     *   Iterator<Liike> i2=liikkeet.iterator();
     *   i2.next() === lii21;
     *   i2.next() === lii11;
     *   i2.next() === lii22;
     *   i2.next() === lii12;
     *   i2.next() === lii23;
     *   i2.next() === lii12;  #THROWS NoSuchElementException  
     *  
     *   int n = 0;
     *   int tnrot[] = {2,1,2,1,2};
     *  
     *   for ( Liike lii:liikkeet ) { 
     *     lii.getTreeniNro() === tnrot[n]; n++;  
     *   }
     *   n === 5;
     * </pre>
     */
    @Override
    public Iterator<Liike> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Haetaan kaikki treeni liikkeet
     * @param tunnusnro treenin tunnusnumero jolle liikkeitä haetaan
     * @return tietorakenne jossa viiteet löydettyihin liikkeisiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii21 = new Liike(2); liikkeet.lisaa(lii21);
     *   Liike lii11 = new Liike(1); liikkeet.lisaa(lii11);
     *   Liike lii22 = new Liike(2); liikkeet.lisaa(lii22);
     *   Liike lii12 = new Liike(1); liikkeet.lisaa(lii12);
     *   Liike lii23 = new Liike(2); liikkeet.lisaa(lii23);
     *   Liike lii51 = new Liike(5); liikkeet.lisaa(lii51);
     *   
     *   List<Liike> loytyneet;
     *   loytyneet = liikkeet.annaLiikkeet(3);
     *   loytyneet.size() === 0; 
     *   loytyneet = liikkeet.annaLiikkeet(1);
     *   loytyneet.size() === 2; 
     *   loytyneet.get(0) == lii11 === true;
     *   loytyneet.get(1) == lii12 === true;
     *   loytyneet = liikkeet.annaLiikkeet(5);
     *   loytyneet.size() === 1; 
     *   loytyneet.get(0) == lii51 === true;
     * </pre> 
     */
    public List<Liike> annaLiikkeet(int tunnusnro) {
        List<Liike> loydetyt = new ArrayList<Liike>();
        for (Liike lii : alkiot)
            if (lii.getTreeniNro() == tunnusnro) loydetyt.add(lii);
        return loydetyt;
    }
    
    
    /**
     * Testiohjelma liikkeille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Liikkeet liikkeet = new Liikkeet();
        Liike lii1 = new Liike();
        lii1.taytaLiike(2);
        Liike lii2 = new Liike();
        lii2.taytaLiike(1);
        Liike lii3 = new Liike();
        lii3.taytaLiike(2);
        Liike lii4 = new Liike();
        lii4.taytaLiike(2);

        liikkeet.lisaa(lii1);
        liikkeet.lisaa(lii2);
        liikkeet.lisaa(lii3);
        liikkeet.lisaa(lii2);
        liikkeet.lisaa(lii4);

        System.out.println("============= Liikkeet testi =================");
        List<Liike> liikkeet2 = liikkeet.annaLiikkeet(2);
        for (Liike lii : liikkeet2) {
            System.out.print(lii.getTreeniNro() + " ");
            lii.tulosta(System.out);
        }
    }
}
