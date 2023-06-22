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
 * @version 0.7.2, 22.06.2023 Treenin näyttäminen fiksusti
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
        //
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML private void handlePeruuta() {
        //
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
        // TODO Auto-generated method stub
        return null;
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
        naytaTreeni(edits, treeniKohdalla);
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Treeni treeniKohdalla;
    private TextField[] edits;
    
    
    /**
     * Tekee tarvittavat alustukset
     */
    private void alusta() {
        edits = new TextField[] {editPvm, editSijainti, editKesto, editFiilikset, editMuistiinpanot};
    }
    
    
    /**
     * Näytetään annettu treeni dialogissa
     * Täytetään treenin kentät
     * @param edit TextField jossa täytettävät kentät
     * @param treeni joka näytetään
     */
    public static void naytaTreeni(TextField[] edit, Treeni treeni) {
        if (treeni == null)
            return;
        edit[0].setText(treeni.getPvm());
        edit[1].setText(treeni.getSijainti());
        edit[2].setText(treeni.getKesto());
        edit[3].setText(treeni.getFiilikset());
        edit[4].setText(treeni.getMuistiinpanot());
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