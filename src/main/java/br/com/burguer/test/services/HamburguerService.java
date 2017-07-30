package br.com.burguer.test.services;

import br.com.burguer.test.domain.Hamburguer;

public interface HamburguerService {
	
	
	Iterable<Hamburguer> listAllHamburguers();
	
	Hamburguer findById(Integer id);

}
