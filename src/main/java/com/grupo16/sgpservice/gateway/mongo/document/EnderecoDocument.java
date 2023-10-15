package com.grupo16.sgpservice.gateway.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grupo16.sgpservice.domain.Condutor;
import com.grupo16.sgpservice.domain.Endereco;
import com.grupo16.sgpservice.domain.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDocument {

	@Id
	private String id;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	@DBRef
	private Condutor condutor;
	
	public EnderecoDocument(Endereco endereco) {
		id = endereco.getId();
		rua = endereco.getRua();
		numero = endereco.getNumero();
		bairro = endereco.getBairro();
		cidade = endereco.getCidade();
		estado = endereco.getEstado().toString();
		cep = endereco.getCep();	
		condutor = Condutor.builder()
				.id(endereco.getCondutor().getId())
				.build();
	}
	
	public Endereco parseDomain() {
		return Endereco.builder()
				.id(id)
				.rua(rua)
				.numero(numero)
				.bairro(bairro)
				.cidade(cidade)
				.estado(Estado.valueOf(estado))
				.cep(cep)
				.build();
	}
	
}
