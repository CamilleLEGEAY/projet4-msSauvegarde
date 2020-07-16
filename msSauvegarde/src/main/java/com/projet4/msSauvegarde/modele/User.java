package com.projet4.msSauvegarde.modele;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String tokenUserName;
	
	@OneToMany (mappedBy = "user", cascade = {CascadeType.ALL})
	private List<Recherche> recherches;

	public Integer getId() {
		return id;
	}

	public String getTokenUserName() {
		return tokenUserName;
	}

	public void setTokenUserName(String tokenUserName) {
		this.tokenUserName = tokenUserName;
	}

	public List<Recherche> getRecherches() {
		return recherches;
	}

	public void setRecherches(List<Recherche> recherches) {
		this.recherches = recherches;
	}

}
