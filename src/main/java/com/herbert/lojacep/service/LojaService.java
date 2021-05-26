package com.herbert.lojacep.service;

import com.herbert.lojacep.service.dto.LojaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LojaService {

	Page<LojaDto> findAll(Pageable pageable);

	LojaDto findByCep(String cep);
	
	LojaDto findById(Long id);
	
	LojaDto save(LojaDto lojaDto);
	
	LojaDto update(Long id, LojaDto lojaDto);
	
	void delete(Long id);
}
