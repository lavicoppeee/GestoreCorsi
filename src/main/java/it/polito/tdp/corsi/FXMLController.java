package it.polito.tdp.corsi;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;


import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCorsiPerPeriodo;

    @FXML
    private Button btnStudenti;

    @FXML
    private Button btnNumeroStudenti;

    @FXML
    private Button btnDivisioneStudenti;

    @FXML
    private TextField txtPeriodo;

    @FXML
    private TextField txtCodiceCorso;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void corsiPerPeriodo(ActionEvent event) {

    	txtRisultato.clear();
    	
    	String input=txtPeriodo.getText();
    	
    	Integer pd;
    	
    	//controllo che sia un numero
    	try {
    		
    		pd=Integer.parseInt(input);
    				
    	}catch(NumberFormatException e){
    		
    		txtRisultato.setText("Devi inserire uno o due!");
    		return;
    	}
    	
    	if(!pd.equals(1) || !pd.equals(2)) {
    		txtRisultato.setText("Devi inserire uno o due!");
    		return;
    	}
    	
    	//stampo le cose
    	
    	List<Corso> corsi=this.model.getCorsiPerPeriodo(pd);
    	
    	for(Corso c: corsi) {
    		txtRisultato.appendText(c.toString()+ "\n");
    		
    	}
    		
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
        txtRisultato.clear();
    	
    	String input=txtPeriodo.getText();
    	
    	Integer pd;
    	
    	//controllo che sia un numero
    	try {
    		
    		pd=Integer.parseInt(input);
    				
    	}catch(NumberFormatException e){
    		
    		txtRisultato.setText("Devi inserire uno o due!");
    		return;
    	}
    	
    	if(!pd.equals(1) || !pd.equals(2)) {
    		txtRisultato.setText("Devi inserire uno o due!");
    		return;
    	}
    	
    	Map<Corso,Integer> statistiche=this.model.getIscrittiPerPeriodo(pd);
    	
    	for(Corso c: statistiche.keySet()) {
    		txtRisultato.appendText(c.getNome()+ "  "+ statistiche.get(c)+"\n");
    	}
    }

    @FXML
    void stampaDivisione(ActionEvent event) {

    }

    @FXML
    void stampaStudenti(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCodiceCorso != null : "fx:id=\"txtCodiceCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
	}
}

