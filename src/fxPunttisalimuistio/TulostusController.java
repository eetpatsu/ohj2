package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;

/**
 * Tulostuksen hoitava luokka
 * @author eetpatsu@student.jyu.fi
 * @version 0.3, 14.02.2023 Tiedoston synty
 * @version 0.7.6, 27.06.2023 Tulostaminen toimii
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
        PrinterJob job = PrinterJob.createPrinterJob();
        if ( job != null && job.showPrintDialog(null) ) {
            WebEngine webEngine = new WebEngine();
            webEngine.loadContent("<pre>" + tulostusAlue.getText() + "</pre>");
            webEngine.print(job);
            job.endJob();
        }
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
        tulostusAlue.requestFocus();
    }
    
    
    /**
     * Oletusarvo
     */
    @Override
    public void setDefault(String oletus) {
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


