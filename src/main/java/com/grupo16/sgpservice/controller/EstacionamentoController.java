package com.grupo16.sgpservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.sgpservice.controller.json.EstacionamentoCheckInJson;
import com.grupo16.sgpservice.controller.json.ReciboJson;
import com.grupo16.sgpservice.controller.json.RegistroEstacionamentoJson;
import com.grupo16.sgpservice.domain.RegistroEstacionamentoBase;
import com.grupo16.sgpservice.usecase.EstacionamentoCheckInUseCase;
import com.grupo16.sgpservice.usecase.GetEstacionamentoUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping()
public class EstacionamentoController {
	
	@Autowired
	private EstacionamentoCheckInUseCase estacionamentoCheckInUseCase;
	
	@Autowired
	private GetEstacionamentoUseCase getEstacionamentoUseCase;
	
	@PostMapping("estacionamentos/check-in")
	@ResponseStatus(HttpStatus.CREATED)
	public String checkIn(@RequestBody(required = true) EstacionamentoCheckInJson json) {
		log.trace("Start json={}", json);
		
		String ckeckInId = estacionamentoCheckInUseCase.ckeckIn(json.parseDomain());
		
		log.trace("End ckeckInId={}", ckeckInId);
		return ckeckInId;
	}
	
	@GetMapping("estacionamentos/{id}")
	public RegistroEstacionamentoJson get(@PathVariable("id") String estacionamentoId) {
		log.trace("Start estacionamentoId={}", estacionamentoId);
		
		RegistroEstacionamentoBase registroEstacionamento = getEstacionamentoUseCase.getById(estacionamentoId);
		RegistroEstacionamentoJson response = new RegistroEstacionamentoJson(registroEstacionamento);
		
		log.trace("End response={}", response);
		return response;
	}
	
	@GetMapping("estacionamentos/{id}/recibo")
	public ReciboJson getRecibo(
			@PathVariable("id") String estacionamentoId,
			@RequestParam(name = "status") String status) {
		log.trace("Start estacionamentoId={}, status={}", estacionamentoId, status);
		
		RegistroEstacionamentoBase registroEstacionamento = getEstacionamentoUseCase.getByIdAndPagamentoStatus(estacionamentoId, status);
		ReciboJson reciboJson = new ReciboJson(registroEstacionamento);
		
		log.trace("End response={}", reciboJson);
		return reciboJson;
	}
}
