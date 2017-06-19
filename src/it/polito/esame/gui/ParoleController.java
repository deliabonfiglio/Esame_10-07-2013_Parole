package it.polito.esame.gui;

/**
 * Sample Skeleton for 'paroleT1.fxml' Controller Class
 */

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.esame.bean.Model;
import it.polito.esame.bean.Parola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;



public class ParoleController {

		private Model model;
		
	    @FXML // ResourceBundle that was given to the FXMLLoader
	    private ResourceBundle resources;

	    @FXML // URL location of the FXML file that was given to the FXMLLoader
	    private URL location;

	    @FXML // fx:id="theRoot"
	    private BorderPane theRoot; // Value injected by FXMLLoader

	    @FXML // fx:id="txtResult"
	    private TextArea txtResult; // Value injected by FXMLLoader

	    @FXML // fx:id="x1"
	    private Color x1; // Value injected by FXMLLoader

	    @FXML // fx:id="txtParolaIniziale"
	    private TextField txtParolaIniziale; // Value injected by FXMLLoader

	    @FXML // fx:id="txtParolaFinale"
	    private TextField txtParolaFinale; // Value injected by FXMLLoader

	    @FXML // fx:id="btnCerca"
	    private Button btnCerca; // Value injected by FXMLLoader

	    @FXML // fx:id="txtLunghezza"
	    private TextField txtLunghezza; // Value injected by FXMLLoader

	    @FXML // fx:id="btnCarica"
	    private Button btnCarica; // Value injected by FXMLLoader
	    
	    @FXML
	    void doCarica(ActionEvent event) {
	    	txtResult.clear();
	    	
	    	String lung= txtLunghezza.getText();
	    	try{
	    		int lunghezza = Integer.parseInt(lung);
	    		
	    		if(lunghezza<=0){
	    			txtResult.appendText("Errore: inserire lunghezza maggiore di 0.\n");
	    			return;
	    		}
	    		
	    		txtResult.appendText("Caricate "+model.getWords(lunghezza).size()+" parole di lunghezza "+lunghezza+".\n");
	    		String s =model.creaGrafo(lunghezza);
	    		txtResult.appendText(s.toString()+"\n");
	    		
	    		/*int conta=0;
	    		for(Parola p: model.getWords(lunghezza)){
	    			conta += model.getCollegate(p);
	    		}
	    		txtResult.appendText("Numero totale di collegamenti: "+conta+".\n");*/
	    		
	    		txtResult.appendText("Parola "+model.getMaxCollegate());
	    		
	    	} catch(NumberFormatException e){
	    		e.printStackTrace();
	    		txtResult.appendText("Inserire un numero con solo cifre.\n");
	    	}
	    }

	    @FXML
	    void doCerca(ActionEvent event) {
	    	String numerotxt= txtLunghezza.getText();
	    	
	    	try{
	    	
	    		int num = Integer.parseInt(numerotxt);
	    		if(num<=0){
	    			txtResult.appendText("Inserire numero maggiore di 0.\n");
	    			return;
	    		}
	    		
	    		String piniziale = txtParolaIniziale.getText();
	    		String pfinale = txtParolaFinale.getText();
	    		
	    		if(piniziale.length()==num && pfinale.length()==num){
	    			txtResult.clear();
	    			
	    			model.creaGrafo(num);
	    			
	    			Parola pa1 = null;
	    			Parola pa2 = null;
	    			
	    			Map<String, Parola> mappa= model.getMappaParole(num);
	    			if(mappa.containsKey(piniziale)&& mappa.containsKey(pfinale)){
	    				pa1= mappa.get(piniziale);
	    				pa2 = mappa.get(pfinale);
	    			}
	    			
	    			if(pa1!= null && pa2!= null && !pa1.equals(pa2)){
	    				Set<Parola> traversed = model.getPathBetween(pa1, pa2);
	    				
	    				if(traversed.size()!=0){
		    				for(Parola p: traversed){
		    					txtResult.appendText(p.getNome()+"\n");
		    				}
	    				} else {
	    					txtResult.appendText("Non esiste un cammino tra le parole inserire.\n");
	    				}
	    				
	    			} else {
	    				txtResult.appendText("Errore: inserire 2 parole presenti nel dizionario e diverse tra loro.\n");
	    			}	    			
	    		} else {
	    			txtResult.appendText("Inserire 2 parole con lunghezza pari a quella specificata nel box sulla sinistra.\n");
	    		}
	    		
	    	}catch(NumberFormatException e){
	    		e.printStackTrace();
	    		txtResult.appendText("Inserire un numero con solo cifre.\n");
	    	}

	    }

	    @FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
	        assert theRoot != null : "fx:id=\"theRoot\" was not injected: check your FXML file 'paroleT1.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'paroleT1.fxml'.";
	        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'paroleT1.fxml'.";
	        assert txtParolaIniziale != null : "fx:id=\"txtParolaIniziale\" was not injected: check your FXML file 'paroleT1.fxml'.";
	        assert txtParolaFinale != null : "fx:id=\"txtParolaFinale\" was not injected: check your FXML file 'paroleT1.fxml'.";
	        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'paroleT1.fxml'.";
	        assert txtLunghezza != null : "fx:id=\"txtLunghezza\" was not injected: check your FXML file 'paroleT1.fxml'.";
	        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'paroleT1.fxml'.";

	    }

		public void setModel(Model model) {
			this.model=model;
			
		}
	}
