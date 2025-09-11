package be.leonet.corentin.gestion_commande_spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import be.leonet.corentin.gestion_commande_spring.model.Article;
import be.leonet.corentin.gestion_commande_spring.model.ListeArticle;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;



@Component
public class EvenementListeArticle {
		
	
	private static ArticleRepository artRep;

	@Autowired
	public void setArticleRepository(ArticleRepository artRep) { //Evite que le repository soit null lors du prepersist
		EvenementListeArticle.artRep = artRep;
	}
	
	
	
	@PrePersist
	public void PrePersistListeArticle(final ListeArticle listeArticle) throws Exception {
		
		int quantityDifferent =  listeArticle.getOldQuantity() - listeArticle.getQuantity();
		int stockDifferent = listeArticle.getArticle().getStock() + quantityDifferent;

		if(stockDifferent < 0) {
			throw new Exception("Pas assez de stock pour " +  listeArticle.getArticle().getDenomination());
		}
		else {
			
			listeArticle.getArticle().setStock(stockDifferent);
			artRep.save(listeArticle.getArticle());
		}
	}
	
	@Transactional
	@PreUpdate
	public void PreUpdateListeArticle(final ListeArticle listeArticle) throws Exception {
		
		int quantityDifferent =  listeArticle.getOldQuantity() - listeArticle.getQuantity();
		int stockDifferent = listeArticle.getArticle().getStock() + quantityDifferent;

		if(stockDifferent < 0) {
			throw new Exception("Pas assez de stock pour " +  listeArticle.getArticle().getDenomination());
		}
		else {
			Article article = listeArticle.getArticle();
			article.setStock(stockDifferent);
			artRep.save(article);
		}
	}
	
	@Transactional
	@PreRemove
	public void PreRemoveListeArticle(final ListeArticle listeArticle) throws Exception {
		
		int stockDifferent = listeArticle.getArticle().getStock() + listeArticle.getQuantity();

		Article article = listeArticle.getArticle();
		article.setStock(stockDifferent);
		artRep.save(article);
		
	}

}



