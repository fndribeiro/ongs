package br.com.petbytes.ongs.ports.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.petbytes.ongs.application.datamappers.OngMongoDataMapper;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;

@Repository
public interface OngMongoRepository extends MongoRepository<OngMongoDataMapper, String> {
	
	OngMongoDataMapper findByIdentificacao(Identificacao identificacao);
	
}
