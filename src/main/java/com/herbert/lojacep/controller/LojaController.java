package com.herbert.lojacep.controller;

import com.herbert.lojacep.service.LojaService;
import com.herbert.lojacep.service.dto.LojaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/loja")
@RestController
@RequiredArgsConstructor
public class LojaController {

	private final LojaService lojaService;
	
	@GetMapping
	public ResponseEntity<Page<LojaDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.lojaService.findAll(pageable));
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<LojaDto> findByCep(@PathVariable String cep) {
		return ResponseEntity.ok(this.lojaService.findByCep(cep));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<LojaDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.lojaService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<LojaDto> save(@Valid @RequestBody LojaDto lojaDto) {
		return new ResponseEntity<>(this.lojaService.save(lojaDto), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<LojaDto> update(@PathVariable Long id, @Valid @RequestBody LojaDto lojaDto) {
		return ResponseEntity.ok(this.lojaService.update(id, lojaDto));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.lojaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}