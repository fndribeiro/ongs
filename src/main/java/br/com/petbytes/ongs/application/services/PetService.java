package br.com.petbytes.ongs.application.services;

import java.util.List;

import br.com.petbytes.ongs.application.dtos.PetDTO;
import br.com.petbytes.ongs.domain.entities.Pet;

public interface PetService {
	
	public PetDTO createPet(Pet petRequest);
	
	public PetDTO findPetById(String petId);
	
	public List<PetDTO> findAllPetsByOngId(String ongId);
	
	public PetDTO updatePet(String petId, Pet petRequest);
	
	public void deletePet(String petId);

}
