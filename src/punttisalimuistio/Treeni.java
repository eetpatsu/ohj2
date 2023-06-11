package punttisalimuistio;

import static kanta.Apu.rand;

import java.io.*;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Treeni                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | (- ei tiedä muistiosta, eikä käyttöliittymästä)    |                   | 
 * | - tietää treenin kentät                            |                   | 
 * | - osaa tarkistaa tietyn kentän oikeellisuuden      |                   |
 * |   syntaksin)                                       |                   | 
 * | - osaa muuttaa 1|09.06.2023|kotikuntosali|60|5|-|  |                   |
 * |   -merkkijonon treenin tiedoiksi                   |                   | 
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kentäksi        |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |-------------------------------------------------------------------------
 * Punttisalimuistion Treeni-luokka
 * @author Eetu
 * @version 0.5, 03.06.2023 Tiedoston synty
 */
public class Treeni {
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
        // Vielä ei tarvita mitään
    }
    
    
    /**
     * Palauttaa treenin tunnusnumeron.
     * @return treenin tunnusnumero
     * <pre name="test">
     *   Treeni tre1 = new Treeni();
     *   tre1.getTunnusNro() === 0;
     *   tre1.rekisteroi();
     *   tre1.getTunnusNro() === 1;
     *   
     *   Treeni tre2 = new Treeni();
     *   tre2.rekisteroi();
     *   tre2.getTunnusNro() === 2;
     * </pre>
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    
    /**
     * Palauttaa treenin pvm
     * @return treenin päivämäärä
     * <pre name="test">
     *   Treeni tre = new Treeni();
     *   tre.getPvm() === "";
     *   tre.taytaTreeni();
     *   tre.getPvm() === "12.06.2023";
     * </pre>
     */
    public String getPvm() {
        return this.pvm;
    }
    
    
    /**
     * Apumetodi, luo testiarvot treenille.
     * @example
     * <pre name="test">
     *   Treeni tre = new Treeni();
     *   tre.getPvm() === "";
     *   tre.taytaTreeni();
     *   tre.getPvm() === "12.06.2023";
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
     *   tre1.getTunnusNro() === 3;
     *   
     *   Treeni tre2 = new Treeni();
     *   tre2.getTunnusNro() === 0;
     *   tre2.rekisteroi();
     *   tre2.getTunnusNro() === 4;
     *   
     *   int n1 = tre1.getTunnusNro();
     *   int n2 = tre2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Testiohjelma treenille.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Treeni tre = new Treeni(), tre2 = new Treeni();
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
