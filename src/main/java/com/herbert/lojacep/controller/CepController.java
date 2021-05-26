package com.herbert.lojacep.controller;

import com.herbert.lojacep.service.CepService;
import com.herbert.lojacep.service.dto.CepDto;
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

@RequestMapping("/api/cep")
@RestController
@RequiredArgsConstructor
public class CepController {

	private final CepService cepService;
	
	@GetMapping
	public ResponseEntity<Page<CepDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(this.cepService.findAll(pageable));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CepDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.cepService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<CepDto> save(@Valid @RequestBody CepDto cepDto) {
		return new ResponseEntity<>(this.cepService.save(cepDto), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CepDto> update(@PathVariable Long id, @Valid @RequestBody CepDto cepDto) {
		return ResponseEntity.ok(this.cepService.update(id, cepDto));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.cepService.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
