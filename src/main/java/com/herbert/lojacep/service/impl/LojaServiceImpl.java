package com.herbert.lojacep.service.impl;

import com.herbert.lojacep.model.Loja;
import com.herbert.lojacep.repository.LojaRepository;
import com.herbert.lojacep.service.LojaService;
import com.herbert.lojacep.service.dto.LojaDto;
import com.herbert.lojacep.service.mapper.LojaMapper;
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
public class LojaServiceImpl implements LojaService {

	private final LojaRepository lojaRepository;

	private final LojaMapper lojaMapper;

	@Override
	@Transactional(readOnly = true)
	public LojaDto findByCep(String cep) {
		return this.lojaMapper.toDto( this.lojaRepository.findByCep(cep).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NO_CONTENT, "shop.not.found")) );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<LojaDto> findAll(Pageable pageable) {
		return this.lojaRepository.findAll(pageable).map(this.lojaMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public LojaDto findById(Long id) {
		return this.lojaMapper.toDto( this.lojaRepository.findById(id).orElseThrow(()->
				new ResponseStatusException(HttpStatus.NO_CONTENT, "not.found")) );
	}

	@Override
	public LojaDto save(LojaDto lojaDto) {
		this.validate(lojaDto);
		return this.lojaMapper.toDto( this.lojaRepository.save( this.lojaMapper.toEntity(lojaDto) ) );
	}

	@Override
	public LojaDto update(Long id, LojaDto lojaDto) {
		LojaDto lojaUpdate = this.findById(id);
		this.validate(lojaDto);
		BeanUtils.copyProperties(lojaDto, lojaUpdate, "id");
		return this.lojaMapper.toDto( this.lojaRepository.save( this.lojaMapper.toEntity(lojaUpdate) ) );
	}

	@Override
	public void delete(Long id) {
		this.lojaRepository.deleteById(id);		
	}

	private void validate(LojaDto lojaDto) {
		if (this.validateName(lojaDto)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "shop.already.exists");
		}
	}

	private boolean validateName(LojaDto lojaDto) {
		Loja lojaExist = this.lojaRepository.findByNomeEqualsIgnoreCase(lojaDto.getNome());
		return !(lojaExist == null || lojaExist.getId().equals(lojaDto.getId()));
	}
}