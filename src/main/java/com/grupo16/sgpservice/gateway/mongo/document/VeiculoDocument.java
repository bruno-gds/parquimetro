package com.grupo16.sgpservice.gateway.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grupo16.sgpservice.domain.Veiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@Document
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDocument {
	
	@Id
	private String id;
	private String marca;
	private String modelo;
	private String placa;
	
	public VeiculoDocument(Veiculo veiculo) {
		id = veiculo.getId();
		marca = veiculo.getMarca();
		modelo = veiculo.getModelo();
		placa = veiculo.getPlaca();
	}
	
	public Veiculo parseVeiculoDomain() {
		return Veiculo.builder()
				.id(id)
				.marca(marca)
				.modelo(modelo)
				.placa(placa)
				.build();
	}
}
