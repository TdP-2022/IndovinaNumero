/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private int segreto;
	private final int TMAX = 8;
	private final int NMAX = 100;
	private int tentativiFatti;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader
    
    @FXML // fx:id="hboxTentativi"
    private HBox hboxTentativi; // Value injected by FXMLLoader


    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	//gestione di una nuova partita
    	this.segreto = (int)((Math.random() * NMAX) +1);
    	this.tentativiFatti = 0;
    	
    	//gestione interfaccia
    	txtTentativi.setText(Integer.toString(TMAX));
    	hboxTentativi.setDisable(false);
    	txtRisultato.clear();    	
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	String ts = txtTentativo.getText();
    	int tentativo;
    	
    	//controllo 1 -> input numerico
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un tentativo numerico tra 1 e 100!");
    		return;
    	}
    	
    	//controllo 2 -> intervallo numerico corretto
    	if(tentativo < 1 || tentativo > NMAX) {
    		txtRisultato.setText("Devi inserire un tentativo numerico tra 1 e 100!");
    		return;
    	}
    	
    	this.tentativiFatti ++;
    	
    	if(tentativo == this.segreto) {
    		//HAI VINTO
    		txtRisultato.setText("HAI INDOVINATO CON " + this.tentativiFatti + " TENTATIVI");
    		hboxTentativi.setDisable(true);
    		return;
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		//esaurito i tentativi -> HAI PERSO
    		txtRisultato.setText("HAI PERSO! IL SEGRETO ERA: " + this.segreto);
    		hboxTentativi.setDisable(true);
    		return;
    	}
    	
    	if(tentativo < this.segreto) {
    		txtRisultato.setText("Tentativo Troppo Basso!");
    	} else {
    		txtRisultato.setText("Tentativo Troppo Alto!");
    	}
    	
    	txtTentativi.setText(Integer.toString(TMAX-tentativiFatti));
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
