package br.com.petbytes.ongs.application.datamappers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.petbytes.ongs.domain.entities.Pet;

@Document("pets")
public class PetMongoDataMapper extends Pet{
	
	@Id
	private String id;
	
	public PetMongoDataMapper() {}

	public PetMongoDataMapper(Pet pet) {
		setNome(pet.getNome());
		setTipoPet(pet.getTipoPet());
		setSexoPet(pet.getSexoPet());
		setCastrado(pet.isCastrado());
		setStatusAdocao(pet.getStatusAdocao());
		setOngId(pet.getOngId());
		setAdotanteId(pet.getAdotanteId());
	}
	
	public String getId() {
		return id;
	}
	
}
