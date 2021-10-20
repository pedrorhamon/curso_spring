package com.kingstar.curso.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingstar.curso.domain.entity.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

//	List<Cozinha> consultarPorNome(String nome);

}
