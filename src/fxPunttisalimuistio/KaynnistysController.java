package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Luokka muistion käynnistysikkunaa varten
 * @author eetpatsu@student.jyu.fi
 * @version 0.3, 14.2.2023 Tiedoston synty
 * @version 0.4, 02.06.2023 Uudistuksia
 * @version 0.6, 14.06.2023 Tiedostonhallinta
 */
public class KaynnistysController implements ModalControllerInterface<String>  {
    @FXML private TextField textVastaus;
    private String vastaus = "";
    
    
    /**
     * OK-painike
     */
    @FXML private void handleOK() {
        vastaus = textVastaus.getText();
        if (vastaus.length() == 0) {
            Dialogs.showMessageDialog("Et kirjoittanut käyttäjää");
            return;
        }
        ModalController.closeStage(textVastaus);
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML private void handlePeruuta() {
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
        textVastaus.clear();
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