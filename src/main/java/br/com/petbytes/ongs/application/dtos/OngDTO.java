package br.com.petbytes.ongs.application.dtos;

import br.com.petbytes.ongs.domain.valueobjects.endereco.Endereco;

public class OngDTO {
	
	private String id;
	private String nome;
	private Endereco endereco;
	
	public OngDTO(String id, String nome, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}

	public String getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

}
