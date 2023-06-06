package punttisalimuistio;

/**
 * |-------------------------------------------------------------------------|
 * | Luokan nimi:   Punttisalimuistio                   | Avustajat:         |
 * |--------------------------------------------------------------------------
 * | Vastuualueet:                                      |                    | 
 * |                                                    | - Treenit          | 
 * | - huolehtii Treenit ja Liikkeet -luokkien          | - Treeni           | 
 * |   välisestä yhteistyöstä ja välittää näitä         | - Liikkeet         | 
 * |   tietoja pyydettäessä                             | - Liike            | 
 * | - lukee ja kirjoittaa muistion tiedostoihin        |                    |
 * |   pyytämällä apua avustajiltaan                    |                    |
 * |                                                    |                    |
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |                                                    |                    | 
 * |--------------------------------------------------------------------------
 * Punttisalimuistio-luokka, joka huolehtii treeneistä.
 * Pääosin kaikki metodit ovat vain "välittäjämetodeja" treeneihin.
 * @author Eetu
 * @version 03.06.2023
 */
public class Punttisalimuistio {
    private final Treenit treenit = new Treenit();
    
    
    /**
     * Palauttaa punttisalimuistioon kirjattujen treenien määrän
     * @return treenien määrä
     */
    public int getTreeneja() {
        return treenit.getLkm();
    }
    
    
    /**
     * Palauttaa i:n treenin
     * @param i monesko treeni palautetaan
     * @return viite i:teen treeniin
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Treeni annaTreeni(int i) throws IndexOutOfBoundsException {
        return treenit.anna(i);
    }
    
    
    /**
     * Lisää punttisalimuistioon uuden jäsenen
     * @param treeni lisättävä jäsen
     * @throws SailoException jos lisäystä ei voida tehdä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Punttisalimuistio punttisalimuistio = new Punttisalimuistio();
     * Treeni tre1 = new Treeni(), tre2 = new Treeni();
     * tre1.rekisteroi(); tre2.rekisteroi();
     * punttisalimuistio.getTreeneja() === 0;
     * punttisalimuistio.lisaa(tre1); punttisalimuistio.getTreeneja() === 1;
     * punttisalimuistio.lisaa(tre2); punttisalimuistio.getTreeneja() === 2;
     * punttisalimuistio.lisaa(tre1); punttisalimuistio.getTreeneja() === 3;
     * punttisalimuistio.getTreeneja() === 3;
     * punttisalimuistio.annaTreeni(0) === tre1;
     * punttisalimuistio.annaTreeni(1) === tre2;
     * punttisalimuistio.annaTreeni(2) === tre1;
     * punttisalimuistio.annaTreeni(3) === tre1; #THROWS IndexOutOfBoundsException 
     * punttisalimuistio.lisaa(tre1); punttisalimuistio.getTreeneja() === 4;
     * punttisalimuistio.lisaa(tre1); punttisalimuistio.getTreeneja() === 5;
     * punttisalimuistio.lisaa(tre1); punttisalimuistio.lisaa(tre2);
     * punttisalimuistio.lisaa(tre1); punttisalimuistio.lisaa(tre2);
     * punttisalimuistio.lisaa(tre1); punttisalimuistio.lisaa(tre2);
     * punttisalimuistio.lisaa(tre1);
     * punttisalimuistio.lisaa(tre1);            #THROWS SailoException
     * </pre>
     */
    public void lisaa(Treeni treeni) throws SailoException {
        treenit.lisaa(treeni);
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
     * @param nimi jota käyteään lukemisessa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        treenit.lueTiedostosta(nimi);
    }


    /**
     * Tallettaa punttisalimuistion tiedot tiedostoon
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void talleta() throws SailoException {
        treenit.talleta();
        // TODO: yritä tallettaa toinen vaikka toinen epäonnistuisi
    }
    
    
    /**
     * Testiohjelma punttisalimuistiolle.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Punttisalimuistio punttisalimuistio = new Punttisalimuistio();
        try {
            // punttisalimuistio.lueTiedostosta("aku");
            Treeni tre1 = new Treeni(), tre2 = new Treeni();
            tre1.rekisteroi();
            tre1.vastaaTreeni();
            tre2.rekisteroi();
            tre2.vastaaTreeni();
            punttisalimuistio.lisaa(tre1);
            punttisalimuistio.lisaa(tre2);
            System.out.println("============= Punttisalimuistion testi =================");
            for (int i = 0; i < punttisalimuistio.getTreeneja(); i++) {
                Treeni treeni = punttisalimuistio.annaTreeni(i);
                System.out.println("Treeni paikassa: " + i);
                treeni.tulosta(System.out);
            }
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
