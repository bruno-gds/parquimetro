package com.grupo16.sgpservice.gateway.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.grupo16.sgpservice.domain.Condutor;

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
public class CondutorDocument {

	@Id
	private String id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private EnderecoEntity endereco;
	
	public CondutorDocument(Condutor condutor) {
		id = condutor.getId();
		nome = condutor.getNome();
		cpf = condutor.getCpf();
		email = condutor.getEmail();
		telefone = condutor.getTelefone();
		endereco = condutor.getEndereco() == null ? null : new EnderecoEntity(condutor.getEndereco());
	}
	
	public Condutor parseCondutorDomain() {
		return Condutor.builder()
				.id(id)
				.nome(nome)
				.cpf(cpf)
				.email(email)
				.telefone(telefone)
				.endereco(endereco == null ? null : endereco.parseEnderecoDomain())
				.build();
	}
}
