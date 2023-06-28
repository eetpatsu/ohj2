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
import punttisalimuistio.Liike;

/**
 * Liikkeelle oma kontrolleri
 * @author eetpatsu@student.jyu.fi
 * @version 0.7.5, 26.06.2023 Tiedoston synty
 */
public class LiikeDialogController implements ModalControllerInterface<Liike>, Initializable {
    @FXML private Label     labelVirhe;
    @FXML private TextField editNimi;
    @FXML private TextField editPaino;
    @FXML private TextField editSarjat;
    @FXML private TextField editToistot;
    
    
    /**
     * OK-painike
     */
    @FXML private void handleOK() {
        if (liikeKohdalla != null && liikeKohdalla.getNimi().equals("")) {
            naytaVirhe("Nimi ei voi olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML private void handlePeruuta() {
        liikeKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }
    
    
    /**
     * Kontrollerin alustus
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    /**
     * Tiedon palautus
     */
    @Override
    public Liike getResult() {
        return liikeKohdalla;
    }
    
    
    /**
     * Oletuskeskitys
     */
    @Override
    public void handleShown() {
        kenttia[0].requestFocus(); 
    }
    
    
    /**
     * Aseta oletusliike
     */
    @Override
    public void setDefault(Liike oletus) {
        liikeKohdalla = oletus;
        naytaLiike(kenttia, liikeKohdalla);
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Liike liikeKohdalla;
    private TextField[] kenttia;
    
    
    /**
     * Tekee tarvittavat alustukset
     */
    private void alusta() {
        kenttia = new TextField[] {editNimi, editPaino, editSarjat, editToistot};
        editNimi.setOnKeyReleased(e -> handleMuutos(0, editNimi));
        editPaino.setOnKeyReleased(e -> handleMuutos(1, editPaino));
        editSarjat.setOnKeyReleased(e -> handleMuutos(2, editSarjat));
        editToistot.setOnKeyReleased(e -> handleMuutos(3,editToistot));
    }
    
    
    /**
     * Käsitellään muutos liikkeen kentässä
     * @param kenttaNro monesko kentta kyseessa
     * @param kentta jossa muutos
     */
    private void handleMuutos(int kenttaNro, TextField kentta) {
        if (liikeKohdalla == null)
            return;
        String syote = kentta.getText();
        String virhe = liikeKohdalla.aseta(kenttaNro, syote);
        if (virhe == null) {
            kentta.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
            return;
        }
        kentta.getStyleClass().add("virhe");
        naytaVirhe(virhe);
    }
    
    
    /**
     * Aliohjelma ilmestyneen virheilmoituksen näyttämiseen käyttöliittymässä
     * @param virhe virheilmoitus
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
     * Näytetään annettu liike dialogissa
     * Täytetään liikkeen kentät
     * @param kentat TextField jossa täytettävät kentät
     * @param liike joka näytetään
     */
    public static void naytaLiike(TextField[] kentat, Liike liike) {
        if (liike == null)
            return;
        for (int i = 0; i < kentat.length; i++)
            kentat[i].setText(liike.anna(i));
    }
    
    
    /**
     * Luodaan liikkeen kysymisdialogi ja palautetaan sama tietue muutettuna tai null.
     * @param modalityStage mille ollaan modaalisia, null = ollaan modaalisia sovellukselle.
     * @param oletus minkä tietoa näytetään oletuksena.
     * @return null jos peruutetaan, muuten täytetty tietue.
     */
    public static Liike kysyLiike(Stage modalityStage, Liike oletus) {
        return ModalController.showModal(PunttisalimuistioGUIController.class.getResource("LiikeDialogView.fxml"), "Liike", modalityStage, oletus);
    }
}