package fxPunttisalimuistio;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import punttisalimuistio.Treeni;

/**
 * Treenille oma kontrolleri
 * @author Eetu
 * @version 0.7.1, 22.06.2023 Tiedoston synty
 * @version 0.7.3, 22.06.2023 Treenin näyttäminen fiksusti
 * @version 0.7.4, 23.06.2023 Tiedon syöttäminen
 */
public class TreeniDialogController implements ModalControllerInterface<Treeni>, Initializable {
    @FXML private Label labelVirhe;
    @FXML private TextField editPvm;
    @FXML private TextField editSijainti;
    @FXML private TextField editKesto;
    @FXML private TextField editFiilikset;
    @FXML private TextField editMuistiinpanot;
    
    /**
     * OK-painike
     */
    @FXML private void handleOK() {
        if (treeniKohdalla != null && treeniKohdalla.getPvm().equals("")) {
            naytaVirhe("Pvm ei voi olla tyhjä");
            return;
        }
        ModalController.closeStage(editPvm);
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML private void handlePeruuta() {
        treeniKohdalla = null;
        ModalController.closeStage(editPvm);
    }
    
    
    /**
     * Kontrollerin alustus
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    /**
     * Tiedon palautus
     */
    @Override
    public Treeni getResult() {
        return treeniKohdalla;
    }
    
    
    /**
     * Oletuskeskitys
     */
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }
    
    
    /**
     * Aseta oletustreeni
     */
    @Override
    public void setDefault(Treeni oletus) {
        treeniKohdalla = oletus;
        naytaTreeni(kenttia, treeniKohdalla);
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Treeni treeniKohdalla;
    private TextField[] kenttia;
    
    
    /**
     * Tekee tarvittavat alustukset
     */
    private void alusta() {
        kenttia = new TextField[] {editPvm, editSijainti, editKesto, editFiilikset, editMuistiinpanot};
        editPvm.setOnKeyReleased(e -> handleMuutos(editPvm));
    }
    
    
    /**
     * Käsitellään muutos treenin kentässä
     * @param kentta jossa muutos
     */
    private void handleMuutos(TextField kentta) {
        if (treeniKohdalla == null)
            return;
        String syote = kentta.getText();
        String virhe = null;
        virhe = treeniKohdalla.setPvm(syote);
        naytaVirhe(virhe);
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
     * Näytetään annettu treeni dialogissa
     * Täytetään treenin kentät
     * @param kentat TextField jossa täytettävät kentät
     * @param treeni joka näytetään
     */
    public static void naytaTreeni(TextField[] kentat, Treeni treeni) {
        if (treeni == null)
            return;
        kentat[0].setText(treeni.getPvm());
        kentat[1].setText(treeni.getSijainti());
        kentat[2].setText(treeni.getKesto());
        kentat[3].setText(treeni.getFiilikset());
        kentat[4].setText(treeni.getMuistiinpanot());
    }
    
    
    /**
     * Luodaan treenin kysymisdialogi ja palautetaan sama tietue muutettuna tai null.
     * @param modalityStage mille ollaan modaalisia, null = ollaan modaalisia sovellukselle.
     * @param oletus minkä tietoa näytetään oletuksena.
     * @return null jos peruutetaan, muuten täytetty tietue.
     */
    public static Treeni kysyTreeni(Stage modalityStage, Treeni oletus) {
        return ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TreeniDialogView.fxml"), "Treeni", modalityStage, oletus);
    }
}