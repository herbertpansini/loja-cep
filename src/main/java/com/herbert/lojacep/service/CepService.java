package com.herbert.lojacep.service;

import com.herbert.lojacep.service.dto.CepDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CepService {

	Page<CepDto> findAll(Pageable pageable);
	
	CepDto findById(Long id);
	
	CepDto save(CepDto cepDto);
	
	CepDto update(Long id, CepDto cepDto);
	
	void delete(Long id);
}