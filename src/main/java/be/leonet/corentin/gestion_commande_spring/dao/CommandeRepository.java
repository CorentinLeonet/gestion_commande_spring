package be.leonet.corentin.gestion_commande_spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.leonet.corentin.gestion_commande_spring.model.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer>{

}
