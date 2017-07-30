package br.com.burguer.test.controllers;

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
public class HamburguerController {

	@Autowired
	private HamburguerService hamburguerService;
	
	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private PedidoService pedidoService;

//	@RequestMapping(value = "/hamburguer/{id}")
//	public String listIngredients(@PathVariable Integer id, Model model) {
//
//		Hamburguer hamburguers = hamburguerService.findById(id);
//
//		model.addAttribute("hamburguerIngredients", hamburguers.getIngredients());
//
//		return "/hamburguer/ingredientes";
//	}
	
	@RequestMapping(value="/hamburguer/{id}", method=RequestMethod.GET,   produces="application/json")
	public String listHamburguerIngredients2(@PathVariable Integer id, Model model) {
		Hamburguer hamburguers = hamburguerService.findById(id);
		Iterable<Ingredient> bla = ingredientService.listAllIngredients();
		model.addAttribute("ingredients", bla);
		return "fragments/hamburguerEdit :: hamburguerEditModal";
	}

//	@RequestMapping(value="/hamburguer/{id}", method=RequestMethod.GET,   produces="application/json")
//	public @ResponseBody List<Ingredient> listHamburguerIngredients(@PathVariable Integer id, Model model) {
//		Hamburguer hamburguers = hamburguerService.findById(id);
//		Iterable<Ingredient> bla = ingredientService.listAllIngredients();
//		return StreamSupport.stream(bla.spliterator(), false)
//                .collect(Collectors.toList());
//	}
//	

	@RequestMapping(value = "/hamburguer/ingredientes/add", method = RequestMethod.POST)
	public String adicionaIngredientes(Model model) {
		Iterable<Hamburguer> hamburguers = hamburguerService.listAllHamburguers();

		hamburguers.forEach(hamb -> hamb.setPrice(pedidoService.calculaPrecoFinal(hamb)));

		model.addAttribute("burguers", hamburguers);

		return "ok";
	}

}
