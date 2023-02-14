package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Luokka muistion käynnistys- ja kirjautumisikkunaa varten
 * @author Eetu
 * @version 14 Feb 2023
 */
public class KaynnistysController implements ModalControllerInterface<String> {
    
    @FXML private ComboBoxChooser<String> cbKayttajat;
    private String vastaus = null;
    
    
    /**
     * OK-painike
     */
    @FXML private void handleOK() {
        vastaus = cbKayttajat.getRivit();
        ModalController.closeStage(cbKayttajat);
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML private void handlePeruuta() {
        ModalController.closeStage(cbKayttajat);
    }
    
    
    /**
     * Käyttäjälista
     */
    @FXML private void handleKayttaja() {
        // Dialogs.showMessageDialog("Ei osata vielä kirjautua käyttäjälle " + vastaus);
    }
    
    
    /**
     * Uusi käyttäjä -painike
     */
    @FXML private void handleUusiKayttaja() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("KayttajaDialogView.fxml"), "Käyttäjä", null, "");
    }
    
    
    /**
     * Mitä palautetaan
     */
    @Override
    public String getResult() {
        return vastaus;
    }
    
    
    /**
     * Oletusarvo käyttäjälistaan
     */
    @Override
    public void setDefault(String oletus) {
        cbKayttajat.setRivit(oletus);
    }
    
    
    /**
     * Mihin keskitytään oletuksena
     */
    @Override
    public void handleShown() {
        cbKayttajat.requestFocus();
    }
    
    
    /**
     * Luodaan käyttäjänkysymisdialogi ja palautetaan käyttäjä tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyKayttaja(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                KaynnistysController.class.getResource("KaynnistysView.fxml"),
                "Punttisalimuistio",
                modalityStage, oletus);
    }
}