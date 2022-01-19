package br.com.petbytes.ongs.ports.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.petbytes.ongs.application.datamappers.PetMongoDataMapper;

@Repository
public interface PetMongoRepository extends MongoRepository<PetMongoDataMapper, String> {
	
	List<PetMongoDataMapper> findAllByOngId(String ongId);

}
