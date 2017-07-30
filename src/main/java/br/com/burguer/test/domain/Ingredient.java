package br.com.burguer.test.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	private String description;
	
	private BigDecimal price;
	
	private Boolean defaultIngredient;
		
	@ManyToOne
	@JoinColumn(name = "hamburguer_id")
	private Hamburguer hamburguer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Hamburguer getHamburguer() {
		return hamburguer;
	}

	public void setHamburguer(Hamburguer hamburguer) {
		this.hamburguer = hamburguer;
	}

	public Boolean isDefaultIngredient() {
		return defaultIngredient;
	}

	public void setDefaultIngredient(Boolean defaultIngredient) {
		this.defaultIngredient = defaultIngredient;
	}

}
