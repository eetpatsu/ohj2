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
 */
public class TreeniDialogController implements ModalControllerInterface<Treeni>, Initializable {
    @FXML private Label labelVirhe;
    @FXML private TextField editPvm;
    
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
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }
    
    
    @Override
    public Treeni getResult() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }
    
    
    @Override
    public void setDefault(Treeni oletus) {
        treeniKohdalla = oletus;
        naytaTreeni(treeniKohdalla);
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Treeni treeniKohdalla;
    
    
    /**
     * Näytetään annettu treeni dialogissa
     * @param treeni joka näytetään
     */
    private void naytaTreeni(Treeni treeni) {
        if (treeni == null)
            return;
        editPvm.setText(treeni.getPvm());
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