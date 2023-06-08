package punttisalimuistio;

import java.io.*;
import static kanta.Apu.rand;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Liike                               | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | (- ei tiedä muistiosta, eikä käyttöliittymästä)    |                   | 
 * | - tietää liikkeen kentät                           |                   | 
 * | - osaa tarkistaa tietyn kentän oikeellisuuden      |                   |
 * |   (syntaksin)                                      |                   | 
 * | - osaa muuttaa 2|1|kyykky|80|3|5| -merkkijonon     |                   |
 * |   liikkeen tiedoiksi                               |                   | 
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kentäksi        |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |-------------------------------------------------------------------------
 * Punttisalimuistion Liike-luokka
 * @author Eetu
 * @version 07.06.2023 Tiedoston synty
 */
public class Liike {
    private int        tunnusNro;
    private int        treeniNro;
    private String     liikkeenNimi     = "";
    private int        paino;
    private int        sarjat;
    private int        toistot;
    private static int seuraavaNro      = 1;
    
    
    /**
     * Oletusmuodostaja
     */
    public Liike() {
        // Vielä ei tarvita mitään
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
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    
    /**
     * Palautetaan mille treenille liike kuuluu
     * @return jäsenen id
     */
    public int getTreeniNro() {
        return this.treeniNro;
    }
    
    
    /**
     * Apumetodi, luo testiarvot liikkeelle.
     * @param treNro id treenille, jonka liikkeestä kyse
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
        out.println(liikkeenNimi + " " + paino + " " + sarjat + " " + toistot);
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
     *   
     *   Liike lii2 = new Liike();
     *   lii2.rekisteroi();
     *   
     *   int n1 = lii1.getTunnusNro();
     *   int n2 = lii2.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
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
