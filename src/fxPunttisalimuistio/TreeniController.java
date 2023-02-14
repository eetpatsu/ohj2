package fxPunttisalimuistio;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Luokka muistion treenien lisäämiseen ja muokkamiseen
 * @author Eetu
 * @version 14 Feb 2023
 */
public class TreeniController implements ModalControllerInterface<String> {
    
    @FXML private TextField textHakuehto;
    @FXML private TextField textUusiliike;
    @FXML private Label labelVirhe;
    
    /**
     * Päiväyksien-hakukenttä
     */
    @FXML private void handleHakuehto() {
        String ehto = textHakuehto.getText();
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + ehto);
    }
    
    /**
     * Lisää-painike
     */
    @FXML private void handleLisaaUusiLiike() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä liikettä");
    }
    
    
    /**
     * OK-painike
     */
    @FXML private void handleOK() {
        ModalController.closeStage(textHakuehto);
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML private void handlePeruuta() {
        ModalController.closeStage(textHakuehto);
    }
    
    
    @FXML private void handleUusiLiike() {
        //
    }
    
    
    /**
     * Mitä palautetaan
     */
    @Override
    public String getResult() {
        return null;
    }
    
    
    /**
     * Oletusarvo
     */
    @Override
    public void setDefault(String oletus) {
        if ( oletus == null ) return;
        textHakuehto.setText(oletus);
    }
    
    
    /**
     * Mihin keskitytään oletuksena
     */
    @Override
    public void handleShown() {
        // 
    }
    
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
    
    
    /**
     * Virhepalkin toiminta
     * @param virhe Selitys virheestä
     */
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    /**
     * Luodaan nimenkysymisdialogi ja palautetaan siihen kirjoitettu nimi tai null
     * @param treeni Treenikerta
     */
    public static void treeni(String treeni) {
        ModalController.showModal(TulostusController.class.getResource("TreeniView.fxml"), treeni, null, null);
    }
}

