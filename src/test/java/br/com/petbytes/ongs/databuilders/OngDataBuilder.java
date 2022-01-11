package br.com.petbytes.ongs.databuilders;

import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.domain.valueobjects.endereco.Cidade;
import br.com.petbytes.ongs.domain.valueobjects.endereco.Endereco;
import br.com.petbytes.ongs.domain.valueobjects.endereco.Estado;
import br.com.petbytes.ongs.domain.valueobjects.endereco.Logradouro;
import br.com.petbytes.ongs.domain.valueobjects.endereco.Pais;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;

public class OngDataBuilder {

	private Ong ong = new Ong();
	
	public OngDataBuilder withNome(String nome) {
		
		ong.setNome(nome);
		
		return this;
	}
	
	public OngDataBuilder withEndereco() {
		
		Pais pais = new Pais("Brasil");
		
		Estado estado = new Estado("Sao Paulo", "SP", pais);
		
		Cidade cidade = new Cidade("Sao Paulo", estado);
		
		String nomeRua = "Avenida Reboucas";
		String numero = "321";
		String complemento = "APTO 102030";
		String cep = "04555006";
		
		Endereco endereco = new Endereco(Logradouro.RUA, nomeRua, numero, complemento, cep, cidade);
		
		ong.setEndereco(endereco);
		
		return this;
	}
	
	public OngDataBuilder withIdentificacao(Identificacao identificacao) {
		
		ong.setIdentificacao(identificacao);
		
		return this;
	}
	
	public Ong build() {
		return ong;
	}
	
}
