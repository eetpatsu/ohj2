package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Tulostuksen hoitava luokka
 * @author Eetu
 * @version 14 Feb 2023
 */
public class TulostusController implements ModalControllerInterface<String> {
    
    @FXML private TextArea tulostusAlue;
    
    
    /**
     * OK-painike
     */
    @FXML void handleOK() {
        ModalController.closeStage(tulostusAlue);
    }
    
    
    /**
     * Tulosta-painike
     */
    @FXML void handleTulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
    
    
    /**
     * Mitä palautetaan
     */
    @Override
    public String getResult() {
        return null;
    }
    
    
    /**
     * Mihin keskitytään oletuksena
     */
    @Override
    public void handleShown() {
        // TODO
    }
    
    
    /**
     * Oletusarvo
     */
    @Override
    public void setDefault(String oletus) {
        if ( oletus == null ) return;
        tulostusAlue.setText(oletus);
    }
    
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostus tulostettava teskti
     */
    public static void tulosta(String tulostus) {
        ModalController.showModeless(TulostusController.class.getResource("TulostusView.fxml"),
                "Tulostus", tulostus);
    }
}


