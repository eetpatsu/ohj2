package punttisalimuistio;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

import static kanta.Apu.rand;

/**
 * Avustajat: -
 * Vastuualueet: Ei tiedä muistiosta, eikä käyttöliittymästä.
 * Tietää liikkeen kentät ja osaa tarkistaa tietyn kentän syntaksin oikeellisuuden.
 * Osaa muuttaa "2|1|kyykky|80|3|5" -merkkijonon liikkeen tiedoiksi.
 * Osaa antaa merkkijonona i:n kentän tiedot
 * ja osaa laittaa merkkijonon i:neksi kentäksi.
 * 
 * Punttisalimuistion Liike-luokka
 * @author Eetu
 * @version 0.5, 07.06.2023 Tiedoston synty
 * @version 0.6, 16.06.2023 Tiedostonhallinta
 */
public class Liike {
    private int        tunnusNro;               // liikkeen id
    private int        treeniNro;               // treenin id
    private String     liikkeenNimi     = "";   // minkä niminen liike
    private int        paino;                   // millä painoilla
    private int        sarjat;                  // montako sarjaa
    private int        toistot;                 // montako kertaa toistettiin
    private static int seuraavaNro      = 1;    // seuraavan liikkeen id
    
    
    /**
     * Oletusmuodostaja
     */
    public Liike() {
        // Attribuutit jo alustettu
    }
    
    
    /**
     * Muodosta tietyn treenin liike
     * @param treeniNro treenin viitenumero
     */
    public Liike(int treeniNro) {
        this.treeniNro = treeniNro;
    }
    
    
    /**
     * Palautetaan liikkeen oma id
     * @return liikkeen id
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.getTunnusNro() === 0;
     *   lii1.rekisteroi();
     *   int n1 = lii1.getTunnusNro();
     *   n1 > 0 && n1+1 == lii1.getSeuraavaNro() === true;
     * </pre>
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    
    /**
     * Palauttaa treenin pvm
     * @return treenin päivämäärä
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.rekisteroi();
     *   lii1.getSeuraavaNro() == 1 + lii1.getTunnusNro() === true;
     * </pre>
     */
    public int getSeuraavaNro() {
        return seuraavaNro;
    }
    
    
    /**
     * Palautetaan mille treenille liike kuuluu
     * @return jäsenen id
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.getTreeniNro() === 0;
     *   lii1.parse("1|1|penkkipunnerrus|60|3|5");
     *   lii1.getTreeniNro() === 1;
     * </pre>
     */
    public int getTreeniNro() {
        return this.treeniNro;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nro asetettava tunnusnumero
     */
    private void setTunnusNro(int nro) {
        this.tunnusNro = nro;
        if (this.tunnusNro >= seuraavaNro) {
            seuraavaNro = this.tunnusNro + 1;
        }
    }
    
    
    /**
     * Apumetodi, luo testiarvot liikkeelle.
     * @param treNro id treenille, jonka liikkeestä kyse
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.getTreeniNro() === 0;
     *   lii1.taytaLiike(1);
     *   lii1.getTreeniNro() === 1;
     * </pre>
     */
    public void taytaLiike(int treNro) {
        String[] liikkeita = {"penkkipunnerrus","soutu","pystypunnerrus","kyykky","dippi"};
        this.treeniNro = treNro;
        this.liikkeenNimi = liikkeita[rand(0,4)];
        this.paino = rand(0,60);
        this.sarjat = rand(1,12);
        this.toistot = rand(1,5);
    }
    
    
    /**
     * Tulostetaan liikkeen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.liikkeenNimi + " " + this.paino + "kg " + this.sarjat + " x " + this.toistot);
    }
    
    
    /**
     * Tulostetaan liikkeen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa liikkeelle seuraavan rekisterinumeron.
     * @return liikkeen uusi tunnusNro
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.getTunnusNro() === 0;
     *   lii1.rekisteroi();
     *   int n1 = lii1.getTunnusNro();
     *   n1 > 0 && n1+1 == lii1.getSeuraavaNro() === true;
     *   Liike lii2 = new Liike();
     *   lii2.getTunnusNro() === 0;
     *   lii2.rekisteroi();
     *   int n2 = lii2.getTunnusNro();
     *   n2 > 0 && n2+1 == lii2.getSeuraavaNro() === true;
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }
    
    
    /**
     * Palauttaa treenin tiedot merkkijonona
     * @return treeni tolppaerotettuna merkkijonona
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.parse("1|1|penkkipunnerrus|60|3|5");
     *   lii1.toString().startsWith("1|1|penkkipunnerrus|60|") === true; // on yli 4 kenttää, siksi loppuu tolppaan
     * </pre> 
     */
    @Override
    public String toString() {
        return "" +
                this.tunnusNro + "|" +
                this.treeniNro + "|" + 
                this.liikkeenNimi + "|" +
                this.paino + "|" +
                this.sarjat + "|" +
                this.toistot;
    }
    
    
    /**
     * Selvitää jäsenen tiedot tolpilla erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta treenin tiedot otetaan
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.parse("1|1|penkkipunnerrus|60|3|5");
     *   lii1.getTunnusNro() === 1;
     *   lii1.getTreeniNro() === 1;
     *   lii1.toString().startsWith("1|1|penkkipunnerrus|60|") === true; // on yli 4 kenttää, siksi loppu |
     *   lii1.rekisteroi();
     *   int n = lii1.getTunnusNro();
     *   lii1.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   lii1.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   lii1.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', this.tunnusNro));
        this.treeniNro = Mjonot.erota(sb, '|', this.treeniNro);
        this.liikkeenNimi = Mjonot.erota(sb, '|', this.liikkeenNimi);
        this.paino = Mjonot.erota(sb, '|', this.paino);
        this.sarjat = Mjonot.erota(sb, '|', this.sarjat);
        this.toistot = Mjonot.erota(sb, '|', this.toistot);
    }
    
    
    /**
     * Testiohjelma liikkeelle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Liike lii1 = new Liike();
        Liike lii2 = new Liike();
        lii1.rekisteroi();
        lii2.rekisteroi();
        
        lii1.tulosta(System.out);
        lii1.taytaLiike(1);
        lii1.tulosta(System.out);

        lii2.taytaLiike(2);
        lii2.tulosta(System.out);

        lii2.taytaLiike(3);
        lii2.tulosta(System.out);
    }
}