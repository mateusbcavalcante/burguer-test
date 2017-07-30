package br.com.burguer.test.services;

import java.math.BigDecimal;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;

public interface PedidoService {
	
	void adicionaIngrediente(Hamburguer hamburguer, Ingredient ingrediente);	
	
	BigDecimal calculaPrecoFinal(Hamburguer hamburguer);
	
	boolean lightPromocao(Hamburguer hamburguer);
	
	BigDecimal descontoMuito(Hamburguer hamburguer, String ingredientDescription);
	
}
