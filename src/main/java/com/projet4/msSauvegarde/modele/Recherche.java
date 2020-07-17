package com.projet4.msSauvegarde.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Recherche {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	private User user;
	
	private String siren;
	private String denomination;
	private String date_creation;
	private String activite_principale;
	private String code_postal;
	private String libelle_commune;
	private int effectifs;
	private int nb_resultats;
	
	
	
	
	public String getSiren() {
		return siren;
	}
	public void setSiren(String siren) {
		this.siren = siren;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}
	public String getActivite_principale() {
		return activite_principale;
	}
	public void setActivite_principale(String activite_principale) {
		this.activite_principale = activite_principale;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getLibelle_commune() {
		return libelle_commune;
	}
	public void setLibelle_commune(String libelle_commune) {
		this.libelle_commune = libelle_commune;
	}
	public int getEffectifs() {
		return effectifs;
	}
	public void setEffectifs(int effectifs) {
		this.effectifs = effectifs;
	}
	public Integer getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getNb_resultat() {
		return nb_resultats;
	}
	public void setNb_resultat(int nb_resultats) {
		this.nb_resultats = nb_resultats;
	}
	
	
}
