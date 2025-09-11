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
import be.leonet.corentin.gestion_commande_spring.dao.CommandeRepository;
import be.leonet.corentin.gestion_commande_spring.dao.ListeArticleRepository;
import be.leonet.corentin.gestion_commande_spring.model.ListeArticle;

@Controller
public class ListeArticleController {

	@Autowired
	private ArticleRepository artRep;
	@Autowired
	private CommandeRepository commandeRep;
	@Autowired
	private ListeArticleRepository listartRep;
	
/* Gestion des Articles commandés */
	
	@RequestMapping(value = "/commande/articlesCommande/nouveau/{id}")
	public String addListeArticle(@PathVariable(value = "id") Integer id, Model model) {
		ListeArticle listeArticle = new ListeArticle();
		listeArticle.setCommande(commandeRep.findById(id).get());;
		model.addAttribute("articles", artRep.findAll());
		model.addAttribute("listeArticle",listeArticle);
		return "/commande/articlesCommande/addArticle";
	}
	
	
	@PostMapping(value = "/commande/articlesCommande/saveListeArticle")
	public String saveListeArticle(@ModelAttribute(value="listeArticle") ListeArticle listeArticle, @RequestParam(value="articleid") Integer id) 
	{
		listeArticle.setArticle(artRep.findById(id).get());
		for(ListeArticle listeart : listeArticle.getCommande().getListArticle()){
			if(listeart.getArticle() == listeArticle.getArticle()) {
				listeart.setQuantity(listeArticle.getQuantity() + listeart.getQuantity());
				listeArticle = listeart;
			}
		}
		listartRep.save(listeArticle);
		return "redirect:/commmandeEdit/" + listeArticle.getCommande().getId();
	}
	
	@PostMapping(value = "/commande/articlesCommande/updateListeArticle")
	public String updateListeArticle(@ModelAttribute(value="listeArticle") ListeArticle listeArticle) 
	{
		listartRep.save(listeArticle);
		return "redirect:/commmandeEdit/" + listeArticle.getCommande().getId();
	}
	
	@RequestMapping(value = "/commande/articlesCommande/delete/{id}")
	public String saveListeArticle(@PathVariable(value = "id") Integer id) 
	{
		Integer idCommande = listartRep.findById(id).get().getCommande().getId();
		listartRep.deleteById(id);
		return "redirect:/commmandeEdit/" + idCommande;
	}
	
	@RequestMapping(value = "/commmande/articlesCommande/edit/{id}")
	public String editListeArticle(@PathVariable(value = "id") Integer id, Model model) 
	{
		model.addAttribute("listeArticle", listartRep.findById(id).get());
		return "/commande/articlesCommande/editArticle";
	}
	
}
