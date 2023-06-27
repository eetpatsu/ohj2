package punttisalimuistio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Avustajat: Liike
 * Vastuualueet: Pitää yllä varsinaista liikerekisteriä,
 * eli osaa lisätä ja poistaa liikkeitä.
 * Lukee ja kirjoittaa liikkeet.dat -tiedostoon.
 * Osaa etsiä ja lajitella.
 * 
 * Punttisalimuistion Liikkeet-luokka
 * @author eetpatsu@student.jyu.fi
 * @version 0.5, 07.06.2023 Tiedoston synty
 * @version 0.6, 16.06.2023 Tiedostonhallinta
 * @version 0.7.5, 26.06.2023 Lisää tai korvaa olemassaoleva
 * @version 0.7.6, 27.06.2023 Poistaminen
 */
public class Liikkeet implements Iterable<Liike>  {
    private final List<Liike> alkiot = new ArrayList<Liike>(); // Taulukko liikkeistä
    private boolean onkoMuutettu = false;                      // Ovatko liikkeen tiedot muuttuneet alkuperäisestä
    private String  tiedostoNimi = "";
    
    
    /**
     * Oletusmuodostaja
     */
    public Liikkeet() {
        // Attribuutit jo alustettu
    }
    
    
    /**
     * Palauttaa punttisalimuistion liikkeiden lukumäärän
     * @return liikkeiden lukumäärä
     * @example
     * <pre name="test">
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike(1);
     *   liikkeet.getLkm() === 0;
     *   lii1T1.rekisteroi();
     *   liikkeet.lisaa(lii1T1);
     *   liikkeet.getLkm() === 1;
     * </pre>
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Lisää uuden liikkeen tietorakenteeseen.  Ottaa liikkeen omistukseensa.
     * @param lii lisättävä liike.  Huom tietorakenne muuttuu omistajaksi
     * @example
     * <pre name="test">
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike(1);
     *   Liike lii2T1 = new Liike(1);
     *   liikkeet.getLkm() === 0;
     *   lii1T1.rekisteroi();
     *   liikkeet.lisaa(lii1T1);
     *   liikkeet.getLkm() === 1;
     *   lii2T1.rekisteroi();
     *   liikkeet.lisaa(lii2T1);
     *   liikkeet.getLkm() === 2;
     * </pre>
     */
    public void lisaa(Liike lii) {
        alkiot.add(lii);
        onkoMuutettu = true;
    }
    
    
    /**
     * Katsotaan onko jo olemassa liike samalla id:llä
     * ja korvataan jos on. Muulloin lisätään normaalisti.
     * @param liike käsiteltävä liike
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * #THROWS SailoException
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike();
     *   lii1T1.rekisteroi();
     *   lii1T1.taytaLiike(1);
     *   liikkeet.lisaa(lii1T1);
     *   Liike lii2T1 = new Liike();
     *   lii2T1.rekisteroi();
     *   lii2T1.taytaLiike(1);
     *   liikkeet.lisaa(lii2T1);
     *   liikkeet.korvaaTaiLisaa(lii1T1);
     *   liikkeet.korvaaTaiLisaa(lii2T1);
     *   liikkeet.getLkm() === 2;
     *   Liike klooni = lii1T1.clone();
     *   lii1T1.toString().equals(klooni.toString()) === true;
     *   liikkeet.korvaaTaiLisaa(klooni);
     *   liikkeet.getLkm() === 2;
     *   lii1T1.parse("2|1|kyykky|80|3|5");
     *   lii1T1.toString().equals(klooni.toString()) === false;
     *   liikkeet.korvaaTaiLisaa(lii1T1);
     *   liikkeet.getLkm() === 3;
     * </pre>
     */
    public void korvaaTaiLisaa(Liike liike) {
        int tunnusNro = liike.getTunnusNro();
        for (int i = 0; i < getLkm(); i++) {
            if (tunnusNro == alkiot.get(i).getTunnusNro()) {
                alkiot.set(i, liike);
                onkoMuutettu = true;
                return;
            }
        }
        lisaa(liike);
    }
    
    
    /**
     * Poistaa annetun liikkeen
     * @param liike poistettava liike
     * @return true jos löytyi poistettava liike 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike(); lii1T1.taytaLiike(1);
     *   Liike lii2T1 = new Liike(); lii2T1.taytaLiike(1);
     *   Liike lii3T2 = new Liike(); lii3T2.taytaLiike(2); 
     *   Liike lii4T2 = new Liike(); lii4T2.taytaLiike(2); 
     *   Liike lii5T2 = new Liike(); lii5T2.taytaLiike(2); 
     *   liikkeet.lisaa(lii1T1);
     *   liikkeet.lisaa(lii2T1);
     *   liikkeet.lisaa(lii3T2);
     *   liikkeet.lisaa(lii4T2);
     *   liikkeet.poista(lii5T2) === false ; liikkeet.getLkm() === 4;
     *   liikkeet.poista(lii1T1) === true;   liikkeet.getLkm() === 3;
     *   List<Liike> liikkeet1 = liikkeet.anna(1);
     *   liikkeet1.size() === 1; 
     *   liikkeet1.get(0) === lii2T1;
     * </pre>
     */
    public boolean poista(Liike liike) {
        boolean tulos = alkiot.remove(liike);
        if (tulos)
            onkoMuutettu = true;
        return tulos;
    }
    
    
    /**
     * Poistaa kaikki tietyn treenin liikkeet
     * @param tunnusNro viite treeniin, mihin liittyvät liikkeet poistetaan
     * @return montako poistettiin 
     * @example
     * <pre name="test">
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike(); lii1T1.taytaLiike(1);
     *   Liike lii2T1 = new Liike(); lii2T1.taytaLiike(1);
     *   Liike lii3T2 = new Liike(); lii3T2.taytaLiike(2); 
     *   Liike lii4T2 = new Liike(); lii4T2.taytaLiike(2); 
     *   Liike lii5T2 = new Liike(); lii5T2.taytaLiike(2); 
     *   liikkeet.lisaa(lii1T1);
     *   liikkeet.lisaa(lii2T1);
     *   liikkeet.lisaa(lii3T2);
     *   liikkeet.lisaa(lii4T2);
     *   liikkeet.lisaa(lii5T2);
     *   liikkeet.poista(2) === 3;  liikkeet.getLkm() === 2;
     *   liikkeet.poista(3) === 0;  liikkeet.getLkm() === 2;
     *   List<Liike> liikkeet2 = liikkeet.anna(2);
     *   liikkeet2.size() === 0; 
     *   liikkeet2 = liikkeet.anna(1);
     *   liikkeet2.get(0) === lii1T1;
     *   liikkeet2.get(1) === lii2T1;
     * </pre>
     */
    public int poista(int tunnusNro) {
        int lkm = 0;
        for (Iterator<Liike> it = alkiot.iterator(); it.hasNext();) {
            Liike lii = it.next();
            if ( lii.getTreeniNro() == tunnusNro ) {
                it.remove();
                lkm++;
            }
        }
        if (lkm > 0) onkoMuutettu = true;
        return lkm;
    }
    
    
    /**
     * Lukee liikkeet tiedostosta.  
     * @param hakemisto hakemiston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike(); lii1T1.taytaLiike(1);
     *   Liike lii2T1 = new Liike(); lii2T1.taytaLiike(1); 
     *   Liike lii3T2 = new Liike(); lii3T2.taytaLiike(2); 
     *   Liike lii4T2 = new Liike(); lii4T2.taytaLiike(2);
     *   Liike lii5T2 = new Liike(); lii5T2.taytaLiike(2); 
     *   String nimi = "testi";
     *   File tiedosto = new File(nimi + "/liikkeet.dat");
     *   File dir = new File(nimi);
     *   dir.mkdir();
     *   tiedosto.delete();
     *   liikkeet.lisaa(lii1T1);
     *   liikkeet.lisaa(lii2T1);
     *   liikkeet.lisaa(lii3T2);
     *   liikkeet.lisaa(lii4T2);
     *   liikkeet.lisaa(lii5T2);
     *   liikkeet.talleta(nimi);
     *   Iterator<Liike> i = liikkeet.iterator();
     *   i.next().toString() === lii1T1.toString();
     *   i.next().toString() === lii2T1.toString();
     *   i.next().toString() === lii3T2.toString();
     *   i.next().toString() === lii4T2.toString();
     *   i.next().toString() === lii5T2.toString();
     *   liikkeet = new Liikkeet();
     *   i = liikkeet.iterator();
     *   i.hasNext() === false;
     *   liikkeet.lueTiedostosta(nimi);
     *   liikkeet.lisaa(lii5T2);
     *   liikkeet.talleta(nimi);
     *   tiedosto.delete() === true;
     *   dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostoNimi = hakemisto + "/liikkeet.dat";
        File tiedosto = new File(tiedostoNimi);
        try (Scanner fi = new Scanner(new FileInputStream(tiedosto))) {
            while (fi.hasNext()) {
                String rivi = fi.nextLine();
                if ( rivi == null || "".equals(rivi) || rivi.charAt(0) == ';')
                    continue;
                Liike lii = new Liike();
                lii.parse(rivi);
                lisaa(lii);
            }
            onkoMuutettu = false;
        } catch (FileNotFoundException ex) {
            throw new SailoException("Ei saa luettua tiedostoa " + tiedostoNimi);
        } 
    }
    
    
    /**
     * Tallentaa liikkeet tiedostoon.
     * Tiedoston muoto:
     * <pre>
     * ;id|tid|liikkeennimi|paino|sarjat|toistot
     * 1|1|penkkipunnerrus|60|3|5
     * 2|1|kyykky|80|3|5
     * 3|1|kulmasoutu|45|3|8
     * 4|2|leuanveto|0|3|8
     * 5|2|dippi|0|2|12
     * 6|2|jalkaprässi|40|4|10
     * 7|3|pystypunnerrus|40|3|6
     * 8|3|kyykky|80|3|6
     * 9|3|kulmasoutu|45|3|8
     * 10|3|selkäojennus|5|3|8
     * </pre>
     * @param hakemisto tallennettavan tiedoston hakemisto
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta(String hakemisto) throws SailoException {
        if (!onkoMuutettu)
            return;
        File tiedosto = new File(hakemisto + "/liikkeet.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedosto, false))) {
            for (var lii: alkiot) {
                fo.println(lii.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + tiedosto.getAbsolutePath() + " ei aukea");
        }
        onkoMuutettu = false;
    }
    
    
    /**
     * Tallentaa liikkeet tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        talleta(tiedostoNimi);
    }
    
    
    /**
     * Iteraattori kaikkien liikkeiden läpikäymiseen
     * @return liikeiteraattori
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike(1); liikkeet.lisaa(lii1T1);
     *   Liike lii2T1 = new Liike(1); liikkeet.lisaa(lii2T1);
     *   Liike lii3T2 = new Liike(2); liikkeet.lisaa(lii3T2);
     *   Liike lii4T2 = new Liike(2); liikkeet.lisaa(lii4T2);
     *   Liike lii5T2 = new Liike(2); liikkeet.lisaa(lii5T2);
     *   Iterator<Liike> i2=liikkeet.iterator();
     *   i2.next() === lii1T1;
     *   i2.next() === lii2T1;
     *   i2.next() === lii3T2;
     *   i2.next() === lii4T2;
     *   i2.next() === lii5T2;
     *   i2.next() === lii4T2;  #THROWS NoSuchElementException  
     *   int n = 0;
     *   int treNrot[] = {1,1,2,2,2};
     *   for ( Liike lii : liikkeet ) { 
     *     lii.getTreeniNro() === treNrot[n]; n++;  
     *   }
     *   n === 5;
     * </pre>
     */
    @Override
    public Iterator<Liike> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Haetaan kaikki treenin liikkeet
     * @param tunnusnro treenin tunnusnumero jolle liikkeitä haetaan
     * @return tietorakenne jossa viiteet löydettyihin liikkeisiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     *   Liikkeet liikkeet = new Liikkeet();
     *   Liike lii1T1 = new Liike(1); liikkeet.lisaa(lii1T1);
     *   Liike lii2T1 = new Liike(1); liikkeet.lisaa(lii2T1);
     *   Liike lii3T2 = new Liike(2); liikkeet.lisaa(lii3T2);
     *   Liike lii4T2 = new Liike(2); liikkeet.lisaa(lii4T2);
     *   Liike lii5T2 = new Liike(2); liikkeet.lisaa(lii5T2);
     *   Liike lii6T5 = new Liike(5); liikkeet.lisaa(lii6T5);
     *   List<Liike> loytyneet;
     *   loytyneet = liikkeet.anna(3);
     *   loytyneet.size() === 0; 
     *   loytyneet = liikkeet.anna(1);
     *   loytyneet.size() === 2; 
     *   loytyneet.get(0) == lii1T1 === true;
     *   loytyneet.get(1) == lii2T1 === true;
     *   loytyneet = liikkeet.anna(5);
     *   loytyneet.size() === 1; 
     *   loytyneet.get(0) == lii6T5 === true;
     * </pre> 
     */
    public List<Liike> anna(int tunnusnro) {
        List<Liike> loydetyt = new ArrayList<Liike>();
        for (Liike lii : alkiot)
            if (lii.getTreeniNro() == tunnusnro)
                loydetyt.add(lii);
        return loydetyt;
    }
    
    
    /**
     * Testiohjelma liikkeille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Liikkeet liikkeet = new Liikkeet();
        // Lue Liikkeet
        try {
            liikkeet.lueTiedostosta("sepe");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        // Luo Liikkeet
        Liike lii1 = new Liike();
        Liike lii2 = new Liike();
        Liike lii3 = new Liike();
        Liike lii4 = new Liike();
        lii1.taytaLiike(1);
        lii1.rekisteroi();
        lii2.taytaLiike(2);
        lii2.rekisteroi();
        lii3.taytaLiike(2);
        lii3.rekisteroi();
        lii4.taytaLiike(2);
        lii4.rekisteroi();
        // Lisää Liikkeet
        liikkeet.lisaa(lii1);
        liikkeet.lisaa(lii2);
        liikkeet.lisaa(lii3);
        liikkeet.lisaa(lii4);
        // Tulosta Liikkeet
        System.out.println("============= Liikkeet testi =================");
        var liikkeet2 = liikkeet.anna(2);
        for (Liike lii : liikkeet2) {
            System.out.print(lii.getTreeniNro() + " ");
            lii.tulosta(System.out);
        }
        // Tallenna Liikkeet
        try {
            liikkeet.talleta("sepe");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
