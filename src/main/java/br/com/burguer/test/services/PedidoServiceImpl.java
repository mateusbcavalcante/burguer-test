package br.com.burguer.test.services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.exception.PirateBurguerException;

@Service
public class PedidoServiceImpl implements PedidoService {

	public void adicionaIngrediente(Hamburguer hamburguer, Ingredient ingrediente) {
		hamburguer.getIngredients().add(ingrediente);
	}

	public BigDecimal calculaPrecoFinal(Hamburguer hamburguer) {

		if (hamburguer != null) {
			Double finalPrice = hamburguer.getIngredients().stream()
					.mapToDouble(ingredient -> ingredient.getPrice().doubleValue()).sum();

			BigDecimal descontoMuitoQueijo = descontoMuito(hamburguer, "Queijo");
			BigDecimal descontoMuitaCarne = descontoMuito(hamburguer, "Hambúrguer de carne");
			finalPrice = new BigDecimal(
					finalPrice.doubleValue() - descontoMuitaCarne.doubleValue() - descontoMuitoQueijo.doubleValue())
							.doubleValue();
			if (lightPromocao(hamburguer)) {
				finalPrice = finalPrice - (finalPrice * 0.1);
			}

			return new BigDecimal(finalPrice);
		} else {
			throw new PirateBurguerException("Não foi possivel calcular o preço final do hamburguer.");
		}
	}

	public BigDecimal descontoMuito(Hamburguer hamburguer, String ingredientDescription) {

		Set<Ingredient> ingredientes = new HashSet<Ingredient>();
		hamburguer.getIngredients().forEach((ingredient) -> {
			if (ingredientDescription.equals(ingredient.getDescription())) {
				ingredientes.add(ingredient);
			}
		});

		int valorADescontar = regraDe3(ingredientes);
		if (ingredientes.iterator().hasNext()) {
			Ingredient ing = ingredientes.iterator().next();
			return new BigDecimal(ing.getPrice().doubleValue() * valorADescontar);
		}else{
			return new BigDecimal(BigDecimal.ZERO.doubleValue());
		}

		
	}

	private int regraDe3(Set<Ingredient> ingredientes) {
		if (ingredientes.size() % 3 != 0) {
			return (ingredientes.size() - 1) / 3;
		} else {
			return ingredientes.size() / 3;
		}
	}

	

	public boolean lightPromocao(Hamburguer hamburguer) {

		boolean hasBacon = verificaSeHaIngrediente(hamburguer, "Bacon");
		boolean hasAlface = verificaSeHaIngrediente(hamburguer, "Alface");

		if (hasAlface && !hasBacon) {
			return true;
		}

		return false;
	}

	private boolean verificaSeHaIngrediente(Hamburguer hamburguer, String ingredientDescription) {
		Ingredient ingredient = hamburguer.getIngredients().stream()
				.filter(in -> "ingredientDescription".equals(in.getDescription())).findAny().orElse(null);
		if (ingredient != null) {
			return true;
		} else {
			return false;
		}
	}

}
