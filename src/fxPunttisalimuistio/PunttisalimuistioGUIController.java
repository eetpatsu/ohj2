package fxPunttisalimuistio;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import punttisalimuistio.Liike;
import punttisalimuistio.Punttisalimuistio;
import punttisalimuistio.SailoException;
import punttisalimuistio.Treeni;

/**
 * Avustajat: Punttisalimuistio, Treeni, Liike
 * Vastuualueet: Hoitaa kaiken näyttöön tulevan tekstin
 * ja kaiken tiedon pyytämisen käyttäjältä.
 * Ei tiedä punttisalimuistion yksityiskohtia.
 * 
 * Luokka muistion pääkäyttöliittymän tapahtumien hoitamiseen.
 * @author eetpatsu@student.jyu.fi
 * @version 0.3, 14.02.2023 Tiedoston synty
 * @version 0.4, 30.05.2023 Uudistuksia
 * @version 0.5, 09.06.2023 Treenit ja Liikkeet
 * @version 0.6, 14.06.2023 Tiedostonhallinta
 * @version 0.7.1, 22.06.2023 Treenin näyttäminen tyhmästi
 * @version 0.7.3, 22.06.2023 Liikkeiden näyttäminen, treeni fiksusti
 * @version 0.7.4, 25.06.2023 Tiedonsyöttö treeniin, tallentaminen
 * @version 0.7.5, 26.06.2023 Hakeminen, Tiedonsyöttö liikkeeseen
 */
public class PunttisalimuistioGUIController implements Initializable {    
    @FXML private ComboBoxChooser<String> cbKentat;         // Hakuehto-valikko
    @FXML private TextField hakuehto;                       // Hakuehto-kenttä
    @FXML private TextField editPvm;
    @FXML private TextField editSijainti;
    @FXML private TextField editKesto;
    @FXML private TextField editFiilikset;
    @FXML private TextField editMuistiinpanot;
    @FXML private Label labelVirhe;                         // Virheilmoitus-kenttä
    @FXML private ScrollPane panelTreeni;                   // Treenin tiedot -paneeli
    @FXML private ListChooser<Treeni> chooserTreenit;       // Treenit-lista
    @FXML private StringGrid<Liike> tableLiikkeet;
    
    
    /**
     * Kontrollerin alustus
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta(); 
    }
    
    
    /**
     * Hakuehto-valikko
     */
    @FXML private void handleHakuehto() {
        hae(0);
    }
    
//===========================================================================================    
// Tiedosto-menu
    
    /**
     * Tallenna-painike
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Avaa-painike
     */
    @FXML private void handleAvaa() {
        avaa();
    }
    
    
    /**
     * Tulosta-painike
     */
    @FXML private void handleTulosta() {
        TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        tulostaValitut(tulostusCtrl.getTextArea());
    }
    
    
    /**
     * Lopeta-painike
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
//===========================================================================================    
// Muokkaa-menu
    
    /**
     * Uusi treeni -painike
     */
    @FXML private void handleUusiTreeni() {
        uusiTreeni();
    }
    
    
    /**
     * Muokkaa treeniä -painike
     */
    @FXML private void handleMuokkaaTreeni() {
        muokkaaTreeni();
    }
    
    
    /**
     * Poista treeni -painike
     */
    @FXML private void handlePoistaTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa treeniä");
    }
    
    
    /**
     * Lisää liike -painike
     */
    @FXML private void handleUusiLiike() {
        uusiLiike();
    }
    
    
    /**
     * Muokkaa liikettä -painike
     */
    @FXML private void handleMuokkaaLiike() {
        muokkaaLiike();
    }
    
    
    /**
     * Poista liike -painike
     */
    @FXML private void handlePoistaLiike() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa liikettä");
    }
    
