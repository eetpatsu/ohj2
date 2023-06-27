package punttisalimuistio;

import static kanta.Apu.*;
import java.io.*;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Apu;

/**
 * Avustajat: -
 * Vastuualueet: Ei tiedä muistiosta, eikä käyttöliittymästä.
 * Tietää treenin kentät ja osaa tarkistaa tietyn kentän syntaksin oikeellisuuden.
 * Osaa muuttaa "1|09.06.2023|kotikuntosali|60|5|-" -merkkijonon treenin tiedoiksi. 
 * Osaa antaa merkkijonona i:n kentän tiedot 
 * ja osaa laittaa merkkijonon i:neksi kentäksi.
 * 
 * Punttisalimuistion Treeni-luokka
 * @author eetpatsu@student.jyu.fi
 * @version 0.5, 03.06.2023 Tiedoston synty
 * @version 0.6, 13.06.2023 Tiedostonkäsittely
 * @version 0.7.1, 22.06.2023 Treenin näyttäminen
 * @version 0.7.4, 25.06.2023 Kloonaaminen, Oikeellisuustarkistus
 * @version 0.7.5, 26.06.2023 Hakeminen
 */
public class Treeni implements Cloneable {
    private int        tunnusNro;               // liikkeen id
    private String     pvm              = "";   // treenin päivämäärä
    private String     sijainti         = "";   // missä tapahtui
    private int        kesto;                   // kuinka kauan kesti minuuteissa
    private int        fiilikset        = 3;    // fiilikset 1-5 asteikolla
    private String     muistiinpanot    = "";   // tärkeitä pohdintoja
    private static int seuraavaNro      = 1;    // seuraavan treenin id
    
    
    /**
     * Oletusmuodostaja
     */
    public Treeni() {
        // Attribuutit jo alustettu
    }
    
    
    /**
     * Palauttaa treenin tunnusnumeron.
     * @return treenin tunnusnumero
     * @example
     * <pre name="test">
     *   Treeni tre1 = new Treeni();
     *   tre1.getTunnusNro() === 0;
     *   tre1.rekisteroi();
     *   int n1 = tre1.getTunnusNro();
     *   n1 > 0 && n1+1 == tre1.getSeuraavaNro() === true;
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
     *   Treeni tre1 = new Treeni();
     *   tre1.rekisteroi();
     *   tre1.getSeuraavaNro() == 1 + tre1.getTunnusNro() === true;
     * </pre>
     */
    public int getSeuraavaNro() {
        return seuraavaNro;
    }
    
    
    /**
     * Palauttaa treenin pvm
     * @return treenin päivämäärä
     * @example
     * <pre name="test">
     *   Treeni tre1 = new Treeni();
     *   tre1.getPvm() === "";
     *   tre1.taytaTreeni();
     *   tre1.getPvm() === "12.06.2023";
     * </pre>
     */
    public String getPvm() {
        return pvm;
    }
    
    
    /**
     * Palauttaa montako näytettävää kenttää treenissä on
     * @return montako kenttää treenissä on
     */
    public int getKenttaLkm() {
        return 5;
    }
    
    
    /**
     * Palauttaa kenttänumeroa vastaavan kentän kysymyksen
     * @param kenttaNro monennenko kentän kysymys palautetaan
     * @return kenttänumeroa vastaava kysymys
     */
    public String getKysymys(int kenttaNro) {
        switch ( kenttaNro ) {
        case 0: return "pvm";
        case 1: return "sijainti";
        case 2: return "kesto";
        case 3: return "fiilikset";
        case 4: return "muistiinpanot";
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
        case 0: return "" + pvm;
        case 1: return "" + sijainti;
        case 2: return "" + kesto;
        case 3: return "" + fiilikset;
        case 4: return "" + muistiinpanot;
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
     *   Treeni tre = new Treeni();
     *   tre.aseta(0, "abcdefgh") === "kiellettyjä merkkejä";
     *   tre.aseta(0, "12.06.2023") === null;
     *   tre.getPvm() === "12.06.2023";
     *   tre.aseta(1, "4 b c d 3 f g") === null;
     *   tre.aseta(1, "kotikuntosali") === null;
     *   tre.aseta(2, "30 minuuttia") === "kiellettyjä merkkejä";
     *   tre.aseta(2, "30") === null;
     *   tre.aseta(3, "OK") === "fiilikset 1-5 asteikolla";
     *   tre.aseta(3, "0") === "fiilikset 1-5 asteikolla";
     *   tre.aseta(3, "3") === null;
     *   tre.aseta(4, "4 b c d 3 f g") === null;
     *   tre.aseta(4, "lisää painoja") === null;
     * </pre>
     */
    public String aseta(int kenttaNro, String syote) {
        switch (kenttaNro) {
        case 0:
            String virhe = Apu.tarkistaPvm(syote);
            if (virhe != null)
                return virhe;
            pvm = syote;
            return null;
        case 1:
            sijainti = syote;
            return null;
        case 2:
            if (!syote.matches("[0-9]+"))
                return "kiellettyjä merkkejä";
            kesto = Mjonot.erotaInt(syote, 0);
            return null;
        case 3:
            if (!syote.matches("[1-5]"))
                return "fiilikset 1-5 asteikolla";
            fiilikset = Mjonot.erotaInt(syote, 0);
            return null;
        case 4:
            muistiinpanot = syote;
            return null;
        default:
            return "kenttää ei ole";
        }
    }
    
    
    /**
     * Apumetodi, luo testiarvot treenille.
     * @example
     * <pre name="test">
     *   Treeni tre1 = new Treeni();
     *   tre1.getPvm() === "";
     *   tre1.taytaTreeni();
     *   tre1.getPvm() === "12.06.2023";
     * </pre>
     */
    public void taytaTreeni() {
        String[] sijainteja = {"kotikuntosali","ulkokuntosali","yksityinen kuntosali"};
        pvm = "12.06.2023";
        sijainti = sijainteja[rand(0,2)];
        kesto = rand(20,90);
        fiilikset = rand(1,5);
        muistiinpanot = "lisää painoja";
    }
    
    
    /**
     * Tulostetaan treenin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3) + "        pvm: " + pvm);
        out.println("      " + "sijainti: " + sijainti);
        out.println("         " + "kesto: " + kesto + " min");
        out.println("     " + "fiilikset: " + fiilikset);
        out.println(" " + "muistiinpanot: " + muistiinpanot);
    }
    
    
    /**
     * Tulostetaan treenin tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa treenille seuraavan rekisterinumeron.
     * @return treenin uusi tunnusNro
     * @example
     * <pre name="test">
     *   Treeni tre1 = new Treeni();
     *   tre1.getTunnusNro() === 0;
     *   tre1.rekisteroi();
     *   int n1 = tre1.getTunnusNro();
     *   n1 > 0 && n1+1 == tre1.getSeuraavaNro() === true;
     *   Treeni tre2 = new Treeni();
     *   tre2.getTunnusNro() === 0;
     *   tre2.rekisteroi();
     *   int n2 = tre2.getTunnusNro();
     *   n2 > 0 && n2+1 == tre2.getSeuraavaNro() === true;
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Luo ja palauttaa kloonin annetusta treenistä
     * @return Object kloonattu treeni olio
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     *   Treeni tre = new Treeni();
     *   tre.parse("3|12.06.2023|kotikuntosali|70");
     *   Treeni klooni = tre.clone();
     *   tre.toString().equals(klooni.toString()) === true;
     *   tre.parse("1|09.06.2023|kotikuntosali|60|5|-");
     *   tre.toString().equals(klooni.toString()) === false;
     * </pre>
     */
    @Override
    public Treeni clone() throws CloneNotSupportedException {
        return (Treeni)super.clone();
    }
    
    
    /**
     * Palauttaa treenin tiedot merkkijonona
     * @return treeni tolppaerotettuna merkkijonona
     * @example
     * <pre name="test">
     *   Treeni tre1 = new Treeni();
     *   tre1.parse("3|12.06.2023|kotikuntosali|70");
     *   tre1.toString().startsWith("3|12.06.2023|kotikuntosali|70|") === true; // on yli 4 kenttää, siksi loppuu tolppaan
     * </pre> 
     */
    @Override
    public String toString() {
        return "" +
                tunnusNro + "|" +
                pvm + "|" + 
                sijainti + "|" +
                kesto + "|" +
                fiilikset + "|" +
                muistiinpanot;
    }
    
    
    /**
     * Selvitää jäsenen tiedot tolpilla erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta treenin tiedot otetaan
     * @example
     * <pre name="test">
     *   Treeni tre1 = new Treeni();
     *   tre1.parse("3 |12.06.2023|kotikuntosali|70");
     *   tre1.getTunnusNro() === 3;
     *   tre1.getPvm() === "12.06.2023";
     *   tre1.toString().startsWith("3|12.06.2023|kotikuntosali|70|") === true; // on yli 4 kenttää, siksi loppu |
     *   tre1.rekisteroi();
     *   int n = tre1.getTunnusNro();
     *   tre1.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   tre1.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   tre1.getTunnusNro() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', tunnusNro));
        pvm = Mjonot.erota(sb, '|', pvm);
        sijainti = Mjonot.erota(sb, '|', sijainti);
        kesto = Mjonot.erota(sb, '|', kesto);
        fiilikset = Mjonot.erota(sb, '|', fiilikset);
        muistiinpanot = Mjonot.erota(sb, '|', muistiinpanot);
    }
    
    
    /**
     * Luokka päivämäärien vertailuun
     * @author Eetu
     * @version 0.7.5, 26.06.2023
     */
    public static class Vertailija implements Comparator<Treeni> {
        
        /**
         * Vertaa kumman treenin päivämäärä on suurempi
         * @param tre1 Ensimmäinen vertailtava treeni
         * @param tre2 Toinen vertailtava treeni
         * @return -1 jos tre1 < tre2, 0 jos tre1 = tre2, 1 jos tre1 > tre2
         */
        @Override
        public int compare(Treeni tre1, Treeni tre2) {
            return tre2.getPvm().compareTo(tre1.getPvm());
        }
    }
    
    
    /**
     * Testiohjelma treenille.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Treeni tre = new Treeni();
        Treeni tre2 = new Treeni();
        tre.rekisteroi();
        tre2.rekisteroi();
        
        tre.tulosta(System.out);
        tre.taytaTreeni();
        tre.tulosta(System.out);

        tre2.taytaTreeni();
        tre2.tulosta(System.out);

        tre2.taytaTreeni();
        tre2.tulosta(System.out);
    }
}