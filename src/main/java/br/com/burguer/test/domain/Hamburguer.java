package br.com.burguer.test.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;

@Entity
public class Hamburguer {
	
	
	public Hamburguer() {
		
	}
	
	public Hamburguer(String description, Set<Ingredient> ingredients, Integer qtd, BigDecimal price) {
		setDescription(description);
		setIngredients(ingredients);
		setQtd(qtd);
		setPrice(price);
		setId(null);
		setVersion(null);
		setPedido(null);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer temporaryId;
	
	@Version
	private Integer version;

	private String description;
	
	@OneToMany(mappedBy = "hamburguer", fetch = FetchType.EAGER)
	private Set<Ingredient> ingredients;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Pedido pedido;
	
	
	private int qtd = 0;
	
	
	public Set<Ingredient> getIngredients() {
		if(ingredients == null){
			ingredients = new HashSet<Ingredient>();
		}
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	private BigDecimal price;

	public BigDecimal getPrice(){
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Integer getTemporaryId() {
		return temporaryId;
	}

	public void setTemporaryId(Integer temporaryId) {
		this.temporaryId = temporaryId;
	}

	
}
