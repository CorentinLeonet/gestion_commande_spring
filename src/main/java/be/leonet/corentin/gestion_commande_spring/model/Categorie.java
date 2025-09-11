package be.leonet.corentin.gestion_commande_spring.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {

	/* Constructor */
	
	public Categorie () {
		
	}
		
	public Categorie(String nom, List<Article> articles) {
		this.nom = nom;
		Articles = articles;
	}
	
	public Categorie(String nom) {
		this.nom = nom;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categorie")
	private Integer id;
	
	@Column(name = "nom_categorie", nullable = false)
	private String nom;
	
	@OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Article> Articles = new ArrayList<Article>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
}
