package be.leonet.corentin.gestion_commande_spring.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "commandes")
public class Commande implements Serializable {
	
	/* Constructor */
	public Commande() {
		
	}
	
	public Commande(String nom, String prenom, Date date) {
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
	}
	
	public Commande(Integer id) {
		this.id = id;
	}

	/* Attributs */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8654537406194343400L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_commande")
	private Integer id;
	@Column(name = "nom_commande", nullable = false)
	private String nom;
	@Column(name = "prenom_commande", nullable = false)
	private String prenom;
	@Column(name = "date_commande", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "cloture_commande", nullable = false)
	private Boolean cloture = false;
	
	@OneToMany(mappedBy = "commande", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<ListeArticle> listArticle = new ArrayList<ListeArticle>();
	
	
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
		final Commande other = (Commande) obj;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the cloture
	 */
	public Boolean getCloture() {
		return cloture;
	}

	/**
	 * @param cloture the cloture to set
	 */
	public void setCloture(Boolean cloture) {
		this.cloture = cloture;
	}
	
	/**
	 * @return the listArticle
	 */
	public List<ListeArticle> getListArticle() {
		return listArticle;
	}

	/**
	 * @param cloture the listArticle to set
	 */
	public void setListArticle(List<ListeArticle> listArticle) {
		this.listArticle = listArticle;
	}
	
	public void addArticle(Article article, int quantity) {
		this.listArticle.add(new ListeArticle(article, this, quantity, article.getPrix()));
	}


}
