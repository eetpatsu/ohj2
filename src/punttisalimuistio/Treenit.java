package punttisalimuistio;

import java.io.*;
import java.util.*;

/**
 * Avustajat: Treeni
 * Vastuualueet: Pitää yllä varsinaista treenirekisteriä,
 * eli osaa lisätä ja poistaa treenin.
 * Lukee ja kirjoittaa treenit.dat -tiedostoon.
 * Osaa etsiä ja lajitella.
 * 
 * Punttisalimuistion Treenit-luokka
 * @author Eetu
 * @version 0.5, 03.06.2023 Tiedoston synty
 * @version 0.6, 13.06.2023 Tiedostonkäsittely
 * @version 0.7.1, 22.06.2023 Rajaton määrä Treenejä
 */
public class Treenit implements Iterable<Treeni> {
    private static final int MAX_TREENEJA   = 12;                       // Treenien lkm yläraja
    private boolean          onkoMuutettu   = false;                    // Ovatko treenin tiedot muuttuneet alkuperäisestä
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
     * @example
     * <pre name="test">
     *   Treenit treenit = new Treenit();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   treenit.getLkm() === 0;
     *   treenit.lisaa(tre1); treenit.getLkm() === 1;
     *   treenit.lisaa(tre2); treenit.getLkm() === 2;
     *   treenit.lisaa(tre1); treenit.getLkm() === 3;
     *   treenit.anna(0) === tre1;
     *   treenit.anna(1) === tre2;
     *   treenit.anna(2) === tre1;
     *   treenit.anna(1) == tre1 === false;
     *   treenit.anna(1) == tre2 === true;
     *   treenit.anna(3) === tre1; #THROWS IndexOutOfBoundsException
     *   treenit.lisaa(tre1); treenit.getLkm() === 4;
     *   treenit.lisaa(tre1); treenit.getLkm() === 5;
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1);
     *   treenit.getLkm() === 12;
     *   treenit.lisaa(tre1);
     *   treenit.getLkm() === 13;
     * </pre>
     */
    public void lisaa(Treeni treeni) {
        if (this.lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm+12);
        alkiot[this.lkm] = treeni;
        this.lkm++;
        this.onkoMuutettu = true;
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
     *   treenit.getLkm() === 0;
     *   treenit.lisaa(tre1); treenit.getLkm() === 1;
     *   treenit.lisaa(tre2); treenit.getLkm() === 2;
     *   treenit.lisaa(tre1); treenit.getLkm() === 3;
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
     * @param hakemisto hakemiston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *   Treenit treenit = new Treenit();
     *   Treeni tre1 = new Treeni(); tre1.taytaTreeni();
     *   Treeni tre2 = new Treeni(); tre2.taytaTreeni(); 
     *   Treeni tre3 = new Treeni(); tre3.taytaTreeni();
     *   Treeni tre4 = new Treeni(); tre4.taytaTreeni();
     *   Treeni tre5 = new Treeni(); tre5.taytaTreeni(); 
     *   String nimi = "testi";
     *   File tiedosto = new File(nimi + "/treenit.dat");
     *   File dir = new File(nimi);
     *   dir.mkdir();
     *   tiedosto.delete();
     *   treenit.lisaa(tre1);
     *   treenit.lisaa(tre2);
     *   treenit.lisaa(tre3);
     *   treenit.lisaa(tre4);
     *   treenit.lisaa(tre5);
     *   treenit.talleta(nimi);
     *   Iterator<Treeni> i = treenit.iterator();
     *   i.next().toString() === tre1.toString();
     *   i.next().toString() === tre2.toString();
     *   i.next().toString() === tre3.toString();
     *   i.next().toString() === tre4.toString();
     *   i.next().toString() === tre5.toString();
     *   treenit = new Treenit();
     *   i = treenit.iterator();
     *   i.hasNext() === false;
     *   treenit.lueTiedostosta(nimi);
     *   treenit.lisaa(tre5);
     *   treenit.talleta(nimi);
     *   tiedosto.delete() === true;
     *   dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        this.tiedostoNimi = hakemisto + "/treenit.dat";
        File tiedosto = new File(this.tiedostoNimi);
        try (Scanner fi = new Scanner(new FileInputStream(tiedosto))) {
            while (fi.hasNext()) {
                String rivi = fi.nextLine();
                if ( rivi == null || "".equals(rivi) || rivi.charAt(0) == ';')
                    continue;
                Treeni lii = new Treeni();
                lii.parse(rivi);
                lisaa(lii);
            }
            this.onkoMuutettu = false;
        } catch (FileNotFoundException ex) {
            throw new SailoException("Ei saa luettua tiedostoa " + this.tiedostoNimi);
        } 
    }
    
    
    /**
     * Tallentaa treenit tiedostoon.
     * Tiedoston muoto:
     * <pre>
     * ;id|pvm|sijainti|kesto|fiilikset|muistiinpanot
     * 1|09.06.2023|kotikuntosali|60|5|-
     * 2|10.06.2023|ulkokuntosali|50|1|jatkossa juotavaa
     * 3|12.06.2023|kotikuntosali|70|3|lisää painoja
     * </pre>
     * @param hakemisto tallennettavan tiedoston hakemisto
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta(String hakemisto) throws SailoException {
        if (!this.onkoMuutettu)
            return;
        File tiedosto = new File(hakemisto + "/treenit.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedosto, false))) {
            for (int i=0; i < getLkm(); i++) {
                Treeni treeni = anna(i);
                fo.println(treeni.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + tiedosto.getAbsolutePath() + " ei aukea");
        }
        this.onkoMuutettu = false;
    }
    
    
    /**
     * Tallentaa treenit tiedostoon
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        talleta(this.tiedostoNimi);
    }
    
    
    /**
     * Palautetaan treeni iteraattori.
     * @return treeni iteraattori
     */
    @Override
    public Iterator<Treeni> iterator() {
        return new TreenitIterator();
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien treenien viitteet 
     * @param hakuehto hakuehto 
     * @param indeksi etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä treeneistä 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     *   Treenit treenit = new Treenit(); 
     *   Treeni treeni1 = new Treeni(); treeni1.parse("1|09.06.2023|kotikuntosali|60|5|-"); 
     *   Treeni treeni2 = new Treeni(); treeni2.parse("2|10.06.2023|ulkokuntosali|50|1|jatkossa juotavaa"); 
     *   Treeni treeni3 = new Treeni(); treeni3.parse("3|12.06.2023|kotikuntosali|70|3|lisää painoja"); 
     *   treenit.lisaa(treeni1); treenit.lisaa(treeni2); treenit.lisaa(treeni3);
     *   // TODO: toistaiseksi palauttaa kaikki treenit 
     * </pre> 
     */ 
    @SuppressWarnings("unused")
    public Collection<Treeni> etsi(String hakuehto, int indeksi) { 
        Collection<Treeni> loytyneet = new ArrayList<Treeni>(); 
        for (Treeni treeni : this) { 
            loytyneet.add(treeni);  
        } 
        return loytyneet; 
    }
    
    
    /**
     * Luokka treenien iteroimiseksi.
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #PACKAGEIMPORT
     * #import java.util.*;
     *   Treenit treenit = new Treenit();
     *   Treeni tre1 = new Treeni(), tre2 = new Treeni();
     *   tre1.rekisteroi(); tre2.rekisteroi();
     *   treenit.lisaa(tre1); 
     *   treenit.lisaa(tre2); 
     *   treenit.lisaa(tre1); 
     *   StringBuffer ids = new StringBuffer(30);
     *   for (Treeni treeni:treenit) {   // Kokeillaan for-silmukan toimintaa
     *     ids.append(" "+treeni.getTunnusNro());
     *   }         
     *   String tulos = " " + tre1.getTunnusNro() + " " + tre2.getTunnusNro() + " " + tre1.getTunnusNro();
     *   ids.toString() === tulos; 
     *   ids = new StringBuffer(30);
     *   for (Iterator<Treeni>  i=treenit.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *     Treeni treeni = i.next();
     *     ids.append(" "+treeni.getTunnusNro());           
     *   }
     *   ids.toString() === tulos;
     *   Iterator<Treeni>  i=treenit.iterator();
     *   i.next() == tre1  === true;
     *   i.next() == tre2  === true;
     *   i.next() == tre1  === true;
     *   i.next();  #THROWS NoSuchElementException
     * </pre>
     */
    public class TreenitIterator implements Iterator<Treeni> {
        private int kohdalla = 0;
        
        
        /**
         * Onko olemassa vielä seuraavaa treeniä
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä treenejä
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }
        
        
        /**
         * Annetaan seuraava treeni
         * @return seuraava treeni
         * @throws NoSuchElementException jos seuraava alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Treeni next() throws NoSuchElementException {
            if ( !hasNext() ) throw new NoSuchElementException("Ei ole seuraavaa");
            return anna(kohdalla++);
        }
        
        
        /**
         * Tuhoamista ei ole toteutettu
         * @throws UnsupportedOperationException aina
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Poistamista ei ole toteutettu");
        }
    }
    
    
    /**
     * Testiohjelma treeneille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Treenit treenit = new Treenit();
        // Lue Treenit
        try {
            treenit.lueTiedostosta("aku");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        // Luo Treenit
        Treeni tre1 = new Treeni();
        Treeni tre2 = new Treeni();
        tre1.rekisteroi();
        tre1.taytaTreeni();
        tre2.rekisteroi();
        tre2.taytaTreeni();
        // Lisää Treenit
        try {
            treenit.lisaa(tre1);
            treenit.lisaa(tre2);
        } catch (IndexOutOfBoundsException ex) {
            System.err.println(ex.getMessage());
        }
        // Tulosta Treenit
        System.out.println("============= Treenit testi =================");
        for (int i = 0; i < treenit.getLkm(); i++) {
            Treeni treeni = treenit.anna(i);
            System.out.println("Treeni nro: " + i);
            treeni.tulosta(System.out);
        }
        // Tallenna Treenit
        try {
            treenit.talleta("aku");
        }  catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
