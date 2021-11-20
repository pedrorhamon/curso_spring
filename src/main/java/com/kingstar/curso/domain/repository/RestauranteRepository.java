package com.kingstar.curso.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kingstar.curso.domain.entity.Restaurante;

public interface RestauranteRepository
		extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	List<Restaurante> findByTaxaFrenteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//	@Query("from Restaurante whrere nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

	List<Restaurante> findByNomeContainingAndId(String nome, Long id);

	Optional<Restaurante> findFirstByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int coutByCozinhaId(Long cozinha);
	
	@Query("from Restaurante r join r.cozinha join fetch r.formasPagamento")
	List<Restaurante> findAll();

}
