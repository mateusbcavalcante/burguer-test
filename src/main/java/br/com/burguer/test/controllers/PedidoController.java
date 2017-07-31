package br.com.burguer.test.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.services.HamburguerService;
import br.com.burguer.test.services.PedidoService;

@Controller
public class PedidoController {
	
	@Autowired
	private HamburguerService hamburguerService;
	
	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public String listIngredients(Model model) {
		Iterable<Hamburguer> hamburguers = hamburguerService.listAllHamburguers();
		hamburguers.forEach(hamb -> hamb.setPrice(pedidoService.calculaPrecoFinal(hamb)));
		model.addAttribute("burguers", hamburguers);
		return "pedido";
	}
	
	
	
	@RequestMapping(value = "/pedido/parte2", method = RequestMethod.GET)
	public String listIa(Model model) {
		
		
		return "";
	}

}
