package br.com.petbytes.ongs.domain.entities;

import br.com.petbytes.ongs.domain.valueobjects.endereco.Endereco;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;

public class Ong {
	
	private String nome;
	private Endereco endereco;
	private Identificacao identificacao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Identificacao getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Identificacao identificacao) {
		this.identificacao = identificacao;
	}
	
}
