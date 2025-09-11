package be.leonet.corentin.gestion_commande_spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Check(constraints = "stock_article >= 0")
@Table(name = "articles")
public class Article implements Serializable {
	/* Constructor */
	public Article() {
		
	}
	
	public Article(String denomination, Double prix, Integer stock) {
		this.denomination = denomination;
		this.prix = prix;
		this.stock = stock;
	}
	
	public Article(Categorie categorie, String denomination, Double prix, Integer stock) {
		this.categorie = categorie;
		this.denomination = denomination;
		this.prix = prix;
		this.stock = stock;
	}
	
	public Article(Categorie categorie, String denomination, Double prix, Integer stock, Boolean actif) {
		this.categorie = categorie;
		this.denomination = denomination;
		this.prix = prix;
		this.stock = stock;
		this.actif = actif;
	}
	

	/* Attributs */

	private static final long serialVersionUID = 4996720855280287470L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_article")
	private Integer id;

	@Column(name = "denomination_article", nullable = false)
	private String denomination;
	@Column(name = "prix_article", nullable = false)
	private Double prix;
	@Column(name = "stock_article", nullable = false)
	private Integer stock;
	@Column(name = "actif_article", nullable = false)
	private Boolean actif = true;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie", nullable = true)
	private Categorie categorie;
	
	@OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
	private List<ListeArticle> listeArticle = new ArrayList<ListeArticle>();
	
	/* Méthodes */
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 + hash + Objects.hashCode(this.id);
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		final Article other = (Article) obj;
		return Objects.equals(this.id, other.id);
	}

	
	/* Getter and Setter */
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param nom the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the denomination
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * @return the actif
	 */
	public Boolean getActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	
	public List<ListeArticle> getListeArticles(){
		return this.listeArticle;
	}
	
	public void setListeArticles(List<ListeArticle> listeArticle){
		this.listeArticle = listeArticle;
	}

}