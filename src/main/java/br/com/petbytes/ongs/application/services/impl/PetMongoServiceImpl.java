package br.com.petbytes.ongs.application.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petbytes.ongs.application.datamappers.OngMongoDataMapper;
import br.com.petbytes.ongs.application.datamappers.PetMongoDataMapper;
import br.com.petbytes.ongs.application.dtos.PetDTO;
import br.com.petbytes.ongs.application.services.PetService;
import br.com.petbytes.ongs.domain.entities.Pet;
import br.com.petbytes.ongs.domain.valueobjects.pet.StatusAdocao;
import br.com.petbytes.ongs.ports.repositories.OngMongoRepository;
import br.com.petbytes.ongs.ports.repositories.PetMongoRepository;
import io.micrometer.core.instrument.util.StringUtils;

@Service
public class PetMongoServiceImpl implements PetService {
	
	private PetMongoRepository petRepository;
	private OngMongoRepository ongRepository;
	
	public PetMongoServiceImpl(PetMongoRepository petRepository, OngMongoRepository ongRepository) {
		this.petRepository = petRepository;
		this.ongRepository = ongRepository;
	}

	@Override
	public PetDTO createPet(Pet petRequest) {
		
		Optional<OngMongoDataMapper> ong = ongRepository.findById(petRequest.getOngId());
		
		if (ong.isEmpty()) {
			return null;
		}
		
		var dataMapper = new PetMongoDataMapper(petRequest);
		
		if (dataMapper.getStatusAdocao() == null) {
			dataMapper.setStatusAdocao(StatusAdocao.RESGATADO);
		}
		
		PetMongoDataMapper pet = petRepository.save(dataMapper);
		
		return new PetDTO(
				pet.getId(), 
				pet.getNome(), 
				pet.getTipoPet(), 
				pet.getSexoPet(), 
				pet.isCastrado(), 
				pet.getStatusAdocao(), 
				pet.getOngId(), 
				pet.getAdotanteId());
	}

	@Override
	public PetDTO findPetById(String petId) {
		
		Optional<PetMongoDataMapper> pet = petRepository.findById(petId);
		
		if (pet.isEmpty()) {
			return null;
		}
		
		var petDto = new PetDTO(
				pet.get().getId(), 
				pet.get().getNome(), 
				pet.get().getTipoPet(), 
				pet.get().getSexoPet(), 
				pet.get().isCastrado(), 
				pet.get().getStatusAdocao(), 
				pet.get().getOngId(), 
				pet.get().getAdotanteId());
		
		return petDto;
	}

	@Override
	public PetDTO updatePet(String petId, Pet petRequest) {
		
		Optional<PetMongoDataMapper> pet = petRepository.findById(petId);
		
		if (pet.isEmpty()) {
			return null;
		}
		
		pet = pet.map(p -> {
			
			if (StringUtils.isNotBlank(petRequest.getNome())) {
				p.setNome(petRequest.getNome());
			}
			
			if (StringUtils.isNotBlank(petRequest.getOngId())) {
				p.setOngId(petRequest.getOngId());
			}
			
			if (StringUtils.isNotBlank(petRequest.getAdotanteId())) {
				p.setAdotanteId(petRequest.getAdotanteId());
			}
			
			if (petRequest.getTipoPet() != null) {
				p.setTipoPet(petRequest.getTipoPet());
			}
			
			if (petRequest.getSexoPet() != null) {
				p.setSexoPet(petRequest.getSexoPet());
			}
			
			if (petRequest.getStatusAdocao() != null) {
				p.setStatusAdocao(petRequest.getStatusAdocao());
			}
			
			p.setCastrado(petRequest.isCastrado());
			
			return petRepository.save(p);
			
		});
		
		return new PetDTO(
				pet.get().getId(), 
				pet.get().getNome(), 
				pet.get().getTipoPet(), 
				pet.get().getSexoPet(), 
				pet.get().isCastrado(), 
				pet.get().getStatusAdocao(), 
				pet.get().getOngId(), 
				pet.get().getAdotanteId());
	}

	@Override
	public void deletePet(String petId) {
		petRepository.deleteById(petId);
	}

}
