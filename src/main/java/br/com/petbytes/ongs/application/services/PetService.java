package br.com.petbytes.ongs.application.services;

import br.com.petbytes.ongs.application.dtos.PetDTO;
import br.com.petbytes.ongs.domain.entities.Pet;

public interface PetService {
	
	public PetDTO createPet(Pet petRequest);
	
	public PetDTO findPetById(String petId);
	
	public PetDTO updatePet(String petId, Pet petRequest);
	
	public void deletePet(String petId);

}
