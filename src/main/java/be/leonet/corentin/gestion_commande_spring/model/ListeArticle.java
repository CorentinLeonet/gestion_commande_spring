package be.leonet.corentin.gestion_commande_spring.model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.Check;

import be.leonet.corentin.gestion_commande_spring.dao.EvenementListeArticle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Check(constraints = "qtycommande >= 0")
@Table(name = "listearticles")
@EntityListeners(EvenementListeArticle.class)
public class ListeArticle implements Serializable {
	
	/* Constructors */
	
	public ListeArticle() {
		
	}
	
	public ListeArticle(Article article, Commande commande, Integer quantity, Double prix) {
		this.article = article;
		this.commande = commande;
		this.quantity = quantity;
		this.prix = prix;
	}
	
	public ListeArticle(int id, Article article, Commande commande, Integer quantity, Double prix) {
		this.id = id;
		this.article = article;
		this.commande = commande;
		this.quantity = quantity;
		this.prix = prix;
	}
	

	/* Attributs */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3942034327818895019L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_listearticle", insertable = false, updatable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleid_article", referencedColumnName = "id_article", nullable = false)
	private Article article;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commandeid_commande", referencedColumnName = "id_commande", nullable = false)
	private Commande commande;
	
	@Column(name = "qtycommande", nullable = false)
	private Integer quantity;
	
	@Column(name = "prixactuel", nullable = false)
	private Double prix;
	
	@Transient
	private int oldQuantity;


	/* Méthodes */

	 @PostLoad
	 public void OnLoad() {
		 this.oldQuantity = this.quantity;
	 }
	
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
		final ListeArticle other = (ListeArticle) obj;
		return Objects.equals(this.id , other.id);
	}
	
	
	
	/* Setter and Getter */

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
	 * @return 
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param 
	 */
	public void setArticle(Article article) {
		this.article = article;
	}
	
	/**
	 * @return 
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * @param 
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	/**
	 * @return 
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param 
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return 
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param 
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Integer getOldQuantity() {
		return oldQuantity;
	}

	/**
	 * @param 
	 */
	public void setOldQuantity(Integer oldQuantity) {
		this.oldQuantity = oldQuantity;
	}
	

}
