package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Kysytään mille käyttäjälle kirjaudutaan.
 * @author Eetu
 * @version 8 Feb 2023
 */
public class KaynnistysController implements ModalControllerInterface<String> {
    
    @FXML private ComboBoxChooser<String> cbKayttajat;
    
    
    @FXML private void handleKayttaja() {
        String valinta = cbKayttajat.getSelectedText();
        Dialogs.showMessageDialog("Ei osata vielä kirjautua käyttäjälle " + valinta);
    }
    
    
    @FXML private void handleOK() {
        ModalController.closeStage(cbKayttajat);
    }
    
    
    @FXML private void handleUusiKayttaja() {
        Dialogs.showMessageDialog("Vielä ei osata lisätä käyttäjää");
    }
    
    
    @Override
    public String getResult() {
        return cbKayttajat.getSelectedText();
    }


    @Override
    public void handleShown() {
        cbKayttajat.requestFocus();
    }


    @Override
    public void setDefault(String oletus) {
        cbKayttajat.setPromptText(oletus);
    }
    
    
    /**
     * Luodaan käyttäjämkysymisdialogi ja palautetaan käyttäjä tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                KaynnistysController.class.getResource("KaynnistysView.fxml"),
                "Punttisalimuistio",
                modalityStage, oletus);
    }
}