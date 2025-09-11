package be.leonet.corentin.gestion_commande_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.leonet.corentin.gestion_commande_spring.dao.CategorieRepository;
import be.leonet.corentin.gestion_commande_spring.model.Categorie;

@Controller
public class CategorieController {
	
	
	@Autowired
	private CategorieRepository catRep;
	
	/* Gestion Articles */

	@RequestMapping(value = "/categorie")
	public String getCategorie(Model model) {
		model.addAttribute("categories",catRep.findAll());
		return "/categorie/categorie";
	}
	
	@RequestMapping(value = "/addCategorie")
	public String addCategorie(Model model) {
		Categorie categorie = new Categorie(); 
		model.addAttribute("cat", categorie); //passe un objet vide pour que thymeleaf le remplisse
		return "/categorie/addCategorie";
	}
	
	@PostMapping(value = "/saveCategorie")
	public String saveCategorie(@ModelAttribute(value="cat") Categorie cat) {
		
		if(catRep.findByNom(cat.getNom()) == null) {
			catRep.save(cat);
		}
		
		return "redirect:/categorie";
	}

	@RequestMapping(value = "/catEdit/{id}")
	public String editCategorie(@PathVariable(value = "id") Integer id, Model model) {
		model.addAttribute("cat",catRep.findById(id).get());
		return "/categorie/editCategorie";
	}
	
	@RequestMapping(value = "/catDelete/{id}")
	public String deleteCategorie(@PathVariable(value = "id") Integer id) {
		catRep.delete(catRep.findById(id).get());
		return "redirect:/categorie";
	}
}
