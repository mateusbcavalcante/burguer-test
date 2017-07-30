package br.com.burguer.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.repositories.HamburguerRepository;

@Service
public class HamburguerServiceImpl implements HamburguerService {

	@Autowired
	private HamburguerRepository hamburguerRepository;

	@Override
	public Iterable<Hamburguer> listAllHamburguers() {
		return hamburguerRepository.findAll();
	}

	@Override
	public Hamburguer findById(Integer id) {
		return hamburguerRepository.findOne(id);
	}

}
