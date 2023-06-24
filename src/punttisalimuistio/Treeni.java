package punttisalimuistio;

import static kanta.Apu.*;
import java.io.*;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * Avustajat: -
 * Vastuualueet: Ei tiedä muistiosta, eikä käyttöliittymästä.
 * Tietää treenin kentät ja osaa tarkistaa tietyn kentän syntaksin oikeellisuuden.
 * Osaa muuttaa "1|09.06.2023|kotikuntosali|60|5|-" -merkkijonon treenin tiedoiksi. 
 * Osaa antaa merkkijonona i:n kentän tiedot 
 * ja osaa laittaa merkkijonon i:neksi kentäksi.
 * 
 * Punttisalimuistion Treeni-luokka
 * @author Eetu
 * @version 0.5, 03.06.2023 Tiedoston synty
 * @version 0.6, 13.06.2023 Tiedostonkäsittely
 * @version 0.7.1, 22.06.2023 Treenin näyttäminen
 * @version 0.7.4, 23.06.2023 Kloonaaminen
 */
public class Treeni implements Cloneable {
    private int        tunnusNro;               // liikkeen id
    private String     pvm              = "";   // treenin päivämäärä
    private String     sijainti         = "";   // missä tapahtui
    private int        kesto;                   // kuinka kauan kesti minuuteissa
    private int        fiilikset;               // fiilikset 1-5 asteikolla
    private String     muistiinpanot;           // tärkeitä pohdintoja
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
        return this.tunnusNro;
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
        return this.pvm;
    }
    
    /**
     * Palauttaa treenin sijainnin
     * @return treenin sijainti
     */
    public String getSijainti() {
        return this.sijainti;
    }
    
    
    /**
     * Palauttaa treenin keston
     * @return treenin kesto
     */
    public String getKesto() {
        return "" + this.kesto;
    }
    
    
    /**
     * Palauttaa treenin fiilikset
     * @return treenin fiilikset
     */
    public String getFiilikset() {
        return "" + this.fiilikset;
    }
    
    
    /**
     * Palauttaa treenin muistiinpanot
     * @return treenin muistiinpanot
     */
    public String getMuistiinpanot() {
        return this.muistiinpanot;
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
     * Asettaa treenin päivämäärän jos annettu syöte kelpaa sellaiseksi
     * @param syote joka yritetään laittaa päivämäräksi
     * @return virheteksti jos syöte ei kelpaa päivämääräksi
     */
    public String setPvm(String syote) {
        this.pvm = syote;
        return null;
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
        this.pvm = "12.06.2023";
        this.sijainti = sijainteja[rand(0,2)];
        this.kesto = rand(20,90);
        this.fiilikset = rand(1,5);
        this.muistiinpanot = "lisää painoja";
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
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
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
                this.tunnusNro + "|" +
                this.pvm + "|" + 
                this.sijainti + "|" +
                this.kesto + "|" +
                this.fiilikset + "|" +
                this.muistiinpanot;
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
        setTunnusNro(Mjonot.erota(sb, '|', this.tunnusNro));
        this.pvm = Mjonot.erota(sb, '|', this.pvm);
        this.sijainti = Mjonot.erota(sb, '|', this.sijainti);
        this.kesto = Mjonot.erota(sb, '|', this.kesto);
        this.fiilikset = Mjonot.erota(sb, '|', this.fiilikset);
        this.muistiinpanot = Mjonot.erota(sb, '|', this.muistiinpanot);
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
