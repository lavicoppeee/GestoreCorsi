package it.polito.tdp.corsi.model;

import java.util.*;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {

	private CorsoDAO dao;

	public Model() {
		dao = new CorsoDAO();
	}

	public List<Corso> getCorsiPerPeriodo(Integer pd) {

		return dao.getCorsiPerPeriodo(pd);
	}

	public Map<Corso, Integer> getIscrittiPerPeriodo(Integer pd) {

		return dao.getIscrittiPerPeriodo(pd);
	}
	
	public List<Studente> getStudentiPerCorso(Corso corso){
		
		return dao.getStudentiPerCorso(corso);
	}
	
	public boolean esisteCorso(String codins) {
		return dao.esisteCorso(codins);
	}
	
	
	/**PRIMO MODO
	 * 
	 * creo una Mappa e controllo se esiste li dentro
	 * @param c
	 * @return
	 */
	public Map<String, Integer> divisioneCDS(Corso c) {
		//SOLUZIONE UNO
//		List<Studente> studenti =dao.getStudentiPerCorso(c);
//		
//		Map<String, Integer> statistiche =new HashMap<String, Integer>();
//		
//		for(Studente s:studenti) {
//			
//			
//			if(statistiche.containsKey(s.getCDS())){
//				statistiche.put(s.getCDS(), statistiche.get(s.getCDS())+1);
//				
//			}
//			statistiche.put(s.getCDS(), 1);
//				
//		}
//		return statistiche; 
		
		return dao.getDivisioneCDS(c);
	}
	
}
