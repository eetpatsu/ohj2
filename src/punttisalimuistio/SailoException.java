package punttisalimuistio;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille.
 * @author Vesa Lappalainen
 * @version 1.0, 22.02.2003
 * @author eetpatsu@student.jyu.fi
 * @version 1.0  03.06.2023 Tiedoston synty
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;


    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}