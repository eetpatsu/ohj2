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
 * @author eetpatsu@student.jyu.fi
 * @version 0.4, 03.06.2023 Tiedoston synty
 * @version 0.5, 08.06.2023 Liikkeet ja Liike mukaan
 * @version 0.6, 14.06.2023 Tiedostonhallinta
 * @version 0.7.1, 22.06.2023 Rajaton määrä Treenejä
 * @version 0.7.4, 25.06.2023 Lisää tai korvaa olemassaoleva treeni
 * @version 0.7.5, 26.06.2023 Hakeminen, Lisää tai korvaa olemassaoleva liike
 * @version 0.7.6, 27.06.2023 Poistaminen
 */
public class Punttisalimuistio {
    private Treenit treenit = new Treenit();              // Treenit-olioviite
    private Liikkeet liikkeet = new Liikkeet();           // Liikkeet-olioviite
    private String hakemisto = "";
    
    
    /**
     * Palauttaa punttisalimuistioon kirjattujen treenien määrän
     * @return treenien määrä
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
     * </pre>
     */
    public int getTreeneja() {
        return treenit.getLkm();
    }
    
    
    /**
     * Palauttaa i:n treenin
     * @param i monesko treeni palautetaan
     * @return viite i:teen treeniin
     * @example
     * <pre name="test">
     * #THROWS IndexOutOfBoundsException
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
    public Treeni annaTreeni(int i) {
        return treenit.anna(i);
    }
    
    
    /**
     * Haetaan kaikki treenin liikkeet
     * @param treeni minkä treenin liikkeet haetaan
     * @return tietorakenne jossa viitteet löydettyihin liikkeisiin
     * @throws SailoException jos tulee ongelmia
     * @example
     * <pre name="test">
     * #THROWS SailoException
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
    public List<Liike> annaLiikkeet(Treeni treeni) throws SailoException {
        return liikkeet.anna(treeni.getTunnusNro());
    }
    
    
    /**
     * Lisää punttisalimuistioon uuden treenin
     * @param treeni lisättävä treeni
     * @example
     * <pre name="test">
     * #THROWS IndexOutOfBoundsException
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
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * #THROWS SailoException
     *   Treenit treenit = new Treenit();
     *   Treeni tre1 = new Treeni();
     *   tre1.rekisteroi();
     *   tre1.taytaTreeni();
     *   treenit.lisaa(tre1);
     *   Treeni tre2 = new Treeni();
     *   tre2.rekisteroi();
     *   tre2.taytaTreeni();
     *   treenit.lisaa(tre2);
     *   treenit.getLkm() === 2;
     *   treenit.korvaaTaiLisaa(tre1);
     *   treenit.korvaaTaiLisaa(tre2);
     *   treenit.getLkm() === 2;
     *   Treeni klooni = tre1.clone();
     *   tre1.toString().equals(klooni.toString()) === true;
     *   treenit.korvaaTaiLisaa(klooni);
     *   treenit.getLkm() === 2;
     *   tre1.parse("1|09.06.2023|kotikuntosali|60|5|-");
     *   tre1.toString().equals(klooni.toString()) === false;
     *   treenit.korvaaTaiLisaa(tre1);
     *   treenit.getLkm() === 3;
     * </pre>
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
     *   liikkeet.getLkm() === 2;
     *   liikkeet.korvaaTaiLisaa(lii1T1);
     *   liikkeet.korvaaTaiLisaa(lii2T1);
     *   liikkeet.getLkm() === 2;
     *   Liike klooni = lii1T1.clone();
     *   lii1T1.toString().equals(klooni.toString()) === true;
     *   liikkeet.korvaaTaiLisaa(klooni);
     *   liikkeet.getLkm() === 2;
     *   lii1T1.parse("3|1|kyykky|80|3|5");
     *   lii1T1.toString().equals(klooni.toString()) === false;
     *   liikkeet.korvaaTaiLisaa(lii1T1);
     *   liikkeet.getLkm() === 3;
     * </pre>
     */
    public void korvaaTaiLisaa(Liike liike) {
        liikkeet.korvaaTaiLisaa(liike);
    }
    
    
    /**
     * Palauttaa "taulukossa" hakuehtoa vastaavien treenien viitteet"
     * @param ehto hakuehto
     * @param kenttaNro kentän indeksi jonka mukaan etsitään
     * @return tietorakenteen löytyneistä treeneistä 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Treenit treenit = new Treenit(); 
     *   Treeni tre1 = new Treeni(), tre2 = new Treeni(), tre3 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi(); tre3.rekisteroi(); 
     *   int nro = tre1.getTunnusNro(); 
     *   treenit.lisaa(tre1); treenit.lisaa(tre2); treenit.lisaa(tre3); 
     *   treenit.etsiIndeksi(nro+1) === 1; 
     *   treenit.etsiIndeksi(nro+2) === 2; 
     * </pre> 
     */
    public Collection<Treeni> etsi(String ehto, int kenttaNro) {
        return treenit.etsi(ehto, kenttaNro);
    }
    
    
    /**
     * Poistaa annetun treenin
     * @param treeni joka poistetaan
     * @return montako treeniä poistettiin
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni(); tre1.taytaTreeni(); tre1.rekisteroi();
     *   muistio.lisaa(tre1);
     *   Liike lii1T1 = new Liike(); lii1T1.taytaLiike(tre1.getTunnusNro());
     *   muistio.lisaa(lii1T1);
     *   muistio.etsi("*",0).size() === 1;
     *   muistio.annaLiikkeet(tre1).size() === 1;
     *   muistio.poista(tre1) === 1;
     *   muistio.etsi("*",0).size() === 0;
     *   muistio.annaLiikkeet(tre1).size() === 0;
     * </pre>
     */
    public int poista(Treeni treeni) {
        if ( treeni == null )
            return 0;
        int ret = treenit.poista(treeni.getTunnusNro());
        liikkeet.poista(treeni.getTunnusNro());
        return ret;
    }
    
    
    /**
     * Poistaa annetun liikkeen.
     * @param liike joka poistetaan
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Punttisalimuistio muistio = new Punttisalimuistio();
     *   Treeni tre1 = new Treeni(); tre1.taytaTreeni(); tre1.rekisteroi();
     *   muistio.lisaa(tre1);
     *   Liike lii1T1 = new Liike(); lii1T1.taytaLiike(tre1.getTunnusNro());
     *   muistio.lisaa(lii1T1);
     *   muistio.etsi("*",0).size() === 1;
     *   muistio.annaLiikkeet(tre1).size() === 1;
     *   muistio.poista(lii1T1);
     *   muistio.annaLiikkeet(tre1).size() === 0;
     * </pre>
     */
    public void poista(Liike liike) {
        liikkeet.poista(liike); 
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
        hakemisto = nimi;
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
            treenit.talleta(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        try {
            liikkeet.talleta(hakemisto);
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
            muistio.lueTiedostosta("roope");
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
