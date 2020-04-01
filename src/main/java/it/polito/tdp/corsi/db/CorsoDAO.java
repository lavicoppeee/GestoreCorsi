package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;


import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {

	
	public boolean esisteCorso(String codins) {
		String sql="Select * from corso where codins=?";
		
		try {
			Connection conn=ConnectionDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs=st.executeQuery();
			
			conn.close();
			
		 if(rs.next()) {
			 return true;
		 }else {
			 conn.close();
			 return false;
		 }
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	}

	public List<Corso> getCorsiPerPeriodo(Integer pd){
		String sql="Select * from corso where pd=?";
		List<Corso> result= new ArrayList<Corso>();
		
		
		
		try {
			Connection conn=ConnectionDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.add(c);
			}
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		return result;
	}
	
	public Map<Corso, Integer> getIscrittiPerPeriodo(Integer pd){
		String sql="Select c.codins, c.nome, c.crediti, c.pd, count(*) as tot from corso as c, iscrizione as i where c.codins=i.codins and c.pd=? group by c.codins, c.nome, c.crediti, c.pd";
		Map<Corso,Integer> result= new HashMap<Corso,Integer>();
		
		try {
			Connection conn=ConnectionDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				Integer n=rs.getInt("tot");
				result.put(c,n);
			}
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		return result;
	}
	
	
	public List<Studente> getStudentiPerCorso(Corso corso){
		
		String sql="Select s.matricola, s.nome, s.cognome, s.CDS " + 
				"from studente as s, iscrizione as i " + 
				"where s.matricola=i.matricola and i.codins= ? ";
		
		List<Studente> result= new ArrayList<Studente>();
		
		
		try {
			Connection conn=ConnectionDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Studente s=new Studente(rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
				result.add(s);
			}
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		return result;
	}
	
	public Map<String, Integer> getDivisioneCDS(Corso c){
		
		String sql="Select s.CDS, COUNT(*) as tot " + 
				"from studente as s, iscrizione as i " + 
				"where s.matricola=i.matricola and s.cds<> \"\" and i.codins= ? " + 
				"group by s.CDS";
		
		Map<String,Integer> result= new HashMap<String,Integer>();
		
		try {
			Connection conn=ConnectionDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, c.getCodins());
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {

				Integer n=rs.getInt("tot");
				result.put(rs.getString("CDS"),n);
			}
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		return result;
	}
	
	
	
}
