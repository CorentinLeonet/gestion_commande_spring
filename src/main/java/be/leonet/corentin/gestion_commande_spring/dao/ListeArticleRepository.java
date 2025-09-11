package be.leonet.corentin.gestion_commande_spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.leonet.corentin.gestion_commande_spring.model.ListeArticle;

@Repository
public interface ListeArticleRepository  extends JpaRepository<ListeArticle, Integer> {

}
