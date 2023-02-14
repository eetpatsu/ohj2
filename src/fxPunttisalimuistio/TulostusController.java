package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Tulostuksen hoitava luokka
 * @author Eetu
 * @version 9 Feb 2023
 */
public class TulostusController implements ModalControllerInterface<String> {
    
    @FXML private TextArea tulostusAlue;
    
    
    @FXML void handleOK() {
        ModalController.closeStage(tulostusAlue);
    }
    
    
    @FXML void handleTulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }


    @Override
    public String getResult() {
        return null;
    }


    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }


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


