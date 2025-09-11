package be.leonet.corentin.gestion_commande_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.leonet.corentin.gestion_commande_spring.dao.ArticleRepository;
import be.leonet.corentin.gestion_commande_spring.dao.CategorieRepository;
import be.leonet.corentin.gestion_commande_spring.model.Article;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository artRep;
	
	@Autowired
	private CategorieRepository catRep;
	
	/* Gestion Articles */

	@RequestMapping(value = "/article")
	public String getArticle(Model model) {
		model.addAttribute("articles",artRep.findAll());
		return "/article/article";
	}
	
	@RequestMapping(value = "/addArticle")
	public String addArticle(Model model) {
		Article article = new Article(); 
		model.addAttribute("art", article); //passe un objet vide pour que thymeleaf le remplisse
		model.addAttribute("categories", catRep.findAll());
		return "/article/addArticle";
	}
	
	@PostMapping(value = "/saveArticle")
	public String saveArticle(@ModelAttribute(value="art") Article art, @RequestParam(value="categorieNom") String nom) {
		if(nom.equals("vide") || art.getPrix() == null || art.getStock() == null || art.getDenomination() == null) {

		}
		else {
			art.setCategorie(catRep.findByNom(nom));
			artRep.save(art);
		}
		
		return "redirect:/article";
	}

	@RequestMapping(value = "/artEdit/{id}")
	public String editArticle(@PathVariable(value = "id") Integer id, Model model) {
		model.addAttribute("art",artRep.findById(id).get());
		model.addAttribute("categories", catRep.findAll());
		return "/article/editArticle";
	}
	
	@RequestMapping(value = "/artDelete/{id}")
	public String deleteArticle(@PathVariable(value = "id") Integer id) {
		artRep.delete(artRep.findById(id).get());
		return "redirect:/article";
	}
	
	
	
	
}
