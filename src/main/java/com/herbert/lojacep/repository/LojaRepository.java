package com.herbert.lojacep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.herbert.lojacep.model.Loja;

import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

    Loja findByNomeEqualsIgnoreCase(String nome);

    @Query(" SELECT " +
            "   l " +
            " FROM Cep c " +
            " INNER JOIN " +
            "   c.loja l " +
            " WHERE " +
            "   :cep BETWEEN c.faixaInicio AND c.faixaFim")
    Optional<Loja> findByCep(@Param("cep") String cep);
}
