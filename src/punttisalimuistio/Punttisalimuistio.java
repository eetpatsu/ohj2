package punttisalimuistio;

import java.util.List;

/**
 * |-------------------------------------------------------------------------|
 * | Luokan nimi:   Punttisalimuistio                   | Avustajat:         |
 * |--------------------------------------------------------------------------
 * | Vastuualueet:                                      |                    | 
 * |                                                    | - Treenit          | 
 * | - huolehtii Treenit ja Liikkeet -luokkien          | - Treeni           | 
 * |   välisestä yhteistyöstä ja välittää näitä         | - Liikkeet         | 
 * |   tietoja pyydettäessä                             | - Liike            | 
 * | - lukee ja kirjoittaa muistion tiedostoihin        |                    |
 * |   pyytämällä apua avustajiltaan                    |                    |
 * |                                                    |                    |
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |--------------------------------------------------------------------------
 * Punttisalimuistio-luokka, joka huolehtii treeneistä ja liikkeistä.
 * Pääosin kaikki metodit ovat vain "välittäjämetodeja".
 * @author Eetu
 * @version 0.4, 03.06.2023 Tiedoston synty
 * @version 0.5, 08.06.2023 Liikkeet ja Liike mukaan
 */
public class Punttisalimuistio {
    private final Treenit treenit = new Treenit();              // Treenit-olioviite
    private final Liikkeet liikkeet = new Liikkeet();           // Liikkeet-olioviite
    
    
    /**
     * Palauttaa punttisalimuistioon kirjattujen treenien määrän
     * @return treenien määrä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi();
     *   
     *   muistio.getTreeneja() === 0;
     *   muistio.lisaa(tre1); muistio.getTreeneja() === 1;
     *   muistio.lisaa(tre2); muistio.getTreeneja() === 2;
     *   muistio.lisaa(tre1); muistio.getTreeneja() === 3;
     * </pre>
     */
    public int getTreeneja() {
        return treenit.getLkm();
    }
    
    
    /**
     * Palauttaa i:n treenin
     * @param i monesko treeni palautetaan
     * @return viite i:teen treeniin
     * @throws IndexOutOfBoundsException jos i väärin
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi();
     *   
     *   muistio.getTreeneja() === 0;
     *   muistio.lisaa(tre1); muistio.annaTreeni(0) === tre1;
     *   muistio.lisaa(tre2); muistio.annaTreeni(1) === tre2;
     *   muistio.lisaa(tre1); muistio.annaTreeni(2) === tre1;
     *   muistio.annaTreeni(3) === tre1; #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public Treeni annaTreeni(int i) throws IndexOutOfBoundsException {
        return treenit.anna(i);
    }
    
    
    /**
     * Haetaan kaikki treenin liikkeet
     * @param treeni minkä treenin liikkeet haetaan
     * @return tietorakenne jossa viitteet löydettyihin harrastuksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni(), tre2 = new Treeni(), tre3 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi(); tre3.rekisteroi();
     *   int id1 = tre1.getTunnusNro();
     *   int id2 = tre2.getTunnusNro();
     *   Liike lii11 = new Liike(id1); muistio.lisaa(lii11);
     *   Liike lii12 = new Liike(id1); muistio.lisaa(lii12);
     *   Liike lii21 = new Liike(id2); muistio.lisaa(lii21);
     *   Liike lii22 = new Liike(id2); muistio.lisaa(lii22);
     *   Liike lii23 = new Liike(id2); muistio.lisaa(lii23);
     *   
     *   List<Liike> loytyneet;
     *   loytyneet = muistio.annaLiikkeet(tre3);
     *   loytyneet.size() === 0; 
     *   loytyneet = muistio.annaLiikkeet(tre1);
     *   loytyneet.size() === 2; 
     *   loytyneet.get(0) == lii11 === true;
     *   loytyneet.get(1) == lii12 === true;
     *   loytyneet = muistio.annaLiikkeet(tre2);
     *   loytyneet.size() === 3; 
     *   loytyneet.get(0) == lii21 === true;
     *   loytyneet.get(1) == lii22 === true;
     *   loytyneet.get(2) == lii23 === true;
     * </pre>
     */
    public List<Liike> annaLiikkeet(Treeni treeni) {
        return liikkeet.annaLiikkeet(treeni.getTunnusNro());
    }
    
    
    /**
     * Lisää punttisalimuistioon uuden treenin
     * @param treeni lisättävä treeni
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi();
     *   
     *   muistio.getTreeneja() === 0;
     *   muistio.lisaa(tre1); muistio.getTreeneja() === 1;
     *   muistio.lisaa(tre2); muistio.getTreeneja() === 2;
     *   muistio.lisaa(tre1); muistio.getTreeneja() === 3;
     *   
     *   muistio.annaTreeni(0) === tre1;
     *   muistio.annaTreeni(1) === tre2;
     *   muistio.annaTreeni(2) === tre1;
     *   muistio.annaTreeni(3) === tre1; #THROWS IndexOutOfBoundsException
     *   
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Treeni treeni) throws SailoException {
        treenit.lisaa(treeni);
    }
    
    
    /**
     * Lisää punttisalimuistioon uuden liikkeen
     * @param liike lisättävä liike
     * <pre name="test">
     * #import java.util.*;
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni(), tre2 = new Treeni(), tre3 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi(); tre3.rekisteroi();
     *   int id1 = tre1.getTunnusNro();
     *   int id2 = tre2.getTunnusNro();
     *   Liike lii11 = new Liike(id1); muistio.lisaa(lii11);
     *   Liike lii12 = new Liike(id1); muistio.lisaa(lii12);
     *   Liike lii21 = new Liike(id2); muistio.lisaa(lii21);
     *   Liike lii22 = new Liike(id2); muistio.lisaa(lii22);
     *   Liike lii23 = new Liike(id2); muistio.lisaa(lii23);
     *   
     *   List<Liike> loytyneet;
     *   loytyneet = muistio.annaLiikkeet(tre3);
     *   loytyneet.size() === 0; 
     *   loytyneet = muistio.annaLiikkeet(tre1);
     *   loytyneet.size() === 2; 
     *   loytyneet.get(0) == lii11 === true;
     *   loytyneet.get(1) == lii12 === true;
     *   loytyneet = muistio.annaLiikkeet(tre2);
     *   loytyneet.size() === 3; 
     *   loytyneet.get(0) == lii21 === true;
     *   loytyneet.get(0) == lii21 === true;
     *   loytyneet.get(1) == lii22 === true;
     *   loytyneet.get(2) == lii23 === true;
     * </pre>
     */
    public void lisaa(Liike liike) {
        liikkeet.lisaa(liike);
    }
    
    
    /**
     * Poistaa treeneistä ja liikkeistä ne joilla on nro. Kesken.
     * @param nro viitenumero, jonka mukaan poistetaan
     * @return montako treeniä poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
    }
    
    
    /**
     * Lukee punttisalimuistion tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        treenit.lueTiedostosta(nimi);
        liikkeet.lueTiedostosta(nimi);
    }
    
    
    /**
     * Tallettaa punttisalimuistion tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        treenit.talleta();
        liikkeet.talleta();
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
    }
    
    
    /**
     * Testiohjelma punttisalimuistiolle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Punttisalimuistio muistio = new Punttisalimuistio();
        try {
            // punttisalimuistio.lueTiedostosta("aku");
            Treeni tre1 = new Treeni(), tre2 = new Treeni();
            tre1.rekisteroi();
            tre2.rekisteroi();
            tre1.taytaTreeni();
            tre2.taytaTreeni();
            
            muistio.lisaa(tre1);
            muistio.lisaa(tre2);
            int id1 = tre1.getTunnusNro();
            int id2 = tre2.getTunnusNro();
            
            Liike lii11 = new Liike(id1); lii11.taytaLiike(id1); muistio.lisaa(lii11);
            Liike lii12 = new Liike(id1); lii11.taytaLiike(id1); muistio.lisaa(lii12);
            Liike lii21 = new Liike(id2); lii21.taytaLiike(id2); muistio.lisaa(lii21);
            Liike lii22 = new Liike(id2); lii22.taytaLiike(id2); muistio.lisaa(lii22);
            Liike lii23 = new Liike(id2); lii23.taytaLiike(id2); muistio.lisaa(lii23);
            
            System.out.println("============= Punttisalimuistion testi =================");
            for (int i = 0; i < muistio.getTreeneja(); i++) {
                Treeni treeni = muistio.annaTreeni(i);
                System.out.println("Treeni paikassa: " + i);
                treeni.tulosta(System.out);
                List<Liike> loytyneet = muistio.annaLiikkeet(treeni);
                for (Liike liike : loytyneet) {
                    liike.tulosta(System.out);
                }
            }
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
