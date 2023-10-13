package com.grupo16.sgpservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.sgpservice.controller.json.EnderecoJson;
import com.grupo16.sgpservice.domain.Endereco;
import com.grupo16.sgpservice.usecase.AlterarEnderecoUseCase;
import com.grupo16.sgpservice.usecase.CriarEnderecoUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("enderecos")
public class EnderecoController {
	
	@Autowired
	private CriarEnderecoUseCase criarEnderecoUseCase;

	@Autowired
	private AlterarEnderecoUseCase alterarEnderecoUseCase;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public String criar(
			@RequestBody(required = true) EnderecoJson enderecoJson) {
		log.trace("Start enderecoJson={}", enderecoJson);
		
		Endereco endereco = enderecoJson.mapearParaEnderecoDomain(null);
		
		String id = criarEnderecoUseCase.criar(endereco);
				
		log.trace("End id={}", id);
		return id;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("{id}")
	public void alterar(
			@PathVariable(required = true, name = "id") String id,
			@RequestBody(required = true) EnderecoJson enderecoJson) {
		log.trace("Start id={}", id);
		
		Endereco endereco = enderecoJson.mapearParaEnderecoDomain(id);
		
		alterarEnderecoUseCase.alterar(endereco);
		log.trace("End");
	}
	
}