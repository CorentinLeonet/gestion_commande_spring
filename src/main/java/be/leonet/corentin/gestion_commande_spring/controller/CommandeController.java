package be.leonet.corentin.gestion_commande_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.leonet.corentin.gestion_commande_spring.dao.ArticleRepository;
import be.leonet.corentin.gestion_commande_spring.dao.CommandeRepository;
import be.leonet.corentin.gestion_commande_spring.model.Commande;


@Controller
public class CommandeController {
	
	
	@Autowired
	private ArticleRepository artRep;
	@Autowired
	private CommandeRepository commandeRep;
	
	
	/* Gestion Commandes */
	
	@RequestMapping(value = "/commande")
	public String getCommandes(Model model) {
		model.addAttribute("commandes",commandeRep.findAll());
		return "/commande/commande";
	}
	
	@RequestMapping(value = "commmandeEdit/{id}")
	String editCommande(@PathVariable(value = "id") Integer id, Model model) {
		model.addAttribute("commande", commandeRep.findById(id).get());
		model.addAttribute("articles", artRep.findAll());
		return "commande/editCommande";
	}
	
	@PostMapping(value = "/saveCommande")
	public String saveCommande(@ModelAttribute(value="commande") Commande commande) {
		commandeRep.save(commande);
		return "redirect:/commande";
	}
	
	@RequestMapping(value = "/commandeDelete/{id}")
	String delCommande(@PathVariable(value = "id") Integer id) {
		commandeRep.delete(commandeRep.findById(id).get());
		return "redirect:/commande";
	}
	
	@RequestMapping(value = "/addCommande")
	String addCommande(Model model) {
		Commande commande = new Commande();
		model.addAttribute("commande",commande);
		return "commande/addCommande";
	}
	
}
