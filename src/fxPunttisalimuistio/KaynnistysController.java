package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Luokka muistion käynnistysikkunaa varten
 * @author Eetu
 * @version 0.3, 14.2.2023 Tiedoston synty
 * @version 0.4, 02.06.2023 Uudistuksia
 */
public class KaynnistysController implements ModalControllerInterface<String>  {
    
    private String vastaus = null;
    @FXML private TextField textVastaus;
    
    
    /**
     * OK-painike
     */
    @FXML void handleOK() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML void handlePeruuta() {
        ModalController.closeStage(textVastaus);
    }
    
    
    /**
     * Tiedon palautus
     */
    @Override
    public String getResult() {
        return vastaus;
    }
    
    
    /**
     * Oletuskeskitys
     */
    @Override
    public void handleShown() {
        textVastaus.requestFocus();    
    }
    
    
    /**
     * Oletusteksti
     */
    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    /**
     * Luodaan käyttäjänkysymisdialogi ja palautetaan siihen kirjoitettu käyttäjänimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä käyttäjänimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyKayttaja(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                KaynnistysController.class.getResource("KaynnistysView.fxml"),
                "Punttisalimuistio", modalityStage, oletus);
    }
}