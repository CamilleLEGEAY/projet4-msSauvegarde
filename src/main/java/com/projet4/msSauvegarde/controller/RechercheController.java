package com.projet4.msSauvegarde.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet4.msSauvegarde.dao.UserRepository;
import com.projet4.msSauvegarde.modele.Recherche;
import com.projet4.msSauvegarde.modele.RechercheReceived;
import com.projet4.msSauvegarde.modele.User;
import com.projet4.msSauvegarde.security.JwtUtilService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/msSauvegarde")
public class RechercheController {
	
	@Autowired
	private JwtUtilService jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * to save a new Recherche
	 * @param recherche + token
	 * @return message
	 */
	@PostMapping(path = "/public/SaveRecherche")
	public String saveRecherche(@RequestBody RechercheReceived rechercheReceived) throws Exception {
		Recherche rechercheEntity = new Recherche();
		User userEntity = new User();
		rechercheEntity=rechercheReceived.getRecherche();
		
		//ligne pour les test sans token crypte
		//String subject = rechercheReceived.getToken();
		//ligne a utiliser si token entrant a le bon cryptage
		String subject = jwtUtil.getUsernameFromToken(rechercheReceived.getToken());
		
		Optional<User> rep = userRepository.findByTokenUserName(subject);
		if(rep.isPresent()) {
			userEntity = rep.get();
			rechercheEntity.setUser(userEntity);
			userEntity.getRecherches().add(rechercheEntity);
		}
		else {
			userEntity.setTokenUserName(subject);
			rechercheEntity.setUser(userRepository.save(userEntity));
			List<Recherche> liste = new ArrayList<Recherche>();
			liste.add(rechercheEntity);
			userEntity.setRecherches(liste);
		}
		userRepository.save(userEntity);
	
		return "Recherche Sauvegardée";
	}
	
	@PostMapping(path="/private/find")  
	public List<Recherche> findAllResearches(@RequestBody String token) {	
		List<Recherche> recherches = new ArrayList<Recherche>();
		String subject = jwtUtil.getUsernameFromToken(token);
		Optional<User> rep = userRepository.findByTokenUserName(subject);
		if(rep.isPresent()) {
			recherches = rep.get().getRecherches()
				.stream()
				.map(recherche -> this.toShow(recherche)).collect(Collectors.toList());
		}			
		return recherches;
	}
	
	/*@GetMapping(path="/public/find/{token}")  
	public List<Recherche> findAllResearches(@PathVariable String token) {	
		List<Recherche> recherches = new ArrayList<Recherche>();
		Optional<User> rep = userRepository.findByTokenUserName(token);
		if(rep.isPresent()) {
			recherches = rep.get().getRecherches()
				.stream()
				.map(recherche -> this.toShow(recherche)).collect(Collectors.toList());
		}			
		return recherches;
	}*/
	
	private Recherche toShow(Recherche rechercheEntity) {
		Recherche rechercheShown = new Recherche();
		rechercheShown.setActivite_principale(rechercheEntity.getActivite_principale());
		rechercheShown.setCode_postal(rechercheEntity.getCode_postal());
		rechercheShown.setDate_creation(rechercheEntity.getDate_creation());
		rechercheShown.setDenomination(rechercheEntity.getDenomination());
		rechercheShown.setEffectifs(rechercheEntity.getEffectifs());
		rechercheShown.setLibelle_commune(rechercheEntity.getLibelle_commune());
		rechercheShown.setNb_resultat(rechercheEntity.getNb_resultat());
		rechercheShown.setSiren(rechercheEntity.getSiren());
		return rechercheShown;
	}

}
