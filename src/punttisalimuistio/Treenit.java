package punttisalimuistio;

import java.io.*;
import java.util.Scanner;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Treenit                             | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | - Treeni          | 
 * | - pitää yllä varsinaista treenirekisteriä, eli     |                   | 
 * |   osaa lisätä ja poistaa treenin                   |                   | 
 * | - lukee ja kirjoittaa treenit.dat -tiedostoon      |                   | 
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
 * Punttisalimuistion Treenit-luokka
 * @author Eetu
 * @version 0.5, 03.06.2023 Tiedoston synty
 */
public class Treenit {
    private static final int MAX_TREENEJA   = 12;                       // Treenien lkm yläraja
    private int              lkm            = 0;                        // Montako treeniä kirjattu
    private String           tiedostoNimi   = "";
    private Treeni           alkiot[]       = new Treeni[MAX_TREENEJA];
    
    
    /**
     * Oletusmuodostaja
     */
    public Treenit() {
        // Attribuutit jo alustettu
    }
    
    
    /**
     * Palauttaa punttisalimuistion treenien lukumäärän
     * @return treenien lukumäärä
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
     * Lisää uuden treenin tietorakenteeseen.  Ottaa treenin omistukseensa.
     * @param treeni lisättävän treenin viite.  Huom tietorakenne muuttuu omistajaksi
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     *   Treenit treenit = new Treenit();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   
     *   treenit.getLkm() === 0;
     *   treenit.lisaa(tre1); treenit.getLkm() === 1;
     *   treenit.lisaa(tre2); treenit.getLkm() === 2;
     *   treenit.lisaa(tre1); treenit.getLkm() === 3;
     *   
     *   treenit.anna(0) === tre1;
     *   treenit.anna(1) === tre2;
     *   treenit.anna(2) === tre1;
     *   treenit.anna(1) == tre1 === false;
     *   treenit.anna(1) == tre2 === true;
     *   treenit.anna(3) === tre1; #THROWS IndexOutOfBoundsException 
     *   
     *   treenit.lisaa(tre1); treenit.getLkm() === 4;
     *   treenit.lisaa(tre1); treenit.getLkm() === 5;
     *   
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1);
     *   treenit.getLkm() === 12;
     *   treenit.lisaa(tre2);  #THROWS SailoException
     *   treenit.getLkm() === 12;
     * </pre>
     */
    public void lisaa(Treeni treeni) throws SailoException {
        if (this.lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[this.lkm] = treeni;
        this.lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:teen treeniin.
     * @param i monennenko treenin viite halutaan
     * @return viite treeniin, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     *   Treenit treenit = new Treenit();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   
     *   treenit.getLkm() === 0;
     *   treenit.lisaa(tre1); treenit.getLkm() === 1;
     *   treenit.lisaa(tre2); treenit.getLkm() === 2;
     *   treenit.lisaa(tre1); treenit.getLkm() === 3;
     *   
     *   treenit.anna(0) === tre1;
     *   treenit.anna(1) === tre2;
     *   treenit.anna(2) === tre1;
     *   treenit.anna(1) == tre1 === false;
     *   treenit.anna(1) == tre2 === true;
     *   treenit.anna(3) === tre1; #THROWS IndexOutOfBoundsException 
     * </pre>
     */
    public Treeni anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Lukee treenit tiedostosta.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        // tiedostoNimi = hakemisto + "/treenit.dat";
        String nimi = hakemisto + "/treenit.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) { // Jotta UTF8/ISO-8859 toimii'
            while ( fi.hasNext() ) {
                String s = fi.nextLine();
                if ( s == null || "".equals(s) || s.charAt(0) == ';') continue;
                Treeni treeni = new Treeni();
                treeni.parse(s); // kertoisi onnistumista ???
                lisaa(treeni);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        // } catch ( IOException e ) {
        //     throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }        
    }


    /**
     * Tallentaa treenit tiedostoon.
     * Tiedoston muoto:
     * <pre>
     * 1|09.06.2023|kotikuntosali|60|5|-|
     * 2|10.06.2023|ulkokuntosali|50|1|jatkossa juotavaa|
     * 3|12.06.2023|kotikuntosali|70|3|lisää painoja|
     * </pre>
     * @param hakemisto tallennettavan tiedoston hakemisto
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta(String hakemisto) throws SailoException {
        File ftied = new File(hakemisto + "/treenit.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i=0; i < getLkm(); i++) {
                Treeni treeni = anna(i);
                fo.println(treeni.toString());
            }
        } catch (FileNotFoundException ex) {
                throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
    }
    
    
    /**
     * Testiohjelma treeneille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Treenit treenit = new Treenit();
        Treeni tre1 = new Treeni();
        Treeni tre2 = new Treeni();
        tre1.rekisteroi();
        tre1.taytaTreeni();
        tre2.rekisteroi();
        tre2.taytaTreeni();
        try {
            treenit.lisaa(tre1);
            treenit.lisaa(tre2);
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            System.err.println(ex.getMessage());
        }
        
        System.out.println("============= Treenit testi =================");
        for (int i = 0; i < treenit.getLkm(); i++) {
            Treeni treeni = treenit.anna(i);
            System.out.println("Treeni nro: " + i);
            treeni.tulosta(System.out);
        }
        
        try {
            treenit.talleta("aku");
        } catch (SailoException ex) {
            // ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }
}
