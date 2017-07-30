package br.com.burguer.test.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.burguer.test.domain.Hamburguer;

public interface HamburguerRepository  extends CrudRepository<Hamburguer, Integer>{

}
