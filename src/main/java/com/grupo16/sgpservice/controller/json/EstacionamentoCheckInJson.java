package com.grupo16.sgpservice.controller.json;

import java.time.LocalDateTime;

import com.grupo16.sgpservice.domain.RegistroEstacionamentoBase;
import com.grupo16.sgpservice.domain.RegistroEstacionamentoPeriodoFixo;
import com.grupo16.sgpservice.domain.RegistroEstacionamentoPeriodoVariavel;
import com.grupo16.sgpservice.domain.TipoEstacionamento;
import com.grupo16.sgpservice.domain.Veiculo;

import lombok.ToString;

@ToString
public class EstacionamentoCheckInJson {
	private String id;
	private LocalDateTime dataHoraFim;
	private Integer quantidadeHoras;
	private String veiculoId;
	private TipoEstacionamento tipoEstacionamento;
	
	public RegistroEstacionamentoBase parseDomain() {
		
		RegistroEstacionamentoBase registroEstacionamento = null;
		
		if(TipoEstacionamento.TEMPO_FIXO.equals(tipoEstacionamento)) {
			
			registroEstacionamento = RegistroEstacionamentoPeriodoFixo.builder()
				.id(id)
				.quantidadeHoras(quantidadeHoras)
				.veiculo(Veiculo.builder().id(veiculoId).build())
			.build();
			
		} else {
			registroEstacionamento = RegistroEstacionamentoPeriodoVariavel.builder()
					.id(id)
					.veiculo(Veiculo.builder().id(veiculoId).build())
					.build();
		}
		
		return registroEstacionamento;
	}
	
}
