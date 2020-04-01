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
}
