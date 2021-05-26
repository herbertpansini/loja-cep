package com.herbert.lojacep.repository;

import com.herbert.lojacep.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

    @Query(" SELECT " +
            "   c.id " +
            " FROM " +
            "   Cep c " +
            " WHERE " +
            "   (c.faixaInicio BETWEEN :faixaInicio AND :faixaFim) OR " +
            "   (c.faixaFim    BETWEEN :faixaInicio AND :faixaFim)")
    Long findByInterval(@Param("faixaInicio") String faixaInicio, @Param("faixaFim") String faixaFim);
}