package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SpellCheckerController {

    private Dictionary model;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> lingua;
    
    @FXML
    private TextArea campoTesto;
    
    @FXML
    private TextArea paroleSbagliate;

    @FXML
    private Button spellCheck;

    @FXML
    private Button clearText;

    @FXML
    private Text errori;

    @FXML
    private Text tempoEsecuzione;

    @FXML
    void doClearText(ActionEvent event) {
    	campoTesto.clear();
    	paroleSbagliate.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	double tempo1=System.nanoTime();
    	int count = 0;
    	paroleSbagliate.clear();
    	model.loadDictionary(lingua.getValue());
    	List<String> dacontrollare = new LinkedList<String>();
    	String testo = campoTesto.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	String input[] = testo.split(" ");
    	
    	for(String s : input)
    		dacontrollare.add(s);
    	
    	System.out.println(dacontrollare);
    	List<RichWord> output = new ArrayList<RichWord>();
    	output = model.spellCheckText(dacontrollare);
    	
    	for(RichWord parola : output) {
    		if(parola.isCorretta() == false) {
    			paroleSbagliate.appendText(parola.getParola()+"\n");
    			count++;
    		}
    	}
    	errori.setText("The text contains "+count+" errors");
    	String s = Double.toString(System.nanoTime()-tempo1);
    	tempoEsecuzione.setText(s);
    }
    
    @FXML
    void initialize() {
        assert lingua != null : "fx:id=\"lingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert spellCheck != null : "fx:id=\"spellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert clearText != null : "fx:id=\"clearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert errori != null : "fx:id=\"errori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert tempoEsecuzione != null : "fx:id=\"tempoEsecuzione\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert paroleSbagliate != null : "fx:id=\"paroleSbagliate\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert campoTesto != null : "fx:id=\"campoTesto\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	lingua.getItems().addAll("English", "Italiano");
    }
    
}
