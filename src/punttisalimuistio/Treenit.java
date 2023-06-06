package punttisalimuistio;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Treenit                             | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | - Treeni          | 
 * | - pitää yllä varsinaista treenirekisteriä, eli     |                   | 
 * |   osaa lisätä ja poistaa treenin                   |                   | 
 * | - lukee ja kirjoittaa treenit.dat -tiedostoon      |                   | 
 * | - osaa etsiä ja lajitella                          |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |-------------------------------------------------------------------------
 * Punttisalimuistion Treenit-luokka
 * @author Eetu
 * @version 03.06.2023 Tiedoston synty
 */
public class Treenit {
    private static final int MAX_TREENEJA   = 12;
    private int              lkm            = 0;
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
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     *   Treenit treenit = new Treenit();
     *   Treeni tre1 = new Treeni();
     *   Treeni tre2 = new Treeni();
     *   
     *   treenit.getLkm() === 0;
     *   treenit.lisaa(tre1); treenit.getLkm() === 1;
     *   treenit.lisaa(tre2); treenit.getLkm() === 2;
     *   treenit.lisaa(tre1); treenit.getLkm() === 3;
     *   
     *   treenit.anna(0) === tre1;
     *   treenit.anna(1) === tre2;
     *   treenit.anna(2) === tre1;
     *   treenit.anna(1) == tre1 === false;
     *   treenit.anna(1) == tre2 === true;
     *   treenit.anna(3) === tre1; #THROWS IndexOutOfBoundsException 
     *   
     *   treenit.lisaa(tre1); treenit.getLkm() === 4;
     *   treenit.lisaa(tre1); treenit.getLkm() === 5;
     *   
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1); treenit.lisaa(tre2);
     *   treenit.lisaa(tre1);
     *   treenit.getLkm() === 12;
     *   treenit.lisaa(tre2);  #THROWS SailoException
     *   treenit.getLkm() === 12;
     * </pre>
     */
    public void lisaa(Treeni treeni) throws SailoException {
        if (this.lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[this.lkm] = treeni;
        this.lkm++;
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
     *   
     *   treenit.getLkm() === 0;
     *   treenit.lisaa(tre1); treenit.getLkm() === 1;
     *   treenit.lisaa(tre2); treenit.getLkm() === 2;
     *   treenit.lisaa(tre1); treenit.getLkm() === 3;
     *   
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
     * Lukee treenit tiedostosta.  Kesken.
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostoNimi = hakemisto + "/treenit.dat";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostoNimi);
    }


    /**
     * Tallentaa treenit tiedostoon.  Kesken.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostoNimi);
    }
    
    
    /**
     * Testiohjelma treeneille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Treenit treenit = new Treenit();
        Treeni tre = new Treeni(), tre2 = new Treeni();
        tre.rekisteroi();
        tre.taytaTreeni();
        tre2.rekisteroi();
        tre2.taytaTreeni();
        try {
            treenit.lisaa(tre);
            treenit.lisaa(tre2);
            System.out.println("============= Treenit testi =================");
            for (int i = 0; i < treenit.getLkm(); i++) {
                Treeni treeni = treenit.anna(i);
                System.out.println("Treeni nro: " + i);
                treeni.tulosta(System.out);
            }
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
