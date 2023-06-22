package fxPunttisalimuistio;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import punttisalimuistio.Treeni;

/**
 * Treenille oma kontrolleri
 * @author Eetu
 * @version 0.7.1, 22.06.2023 Tiedoston synty
 */
public class TreeniDialogController implements ModalControllerInterface<Treeni>, Initializable {
    @FXML private Label labelVirhe;
    
    
    /**
     * OK-painike
     */
    @FXML private void handleOK() {
        //
    }
    
    
    /**
     * Peruuta-painike
     */
    @FXML private void handlePeruuta() {
        //
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }
    
    
    @Override
    public Treeni getResult() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }
    
    
    @Override
    public void setDefault(Treeni oletus) {
        // TODO Auto-generated method stub  
    }
}