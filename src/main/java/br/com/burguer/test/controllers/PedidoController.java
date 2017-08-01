package br.com.burguer.test.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.services.HamburguerService;
import br.com.burguer.test.services.IngredientService;
import br.com.burguer.test.services.PedidoService;

@Controller
public class PedidoController {

	@Autowired
	private HamburguerService hamburguerService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private PedidoService pedidoService;

	Set<Hamburguer> hamburguersAdded = new HashSet<>();

	BigDecimal totalPrice = new BigDecimal(BigDecimal.ZERO.doubleValue());

	private static final String MODAL_PEDIDO_RESULTS_FRAGMENT = "hamburguerEdit :: resultsList";

	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public String listIngredients(Model model) {
		Iterable<Hamburguer> hamburguers = hamburguerService.listAllHamburguers();
		hamburguers.forEach(hamb -> hamb.setPrice(pedidoService.calculaPrecoFinal(hamb)));
		model.addAttribute("burguers", hamburguers);
		model.addAttribute("hamburguersCartList", hamburguersAdded);

		totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
		model.addAttribute("totalPrice", totalPrice);
		return "pedido";
	}

	@RequestMapping(value = "/hamburguer/{id}", method = RequestMethod.GET, produces = "application/json")
	public String listHamburguerIngredients(@PathVariable Integer id, Model model) {
		Iterable<Ingredient> defaultIngredients = ingredientService.listDefaultAllIngredients();
		Iterable<Ingredient> hamburguerIngredients = ingredientService.listAllIngredientsByHamburguerId(id);

		model.addAttribute("allDefaultIngredients", defaultIngredients);
		model.addAttribute("hamburguerIngredients", hamburguerIngredients);
		Hamburguer hamburguer = hamburguerService.findById(id);
		model.addAttribute("hamburguerSelected", hamburguer);
		model.addAttribute("hamburguersCartList", hamburguersAdded);
		return MODAL_PEDIDO_RESULTS_FRAGMENT;
	}

	@RequestMapping(value = "/hamburguer/add/cart/{id}", method = RequestMethod.GET, produces = "application/json")
	public String addHamburguerToCart(@PathVariable Integer id, Model model) {
		Hamburguer hamburguer = hamburguerService.findById(id);

		totalPrice = new BigDecimal(BigDecimal.ZERO.doubleValue());

		hamburguersAdded.add(hamburguer);

		addCartAndSumFinalPrice();

		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("hamburguersCartList", hamburguersAdded);
		return MODAL_PEDIDO_RESULTS_FRAGMENT;
	}

	@RequestMapping(value = "/hamburguer/remove/cart/{id}", method = RequestMethod.GET, produces = "application/json")
	public String removeHamburguerToCart(@PathVariable Integer id, Model model) {
		totalPrice = new BigDecimal(BigDecimal.ZERO.doubleValue());

		hamburguersAdded.forEach(hamb -> {

			if (hamb.getTemporaryId().intValue() == id.intValue()) {
				hamburguersAdded.remove(hamb);
			}
		});

		addCartAndSumFinalPrice();

		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("hamburguersCartList", hamburguersAdded);
		return MODAL_PEDIDO_RESULTS_FRAGMENT;
	}

	private void addCartAndSumFinalPrice() {
		hamburguersAdded.forEach(hamb -> {
			BigDecimal price = pedidoService.calculaPrecoFinal(hamb);
			Random ran = new Random();
			int randomNum = ran.nextInt();
			if(randomNum < 0) {
				randomNum = randomNum * -1;
			}
			hamb.setTemporaryId(randomNum);

			price = price.setScale(2, RoundingMode.HALF_UP);
			hamb.setPrice(price);
			totalPrice = totalPrice.add(price);
			totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
		});
	}

}
