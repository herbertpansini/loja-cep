package com.herbert.lojacep.service.impl;

import com.herbert.lojacep.repository.CepRepository;
import com.herbert.lojacep.service.CepService;
import com.herbert.lojacep.service.dto.CepDto;
import com.herbert.lojacep.service.mapper.CepMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class CepServiceImpl implements CepService {

	private final CepRepository cepRepository;

	private final CepMapper cepMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<CepDto> findAll(Pageable pageable) {
		return this.cepRepository.findAll(pageable).map(this.cepMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public CepDto findById(Long id) {
		return this.cepMapper.toDto( this.cepRepository.findById(id).orElseThrow(()->
				new ResponseStatusException(HttpStatus.NO_CONTENT, "not.found")) );
	}

	@Override
	public CepDto save(CepDto cepDto) {
		this.validate(cepDto);
		return this.cepMapper.toDto( this.cepRepository.save( this.cepMapper.toEntity(cepDto) ) );
	}

	@Override
	public CepDto update(Long id, CepDto cepDto) {
		CepDto cepUpdate = this.findById(id);
		this.validate(cepDto);
		BeanUtils.copyProperties(cepDto, cepUpdate, "id");
		return this.cepMapper.toDto( this.cepRepository.save( this.cepMapper.toEntity(cepUpdate) ) );
	}

	@Override
	public void delete(Long id) {
		this.cepRepository.deleteById(id);		
	}

	private void validate(CepDto cepDto) {
		if (this.validateInterval(cepDto)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "interval.already.exists");
		}
	}

	private boolean validateInterval(CepDto cepDto) {
		Long id = this.cepRepository.findByInterval(cepDto.getFaixaInicio(), cepDto.getFaixaFim());
		return !(id == null || id.equals(cepDto.getId()));
	}
}