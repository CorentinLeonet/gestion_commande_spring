package be.leonet.corentin.gestion_commande_spring;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.leonet.corentin.gestion_commande_spring.dao.ArticleRepository;
import be.leonet.corentin.gestion_commande_spring.dao.CategorieRepository;
import be.leonet.corentin.gestion_commande_spring.model.Article;
import be.leonet.corentin.gestion_commande_spring.model.Categorie;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleTest {

	Categorie categorie = new Categorie("categorie");
	String denomination = "den";
	Double prix = 20.5;
	Integer stock = 15;
	Article article = new Article(categorie, denomination, prix, stock);

	@Autowired
	private ArticleRepository artRep;
	
	@Autowired
	private CategorieRepository catRep;
	
	@Test
	@Order(1)
	void test_Create_Article() {
		
		categorie = catRep.save(categorie);
		article.setCategorie(categorie);
		Article articleTest = artRep.save(article);
		
		assertThat(articleTest.getCategorie()).isEqualTo(categorie);
		assertThat(articleTest.getDenomination()).isEqualTo(denomination);
		assertThat(articleTest.getPrix()).isEqualTo(prix);
		assertThat(articleTest.getStock()).isEqualTo(stock);
	}
	
	@Test
	@Order(2)
	void test_Read_Article() {
		
		Integer id = artRep.findByDenomination(denomination).getId();
		Article articleTest = artRep.findById(id).get();
		categorie = catRep.findByNom("categorie");
		
		assertThat(articleTest.getId()).isEqualTo(id);
		assertThat(articleTest.getCategorie().getNom()).isEqualTo(categorie.getNom());
		assertThat(articleTest.getDenomination()).isEqualTo(denomination);
		assertThat(articleTest.getPrix()).isEqualTo(prix);
		assertThat(articleTest.getStock()).isEqualTo(stock);
	}
	
	@Test
	@Order(3)
	void test_Update_Article() {
		
		Article articleTest = artRep.findByDenomination(denomination);
		articleTest.setActif(false);
		artRep.save(articleTest);
		Article articleTest2 =  artRep.findByDenomination(denomination);
		
		categorie = catRep.findByNom("categorie");
		
		assertThat(articleTest2.getActif()).isFalse();
		assertThat(articleTest2.getCategorie().getNom()).isEqualTo(categorie.getNom());
		assertThat(articleTest2.getDenomination()).isEqualTo(denomination);
		assertThat(articleTest2.getPrix()).isEqualTo(prix);
		assertThat(articleTest2.getStock()).isEqualTo(stock);
	}
	
	@Test
	@Order(4)
	void test_Delete_Article() {
		Article articleTest = artRep.findByDenomination(denomination);
		artRep.delete(articleTest);
		
		assertThat(artRep.findById(articleTest.getId()).isEmpty()).isTrue();
	}
}
