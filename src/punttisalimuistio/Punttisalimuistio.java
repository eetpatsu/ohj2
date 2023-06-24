package punttisalimuistio;

import java.io.File;
import java.util.*;

/**
 * Avustajat: Treenit, Treeni, Liikkeet, Liike 
 * Vastuualueet: huolehtii Treenit ja Liikkeet -luokkien välisestä yhteistyöstä
 * ja välittää näitä tietoja pyydettäessä.
 * Lukee ja kirjoittaa muistion tiedostoihin pyytämällä apua avustajiltaan
 * 
 * Punttisalimuistio-luokka, joka huolehtii treeneistä ja liikkeistä.
 * Pääosin kaikki metodit ovat vain "välittäjämetodeja".
 * @author Eetu
 * @version 0.4, 03.06.2023 Tiedoston synty
 * @version 0.5, 08.06.2023 Liikkeet ja Liike mukaan
 * @version 0.6, 14.06.2023 Tiedostonhallinta
 * @version 0.7.1, 22.06.2023 Rajaton määrä Treenejä
 * @version 0.7.4, 23.06.2023 Lisää tai korvaa olemassaoleva
 */
public class Punttisalimuistio {
    private Treenit treenit = new Treenit();              // Treenit-olioviite
    private Liikkeet liikkeet = new Liikkeet();           // Liikkeet-olioviite
    private String hakemisto = "aku";
    
    
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
     * @return tietorakenne jossa viitteet löydettyihin liikkeisiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni(), tre2 = new Treeni(), tre3 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi(); tre3.rekisteroi();
     *   int id1 = tre1.getTunnusNro();
     *   int id2 = tre2.getTunnusNro();
     *   Liike lii1T1 = new Liike(id1); muistio.lisaa(lii1T1);
     *   Liike lii2T1 = new Liike(id1); muistio.lisaa(lii2T1);
     *   Liike lii3T2 = new Liike(id2); muistio.lisaa(lii3T2);
     *   Liike lii4T2 = new Liike(id2); muistio.lisaa(lii4T2);
     *   Liike lii5T2 = new Liike(id2); muistio.lisaa(lii5T2);
     *   List<Liike> loytyneet;
     *   loytyneet = muistio.annaLiikkeet(tre3);
     *   loytyneet.size() === 0; 
     *   loytyneet = muistio.annaLiikkeet(tre1);
     *   loytyneet.size() === 2; 
     *   loytyneet.get(0) == lii1T1 === true;
     *   loytyneet.get(1) == lii2T1 === true;
     *   loytyneet = muistio.annaLiikkeet(tre2);
     *   loytyneet.size() === 3; 
     *   loytyneet.get(0) == lii3T2 === true;
     *   loytyneet.get(1) == lii4T2 === true;
     *   loytyneet.get(2) == lii5T2 === true;
     * </pre>
     */
    public List<Liike> annaLiikkeet(Treeni treeni) {
        return liikkeet.anna(treeni.getTunnusNro());
    }
    
    
    /**
     * Lisää punttisalimuistioon uuden treenin
     * @param treeni lisättävä treeni
     * @example
     * <pre name="test">
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi();
     *   muistio.getTreeneja() === 0;
     *   muistio.lisaa(tre1); muistio.getTreeneja() === 1;
     *   muistio.lisaa(tre2); muistio.getTreeneja() === 2;
     *   muistio.lisaa(tre1); muistio.getTreeneja() === 3;
     *   muistio.annaTreeni(0) === tre1;
     *   muistio.annaTreeni(1) === tre2;
     *   muistio.annaTreeni(2) === tre1;
     *   muistio.annaTreeni(3) === tre1; #THROWS IndexOutOfBoundsException
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.lisaa(tre1); muistio.lisaa(tre2);
     *   muistio.getTreeneja() === 13;
     * </pre>
     */
    public void lisaa(Treeni treeni) {
        treenit.lisaa(treeni);
    }
    
    
    /**
     * Katsotaan onko jo olemassa treeni samalla id:llä
     * ja korvataan jos on. Muulloin lisätään normaalisti.
     * @param treeni käsiteltävä treeni
     */
    public void korvaaTaiLisaa(Treeni treeni) {
        treenit.korvaaTaiLisaa(treeni);
    }
    
    
    /**
     * Lisää punttisalimuistioon uuden liikkeen
     * @param liike lisättävä liike
     * @throws SailoException jos lisäystä ei voida tehdä
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
    public void lisaa(Liike liike) throws SailoException  {
        liikkeet.lisaa(liike);
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien treenien viitteet 
     * @param hakuehto käytettävä hakuehto  
     * @param indeksi etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä treeneistä 
     * @throws SailoException Jos jotakin menee väärin
     */ 
    public Collection<Treeni> etsi(String hakuehto, int indeksi) throws SailoException {
        return treenit.etsi(hakuehto, indeksi);
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
     * @param nimi käyttäjänimi jota käytetään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni(); tre1.taytaTreeni(); tre1.rekisteroi();
     *   Treeni tre2 = new Treeni(); tre2.taytaTreeni(); tre2.rekisteroi();
     *   Liike lii1T1 = new Liike(); lii1T1.taytaLiike(tre1.getTunnusNro());
     *   Liike lii2T1 = new Liike(); lii2T1.taytaLiike(tre1.getTunnusNro()); 
     *   Liike lii3T2 = new Liike(); lii3T2.taytaLiike(tre2.getTunnusNro()); 
     *   Liike lii4T2 = new Liike(); lii4T2.taytaLiike(tre2.getTunnusNro());
     *   Liike lii5T2 = new Liike(); lii5T2.taytaLiike(tre2.getTunnusNro()); 
     *   String hakemisto = "testi";
     *   File dir = new File(hakemisto);
     *   File tiedostoT  = new File(hakemisto+"/treenit.dat");
     *   File tiedostoL = new File(hakemisto+"/liikkeet.dat");
     *   dir.mkdir();  
     *   tiedostoT.delete();
     *   tiedostoL.delete();
     *   muistio.lueTiedostosta(hakemisto); #THROWS SailoException
     *   muistio.lisaa(tre1);
     *   muistio.lisaa(tre2);
     *   muistio.lisaa(lii1T1);
     *   muistio.lisaa(lii2T1);
     *   muistio.lisaa(lii3T2);
     *   muistio.lisaa(lii4T2);
     *   muistio.lisaa(lii5T2);
     *   muistio.talleta();
     *   muistio = new Punttisalimuistio();
     *   muistio.lueTiedostosta(hakemisto);
     *   List<Liike> loytyneet = muistio.annaLiikkeet(tre2);
     *   Iterator<Liike> il = loytyneet.iterator();
     *   il.next().toString() === lii3T2.toString();
     *   il.next().toString() === lii4T2.toString();
     *   il.next().toString() === lii5T2.toString();
     *   il.hasNext() === false;
     *   muistio.talleta();
     *   tiedostoT.delete() === true;
     *   tiedostoL.delete() === true;
     *   dir.delete()  === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        File dir = new File(nimi);
        dir.mkdir();
        treenit = new Treenit();
        liikkeet = new Liikkeet();
        this.hakemisto = nimi;
        treenit.lueTiedostosta(nimi);
        liikkeet.lueTiedostosta(nimi);
    }
    
    
    /**
     * Tallettaa punttisalimuistion tiedot tiedostoon
     * Vaikka treenien tallentaminen ei onnistuisi,
     * yritetään silti talllentaa liikkeet.
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        String virhe = "";
        try {
            treenit.talleta(this.hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        try {
            liikkeet.talleta(this.hakemisto);
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        if (!virhe.isEmpty())
            throw new SailoException(virhe);
    }
    
    
    /**
     * Testiohjelma punttisalimuistiolle.
     * @param args ei käytössä
     */
    public static void main(String[] args)  {
        Punttisalimuistio muistio = new Punttisalimuistio();
        // Lue Treenit ja Liikkeet
        try {
            muistio.lueTiedostosta("testiaku");
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        // Luo Treenit
        Treeni tre1 = new Treeni();
        Treeni tre2 = new Treeni();
        tre1.rekisteroi();
        tre1.taytaTreeni();
        tre2.rekisteroi();
        tre2.taytaTreeni();
        // Luo Liikkeet
        Liike lii1T1 = new Liike();
        lii1T1.rekisteroi();
        Liike lii2T2 = new Liike();
        lii2T2.rekisteroi();
        Liike lii3T2 = new Liike();
        lii3T2.rekisteroi();
        Liike lii4T2 = new Liike();
        lii4T2.rekisteroi();
        lii1T1.taytaLiike(1);
        lii2T2.taytaLiike(2);
        lii3T2.taytaLiike(2);
        lii4T2.taytaLiike(2);
        // Lisää Treenit
        try {
            muistio.lisaa(tre1);
            muistio.lisaa(tre2);
            // Lisää Liikkeet
            muistio.lisaa(lii1T1);
            muistio.lisaa(lii2T2);
            muistio.lisaa(lii3T2);
            muistio.lisaa(lii4T2);
            // Tulosta Treenit
            for (int i = 0; i < muistio.getTreeneja(); i++) {
                Treeni treeni = muistio.annaTreeni(i);
                treeni.tulosta(System.out);
                // Tulosta Liikkeet
                var liikkeet = muistio.annaLiikkeet(treeni);
                for (Liike liike : liikkeet) {
                    liike.tulosta(System.out);
                }
            }
            // Tallenna Treenit ja Liikkeet
            muistio.talleta();
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
