package com.herbert.lojacep.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CepDto  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@NotNull
	private LojaDto loja;

	@NotBlank
	private String faixaInicio;

	@NotBlank
	private String faixaFim;
}