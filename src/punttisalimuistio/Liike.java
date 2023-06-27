package punttisalimuistio;

import static kanta.Apu.*;
import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;


/**
 * Avustajat: -
 * Vastuualueet: Ei tiedä muistiosta, eikä käyttöliittymästä.
 * Tietää liikkeen kentät ja osaa tarkistaa tietyn kentän syntaksin oikeellisuuden.
 * Osaa muuttaa "2|1|kyykky|80|3|5" -merkkijonon liikkeen tiedoiksi.
 * Osaa antaa merkkijonona i:n kentän tiedot
 * ja osaa laittaa merkkijonon i:neksi kentäksi.
 * 
 * Punttisalimuistion Liike-luokka
 * @author eetpatsu@student.jyu.fi
 * @version 0.5, 07.06.2023 Tiedoston synty
 * @version 0.6, 16.06.2023 Tiedostonhallinta
 * @version 0.7.5, 26.06.2023 Kloonaaminen, Oikeellisuustarkistus
 */
public class Liike implements Cloneable {
    private int        tunnusNro;               // liikkeen id
    private int        treeniNro;               // treenin id
    private String     liikkeenNimi     = "";   // minkä niminen liike
    private Double     paino            = 0.0;  // millä painoilla
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
     * @param nro treenin viitenumero
     */
    public Liike(int nro) {
        treeniNro = nro;
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
        return tunnusNro;
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
        return treeniNro;
    }
    
    
    /**
     * Palauttaa liikkeen nimen
     * @return liikkeen nimi
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.getNimi() === "";
     *   lii1.taytaLiike(1);
     *   lii1.getNimi() === "penkkipunnerrus";
     * </pre>
     */
    public String getNimi() {
        return liikkeenNimi;
    }
    
    
    /**
     * Palauttaa montako näytettävää kenttää liikkeessä on
     * @return montako kenttää liikkeessä on
     */
    public int getKenttaLkm() {
        return 4;
    }
    
    
    /**
     * Palauttaa kenttänumeroa vastaavan kentän kysymyksen
     * @param kenttaNro monennenko kentän kysymys palautetaan
     * @return kenttänumeroa vastaava kysymys
     */
    public String getKysymys(int kenttaNro) {
        switch ( kenttaNro ) {
        case 0: return "liike";
        case 1: return "paino";
        case 2: return "sarjat";
        case 3: return "toistot";
        default: return "kenttää ei ole";
        }
    }
    
    
    /**
     * Antaa kenttänumeroa vastaavan kentän sisällön
     * @param kenttaNro Monennenko kentän sisältö annetaan
     * @return kentän sisältö
     */
    public String anna(int kenttaNro) {
        switch (kenttaNro) {
        case 0: return "" + liikkeenNimi;
        case 1: return "" + paino;
        case 2: return "" + sarjat;
        case 3: return "" + toistot;
        default: return "kenttää ei ole";
        }
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nro asetettava tunnusnumero
     */
    private void setTunnusNro(int nro) {
        tunnusNro = nro;
        if (tunnusNro >= seuraavaNro) {
            seuraavaNro = tunnusNro + 1;
        }
    }
    
    
    /**
     * Asettaa syötteen haluttuun kenttään jos syöte kelpaa.
     * @param kenttaNro Monenteenko kenttään halutaan asettaa
     * @param syote Mitä kenttään asetetaan
     * @return virheilmoitus jos syöte ei kelvannut kenttään, kelvatessa null
     * @example
     * <pre name="test">
     *   Liike lii = new Liike();
     *   lii.aseta(0, "a 1 b 2 c ? defg h") === null;
     *   lii.aseta(0, "penkkipunnerrus") === null;
     *   lii.getNimi() === "penkkipunnerrus";
     *   lii.aseta(1, "50kg") === "anna paino muodossa 0.0";
     *   lii.aseta(1, "50") === "anna paino muodossa 0.0";
     *   lii.aseta(1, "5a.0") === "anna paino muodossa 0.0";
     *   lii.aseta(1, "5.0") === null;
     *   lii.aseta(1, "50.0") === null;
     *   lii.aseta(1, "500.0") === null;
     *   lii.aseta(2, "kolme") === "kiellettyjä merkkejä";
     *   lii.aseta(2, "3") === null;
     *   lii.aseta(3, "-1") === "kiellettyjä merkkejä";
     *   lii.aseta(3, "12") === null;
     * </pre>
     */
    public String aseta(int kenttaNro, String syote) {
        switch (kenttaNro) {
        case 0:
            liikkeenNimi = syote;
            return null;
        case 1:
            if (!syote.matches("[0-9]+\\.[0-9]"))
                return "anna paino muodossa 0.0";
            paino = Mjonot.erotaDouble(syote, 0);
            return null;
        case 2:
            if (!syote.matches("[0-9]+"))
                return "kiellettyjä merkkejä";
            sarjat = Mjonot.erotaInt(syote, 0);
            return null;
        case 3:
            if (!syote.matches("[0-9]+"))
                return "kiellettyjä merkkejä";
            toistot = Mjonot.erotaInt(syote, 0);
            return null;
        default:
            return "kenttää ei ole";
        }
    }
    
    
    /**
     * Apumetodi, luo testiarvot liikkeelle.
     * @param nro id treenille, jonka liikkeestä kyse
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.getTreeniNro() === 0;
     *   lii1.taytaLiike(1);
     *   lii1.getTreeniNro() === 1;
     * </pre>
     */
    public void taytaLiike(int nro) {
        treeniNro = nro;
        liikkeenNimi = "penkkipunnerrus";
        paino = Double.valueOf(rand(0,60));
        sarjat = rand(1,12);
        toistot = rand(1,5);
    }
    
    
    /**
     * Tulostetaan liikkeen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(liikkeenNimi + " " + paino + "kg " + sarjat + " x " + toistot);
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
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Luo ja palauttaa kloonin annetusta liienistä
     * @return Object kloonattu liieni olio
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     *   Liike lii = new Liike();
     *   lii.parse("7|3|pystypunnerrus|40|3|6");
     *   Liike klooni = lii.clone();
     *   lii.toString().equals(klooni.toString()) === true;
     *   lii.parse("1|1|penkkipunnerrus|60|3|5");
     *   lii.toString().equals(klooni.toString()) === false;
     * </pre>
     */
    @Override
    public Liike clone() throws CloneNotSupportedException {
        return (Liike)super.clone();
    }
    
    
    /**
     * Palauttaa treenin tiedot merkkijonona
     * @return treeni tolppaerotettuna merkkijonona
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.parse("1|1|penkkipunnerrus|60.0|3|5");
     *   lii1.toString().startsWith("1|1|penkkipunnerrus|60.0|") === true; // on yli 4 kenttää, siksi loppuu tolppaan
     * </pre> 
     */
    @Override
    public String toString() {
        return "" +
                tunnusNro + "|" +
                treeniNro + "|" + 
                liikkeenNimi + "|" +
                paino + "|" +
                sarjat + "|" +
                toistot;
    }
    
    
    /**
     * Selvitää jäsenen tiedot tolpilla erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta treenin tiedot otetaan
     * @example
     * <pre name="test">
     *   Liike lii1 = new Liike();
     *   lii1.parse("1|1|penkkipunnerrus|60.0|3|5");
     *   lii1.getTunnusNro() === 1;
     *   lii1.getTreeniNro() === 1;
     *   lii1.toString().startsWith("1|1|penkkipunnerrus|60.0|") === true; // on yli 4 kenttää, siksi loppu |
     *   lii1.rekisteroi();
     *   int n = lii1.getTunnusNro();
     *   lii1.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   lii1.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   lii1.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', tunnusNro));
        treeniNro = Mjonot.erota(sb, '|', treeniNro);
        liikkeenNimi = Mjonot.erota(sb, '|', liikkeenNimi);
        paino = Mjonot.erota(sb, '|', paino);
        sarjat = Mjonot.erota(sb, '|', sarjat);
        toistot = Mjonot.erota(sb, '|', toistot);
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