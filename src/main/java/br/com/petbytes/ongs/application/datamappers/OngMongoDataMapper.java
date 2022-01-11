package br.com.petbytes.ongs.application.datamappers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.petbytes.ongs.domain.entities.Ong;

@Document("ongs")
public class OngMongoDataMapper extends Ong {
	
	@Id
	private String id;
	
	public OngMongoDataMapper() {}

	public OngMongoDataMapper(Ong ong) {
		setNome(ong.getNome());
		setEndereco(ong.getEndereco());
		setIdentificacao(ong.getIdentificacao());
	}

	public String getId() {
		return id;
	}
	
}
