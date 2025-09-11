package be.leonet.corentin.gestion_commande_spring;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.leonet.corentin.gestion_commande_spring.dao.CommandeRepository;
import be.leonet.corentin.gestion_commande_spring.model.Commande;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommandeTest {


	String nom = "nom";
	String prenom = "prénom";
	Date date = Date.valueOf("2022-12-20");
	Commande commande = new Commande(nom, prenom, date);

	
	@Autowired
	private CommandeRepository comRep;
	
	@Test
	@Order(1)
	void test_Create_Commande() {
		
		Commande commandeTest = comRep.save(commande);
		assertThat(commandeTest.getNom()).isEqualTo(nom);
		assertThat(commandeTest.getPrenom()).isEqualTo(prenom);
		assertThat(commandeTest.getDate()).isEqualTo(date);
	}
	
	@Test
	@Order(2)
	void test_Read_Commande() {
		
		Integer id = comRep.save(commande).getId();
		Commande commandeTest = comRep.findById(id).get();
		
		assertThat(commandeTest.getId()).isEqualTo(id);
		assertThat(commandeTest.getNom()).isEqualTo(nom);
		assertThat(commandeTest.getPrenom()).isEqualTo(prenom);
		assertThat(commandeTest.getDate()).isEqualTo(date);
	}
	
	@Test
	@Order(3)
	void test_Update_Commande() {
		
		Integer id = comRep.save(commande).getId();
		Commande commandeTest = comRep.save(commande);
		commandeTest.setCloture(true);
		comRep.save(commandeTest);
		Commande commandeTest2 = comRep.findById(id).get();
		
		assertThat(commandeTest2.getCloture()).isTrue();
		assertThat(commandeTest2.getNom()).isEqualTo(nom);
		assertThat(commandeTest2.getPrenom()).isEqualTo(prenom);
		assertThat(commandeTest2.getDate()).isEqualTo(date);

	}
	
	@Test
	@Order(4)
	void test_Delete_Commande() {
		Commande commandeTest = comRep.save(commande);
		comRep.delete(commandeTest);
		
		assertThat(comRep.findById(commandeTest.getId()).isEmpty()).isTrue();
	}

}
