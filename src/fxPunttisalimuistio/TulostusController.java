package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Tulostuksen hoitava luokka
 * @author Eetu
 * @version 0.3, 14.02.2023 Tiedoston synty
 */
public class TulostusController implements ModalControllerInterface<String> {
    
    @FXML TextArea tulostusAlue;
    
    
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
        // if ( oletus == null ) return;
        tulostusAlue.setText(oletus);
    }
    
    
    /**
     * @return alue johon tulostetaan
     */
    public TextArea getTextArea() {
        return tulostusAlue;
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostus tulostettava teskti
     * @return kontrolleri, jolta voidaan pyytää lisää tietoa
     */
    public static TulostusController tulosta(String tulostus) {
        TulostusController tulostusCtrl =
        ModalController.showModeless(TulostusController.class.getResource("TulostusView.fxml"),
                "Tulostus", tulostus);
        return tulostusCtrl;
    }
}


