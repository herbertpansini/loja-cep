package com.herbert.lojacep.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cep")
@Data
public class Cep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="codigo_loja", nullable = false)
	private Loja loja;
	
	@Column(name = "faixa_inicio", length = 8, nullable = false)
	private String faixaInicio;
	
	@Column(name = "faixa_fim", length = 8, nullable = false)
	private String faixaFim;
}
