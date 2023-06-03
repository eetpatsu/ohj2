package punttisalimuistio;

import java.io.*;

/**
 * Punttisalimuistion Treeni-luokka
 * @author Eetu
 * @version 03.06.2023 Tiedoston synty
 */
public class Treeni {
    private int        tunnusNro;
    private String     pvm              = "";
    private String     sijainti         = "";
    private int        kesto;
    private int        fiilikset;
    private String     muistiinpanot;
    private static int seuraavaNro      = 1;
    
    
    /**
     * Palauttaa treenin tunnusnumeron.
     * @return treenin tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Palauta pvm
     * @return treenin päivämäärä
     * <pre name="test">
     *   Treeni tre = new Treeni();
     *   tre.vastaaTreeni();
     *   tre.getPvm() =R= "12.06.2023";
     * </pre>
     */
    public String getPvm() {
        return this.pvm;
    }
    
    
    /**
     * Apumetodi, luo testiarvot treenille.
     */
    public void vastaaTreeni() {
        this.pvm = "12.06.2023";
        this.sijainti = "kotikuntosali";
        this.kesto = 70;
        this.fiilikset = 3;
        this.muistiinpanot = "lisää painoja";
    }
    
    
    /**
     * Tulostetaan treenin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3) + "        pvm: " + pvm);
        out.println("      " + "sijainti: " + sijainti);
        out.println("         " + "kesto: " + kesto);
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
     *   Treeni tre2 = new Treeni();
     *   tre2.rekisteroi();
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
        tre.vastaaTreeni();
        tre.tulosta(System.out);

        tre2.vastaaTreeni();
        tre2.tulosta(System.out);

        tre2.vastaaTreeni();
        tre2.tulosta(System.out);
    }
}
