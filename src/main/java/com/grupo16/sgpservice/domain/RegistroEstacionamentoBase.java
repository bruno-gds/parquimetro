package com.grupo16.sgpservice.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString
public abstract class RegistroEstacionamentoBase {
	private String id;
	protected LocalDateTime dataHoraInicio;
	protected LocalDateTime dataHoraTermino;
	
	@Setter
	protected Pagamento pagamento;
	
	@Setter
	protected LocalDateTime dataHoraPrevisaoNotificacao;
	
	@Setter
	protected LocalDateTime dataHoraUltimaNotificacao;
	
	@Setter
	protected Boolean deveNotificar;
	
	protected List<Notificacao> notificacoes;
	protected Veiculo veiculo;
	protected Recibo recibo;
	@Setter
	protected Tarifa tarifa;
	
	public void iniciar(Long minutosProximaNotificacao) {
		dataHoraInicio = LocalDateTime.now();
		dataHoraPrevisaoNotificacao = dataHoraInicio.plusHours(1).minusMinutes(minutosProximaNotificacao); 
	}

	public void encerrar() {
		LocalDateTime now = LocalDateTime.now();
		dataHoraTermino = now;
		dataHoraPrevisaoNotificacao = now; 
	}
	
	public abstract BigDecimal getValor();
	
	public void setStatusPagamento(StatusPagamento statusPagamento) {
		pagamento.setStatus(statusPagamento);
	}
	
	public void setValorPago() {
		pagamento.setValorPago(getValor().doubleValue());
	}

	public void criarSolicitacaoPagamento(String idSolicitacaoPagamento) {
		pagamento = Pagamento.builder()
				.idSolicitacaoPagamento(idSolicitacaoPagamento)
				.status(StatusPagamento.AGUARDANDO_PAGAMENTO).build();
	}
}
