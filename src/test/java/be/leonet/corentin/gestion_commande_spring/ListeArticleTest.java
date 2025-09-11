package be.leonet.corentin.gestion_commande_spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.leonet.corentin.gestion_commande_spring.dao.ArticleRepository;
import be.leonet.corentin.gestion_commande_spring.dao.CategorieRepository;
import be.leonet.corentin.gestion_commande_spring.dao.CommandeRepository;
import be.leonet.corentin.gestion_commande_spring.dao.ListeArticleRepository;
import be.leonet.corentin.gestion_commande_spring.model.Article;
import be.leonet.corentin.gestion_commande_spring.model.Categorie;
import be.leonet.corentin.gestion_commande_spring.model.Commande;
import be.leonet.corentin.gestion_commande_spring.model.ListeArticle;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ListeArticleTest {

	String nom = "nom";
	String prenom = "prénom";
	Date date = Date.valueOf("2022-12-20");
	Commande commande = new Commande(nom, prenom, date);
	
	Categorie categorie = new Categorie("categorie");
	String denomination = "deno";
	Double prix = 20.5;
	Integer stock = 15;
	Article article = new Article(categorie, denomination, prix, stock);
	
	Integer quantity = 2;
	
	
	@Autowired
	private ArticleRepository artRep;
	
	@Autowired
	private CommandeRepository comRep;
	
	@Autowired
	private ListeArticleRepository listArtRep;
	
	@Autowired
	private CategorieRepository catRep;
	
	@Test
	@Order(1)
	void test_Create_ListeArtice() {
		
		System.out.println("##########################################################");
		System.out.println("######################## Test C ##########################");
		
		catRep.save(categorie);
		article.setCategorie(categorie);
		Article articleTest = artRep.save(article);
		Commande commandeTest = comRep.save(commande);
		
		ListeArticle listeArticle = new ListeArticle(articleTest, commandeTest, quantity, prix);
		ListeArticle listeArticleTest = listArtRep.save(listeArticle);
		
		assertThat(listeArticleTest.getCommande()).isEqualTo(commandeTest);
		assertThat(listeArticleTest.getArticle()).isEqualTo(articleTest);
		assertThat(listeArticleTest.getQuantity()).isEqualTo(quantity);
		assertThat(listeArticleTest.getPrix()).isEqualTo(prix);
		
		assertThat(listeArticleTest.getArticle().getStock()).isEqualTo(stock-quantity);
		
		listArtRep.delete(listeArticleTest);
	}
	
	@Test
	@Order(2)
	void test_Read_ListeArtice() {
		System.out.println("##########################################################");
		System.out.println("######################## Test R ##########################");
		
		Article articleTest = artRep.findByDenomination("deno");
		Commande commandeTest = comRep.findById(1).get();
		
		ListeArticle listeArticle = new ListeArticle(articleTest, commandeTest, quantity, prix);
		
		Integer id = listArtRep.save(listeArticle).getId();
		ListeArticle listeArticleTest = listArtRep.findById(id).get();
		
		assertThat(listeArticleTest.getCommande()).isEqualTo(commandeTest);
		assertThat(listeArticleTest.getArticle()).isEqualTo(articleTest);
		assertThat(listeArticleTest.getQuantity()).isEqualTo(quantity);
		assertThat(listeArticleTest.getPrix()).isEqualTo(prix);
		
		assertThat(listeArticleTest.getArticle().getStock()).isEqualTo(stock-quantity);
		
		listArtRep.delete(listeArticleTest);
	}
	
	@Test
	@Order(3)
	void test_Update_ListeArtice() {
		System.out.println("##########################################################");
		System.out.println("######################## Test U ##########################");
		
		Article articleTest = artRep.findByDenomination("deno");
		Commande commandeTest = comRep.findById(1).get();
		
		ListeArticle listeArticle = new ListeArticle(articleTest, commandeTest, quantity, prix);
		
		Integer id = listArtRep.save(listeArticle).getId();
		ListeArticle listeArticleTest = listArtRep.findById(id).get();
		
		listeArticleTest.setQuantity(quantity*2);
		listArtRep.save(listeArticleTest);
		
		assertThat(listeArticleTest.getCommande()).isEqualTo(commandeTest);
		assertThat(listeArticleTest.getArticle()).isEqualTo(articleTest);
		assertThat(listeArticleTest.getQuantity()).isEqualTo(quantity*2);
		assertThat(listeArticleTest.getPrix()).isEqualTo(prix);

		assertThat(listeArticleTest.getArticle().getStock()).isEqualTo(stock-(quantity*2));
		
		listArtRep.delete(listeArticleTest);
		
	}
	
	@Test
	@Order(4)
	void test_Check_ListeArtice() {
		System.out.println("##########################################################");
		System.out.println("######################## Test check ##########################");
		
		Article articleTest = artRep.findByDenomination("deno");
		Commande commandeTest = comRep.findById(1).get();
		
		ListeArticle listeArticle = new ListeArticle(articleTest, commandeTest, quantity, prix);
		
		Integer id = listArtRep.save(listeArticle).getId();
		ListeArticle listeArticleTest = listArtRep.findById(id).get();
		
		
		
		Exception exception = assertThrows(Exception.class, ()-> {
			listeArticleTest.setQuantity(stock+1);
			listArtRep.save(listeArticleTest);
		});
		
		assertThat(exception.getMessage()).isEqualTo("Could not commit JPA transaction");

		
	}
	
	@Test
	@Order(5)
	void test_Delete_ListeArtice() {
		System.out.println("##########################################################");
		System.out.println("######################## Test D ##########################");
		Article articleTest = artRep.findByDenomination("deno");
		Commande commandeTest = comRep.findById(1).get();
		
		ListeArticle listeArticle = new ListeArticle(articleTest, commandeTest, quantity, prix);
		
		ListeArticle listeArticleTest = listArtRep.save(listeArticle);
		listArtRep.delete(listeArticleTest);
		
		artRep.delete(article);
		comRep.delete(commande);
		assertThat(comRep.findById(listeArticleTest.getId()).isEmpty()).isTrue();
	}

}
