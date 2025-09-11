package be.leonet.corentin.gestion_commande_spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.leonet.corentin.gestion_commande_spring.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	public Article findByDenomination(String denomination);
}
