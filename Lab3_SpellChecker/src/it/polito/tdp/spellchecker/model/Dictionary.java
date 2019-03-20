package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	List<String> dizionario = new ArrayList<String>();
	
	public void loadDictionary(String language) {
		this.dizionario.clear();
		if(language.equals("Italiano")) {
			this.leggiFile("rsc/Italian.txt");
		}
		if(language.equals("English")) {
			this.leggiFile("rsc/English.txt");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> output = new ArrayList<RichWord>();
		Boolean trovata =false;
		
		for(String parola : inputTextList) {
			if(dizionario.contains(parola))
				trovata=true;
			else
				trovata = false;
			
			RichWord word = new RichWord(parola);
			word.setCorretta(trovata);
			output.add(word);
		}
		
		return output;
	}
	
	private void leggiFile(String nomeFile) {
		try {
		FileReader fr = new FileReader(nomeFile);
		BufferedReader br = new BufferedReader(fr);
		String word;
		while((word = br.readLine()) != null) {
			dizionario.add(word);
		}
		br.close();
		}catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}
}