//===========================================================================================    
// Apua-menu
    
    /**
     * Apua-painike
     */
    @FXML private void handleApua() {
        avustus();
    }
    
    
    /**
     * Tietoa-painike
     */
    @FXML private void handleTietoa() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TietoaView.fxml"), "Punttisalimuistio", null, "");
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
//=========================================================================================== 
    
    private String              kayttaja = "aku";
    private Punttisalimuistio   muistio;                        // Tynkä Punttisalimuistio-olioviite
    private TextField[]         kentat;
    private static Treeni       apuTreeni = new Treeni();
    
    
    /**
     * Tekee tarvittavat muut alustukset
     * Alustetaan myös treenilistan kuuntelija 
     */
    private void alusta() {
        kentat = new TextField[] {editPvm, editSijainti, editKesto, editFiilikset, editMuistiinpanot};
        chooserTreenit.clear();
        cbKentat.clear();
        for (int i = 0; i < apuTreeni.getKenttaLkm(); i++) {
            cbKentat.add(apuTreeni.getKysymys(i));
        }
        cbKentat.setSelectedIndex(0);
        chooserTreenit.addSelectionListener(e -> naytaTreeni());
    }
    
    
    /**
     * Aliohjelma ilmestyneen virheilmoituksen näyttämiseen käyttöliittymässä
     * @param virhe virheilmoitus
     */
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    /**
     * Aseta ohjelmaikkunalle nimi
     * @param title asetettava nimi
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * Alustaa punttisalimuisiton lukemalla sen valitun nimisestä hakemistosta
     * @param nimi hakemisto josta käyttajän tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        kayttaja = nimi;
        setTitle("Punttisalimuistio - " + kayttaja);
        try {
            muistio.lueTiedostosta(nimi);
            hae(0);
            return null;
        } catch (SailoException ex) {
            hae(0);
            if (ex.getMessage() != null)
                Dialogs.showMessageDialog(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    
    /**
     * Kysytään hakemiston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusikayttaja = KaynnistysController.kysyKayttaja(null, kayttaja);
        if (uusikayttaja == null) return false;
        lueTiedosto(uusikayttaja);
        return true;
    }
    
    
    /**
     * Tietojen tallennustoiminto
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            muistio.talleta();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }
    
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    
    /**
     * Näyttää listasta valitun treenin tiedot
     */
    private void naytaTreeni() {
        Treeni treeniKohdalla = chooserTreenit.getSelectedObject();
        if (treeniKohdalla == null) {
            return;
        }
        TreeniDialogController.naytaTreeni(kentat, treeniKohdalla);
        naytaLiikkeet(treeniKohdalla);
    }
    
    
    /**
     * Näyttää listasta valitun treenin liikkeet taulukossa
     * @param treeni
     */
    private void naytaLiikkeet(Treeni treeni) {
        if (treeni == null)
            return;
        tableLiikkeet.clear();
        try {
            List<Liike> liikkeet = muistio.annaLiikkeet(treeni);
            if (liikkeet.size() == 0)
                return;
            for (Liike lii : liikkeet)
                naytaLiike(lii);
        } catch (SailoException ex) {
            naytaVirhe(ex.getMessage());
        }
    }
    
    
    /**
     * Asettaa yhden liikkeen tiedot taulukkoon
     * @param liike jonka tiedot asetetaan
     */
    private void naytaLiike(Liike liike) {
        String[] rivi = liike.toString().split("\\|");
        tableLiikkeet.add(liike, rivi[2], rivi[3], rivi[4], rivi[5]);
    }
    
    
    /**
     * Hakee treenien tiedot listaan
     * @param nro treeni joka valitaan haun jälkeen, 0 = haetaan nykyinen treeni
     */
    private void hae(int nro) {
        int treNro = nro;
        if (treNro == 0) {
            Treeni treeniKohdalla = chooserTreenit.getSelectedObject();
            if (treeniKohdalla != null)
                treNro = treeniKohdalla.getTunnusNro();
        }
        int kenttaNro = cbKentat.getSelectionModel().getSelectedIndex();
        
        chooserTreenit.clear();
        String ehto = hakuehto.getText();
        if (ehto.indexOf('*') < 0)
            ehto = "*" + ehto + "*";
        Collection<Treeni> treenit = muistio.etsi(ehto, kenttaNro);
        int indeksi = 0;
        int chooserIndeksi = 0;
        for (Treeni treeni : treenit) {
            if (treeni.getTunnusNro() == treNro)
                indeksi = chooserIndeksi;
            chooserTreenit.add("" + treeni.getPvm(), treeni);
            chooserIndeksi++;
        }
        chooserTreenit.setSelectedIndex(indeksi);
    }
    
    
    /**
     * Luo uuden treenin jota aletaan editoimaan 
     */
    private void uusiTreeni() {
        Treeni uusi = new Treeni();
        uusi = TreeniDialogController.kysyTreeni(null, uusi);
        if (uusi == null)
            return;
        uusi.rekisteroi();
        muistio.lisaa(uusi);
        hae(uusi.getTunnusNro());
    }
    
    
    /**
     * Tekee uuden tyhjän liikkeen editointia varten
     */
    public void uusiLiike() {
        Treeni treeniKohdalla = chooserTreenit.getSelectedObject();
        if (treeniKohdalla == null)
            return;
        Liike uusi = new Liike(treeniKohdalla.getTunnusNro());
        uusi = LiikeDialogController.kysyLiike(null, uusi);
        if (uusi == null)
            return;
        uusi.rekisteroi();
        try {
            muistio.lisaa(uusi);
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Ongelmia lisäämisessä! " + ex.getMessage());
        }
        naytaLiikkeet(treeniKohdalla);
        tableLiikkeet.selectRow(1000);
    }
    
    
    /**
     * Avaa valitun treenin muokkausdialogissa
     */
    private void muokkaaTreeni() {
        Treeni treeniKohdalla = chooserTreenit.getSelectedObject();
        if (treeniKohdalla == null)
            return;
        try {
            treeniKohdalla = treeniKohdalla.clone();
        } catch (CloneNotSupportedException ex) {
            Dialogs.showMessageDialog(ex.getMessage()); 
        }
        if (TreeniDialogController.kysyTreeni(null, treeniKohdalla) == null)
            return;
        muistio.korvaaTaiLisaa(treeniKohdalla);
        hae(treeniKohdalla.getTunnusNro());
    }
    
    
    /**
     * Avaa valitun liikkeen muokkausdialogissa
     */
    private void muokkaaLiike() {
        Liike liikeKohdalla = tableLiikkeet.getObject();
        if (liikeKohdalla == null)
            return;
        try {
            liikeKohdalla = liikeKohdalla.clone();
        } catch (CloneNotSupportedException ex) {
            Dialogs.showMessageDialog(ex.getMessage()); 
        }
        if (LiikeDialogController.kysyLiike(null, liikeKohdalla) == null)
            return;
        muistio.korvaaTaiLisaa(liikeKohdalla);
    }
    
    
    /**
     * Aseta punttisalimuistio.
     * @param punttisalimuistio Punttisalimuistio jota käytetään tässä käyttöliittymässä
     */
    public void setMuistio(Punttisalimuistio punttisalimuistio) {
        muistio = punttisalimuistio;
        naytaTreeni();
    }
    
    
    /**
     * Tulostaa treenin tiedot
     * @param os tietovirta johon tulostetaan
     * @param treeni tulostettava jäsen
     */
    public void tulosta(PrintStream os, final Treeni treeni) {
        os.println("----------------------------------------------");
        treeni.tulosta(os);
        os.println("----------------------------------------------");
        try {
            List<Liike> liikkeet = muistio.annaLiikkeet(treeni);
            for (Liike lii : liikkeet)
                lii.tulosta(os);
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Liikkeen hakemisessa ongelmia! " + ex.getMessage());
        }
    }
    
    
    /**
     * Tulostaa listassa olevat treenit tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki treenit");
            Collection<Treeni> treenit = muistio.etsi("", -1); 
            for (Treeni treeni : treenit) { 
                tulosta(os, treeni);
                os.println("\n\n");
            }
        }
    }
    
    
    /**
     * Näytetään ohjelman dokumentaatio erillisessä selaimessa.
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2023k/ht/eetpatsu");
            desktop.browse(uri);
        } catch (URISyntaxException ex) {
            return;
        } catch (IOException ex) {
            return;
        }
    }
}