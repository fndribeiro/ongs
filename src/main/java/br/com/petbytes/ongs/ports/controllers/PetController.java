package br.com.petbytes.ongs.ports.controllers;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petbytes.ongs.application.dtos.PetDTO;
import br.com.petbytes.ongs.application.services.PetService;
import br.com.petbytes.ongs.domain.entities.Pet;
import br.com.petbytes.ongs.ports.controllers.exceptions.NotFoundException;
import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/pets")
public class PetController {
	
	private PetService service;
	
	private static final String NOT_FOUND_MESSAGE = "Pet not for found for the given id.";
	
	private final Logger logger = LoggerFactory.getLogger(PetController.class);
	
	public PetController(PetService service) {
		this.service = service;
	}
	
	@PostMapping
	ResponseEntity<String> createPet(@RequestBody Pet petRequest) {
		
		if (StringUtils.isBlank(petRequest.getNome())) {
			return ResponseEntity.badRequest().body("Name must not be null or empty.");
		}
		
		if (petRequest.getTipoPet() == null) {
			return ResponseEntity.badRequest().body("TipoPet must not be null.");
		}
		
		if (petRequest.getSexoPet() == null) {
			return ResponseEntity.badRequest().body("SexoPet must not be null.");
		}
		
		if (StringUtils.isBlank(petRequest.getOngId())) {
			return ResponseEntity.badRequest().body("OngId must not be null or empty.");
		}
		
		PetDTO pet = service.createPet(petRequest);
		
		if (pet == null) {
			throw new NotFoundException("Ong not found for the given ongId.");
		}
		
		URI location = URI.create(MessageFormat.format("/pets/{0}", pet.getId()));
			
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/{id}")
	ResponseEntity<PetDTO> findPetById(@PathVariable(value = "id") String id) {
		
		PetDTO pet = service.findPetById(id);
		
		if (pet == null) {
			throw new NotFoundException(NOT_FOUND_MESSAGE);
		}
		
		return ResponseEntity.ok(pet);
	}
	
	@GetMapping("/byong/{id}")
	ResponseEntity<List<PetDTO>> findAllPetsByOngId(@PathVariable(value = "id") String id) {
		
		logger.info("findAllPetsByOngId method started.");
		
		List<PetDTO> pets = service.findAllPetsByOngId(id);
		
		logger.info("Size of pets list: " + pets.size() + ". OngID: " + id);
		
		return ResponseEntity.ok(pets);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<PetDTO> updatePetById(@PathVariable(value = "id") String id, @RequestBody Pet petRequest) {
		
		PetDTO pet = service.updatePet(id, petRequest);
		
		if (pet == null) {
			throw new NotFoundException(NOT_FOUND_MESSAGE);
		}
		
		return ResponseEntity.ok(pet);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<String> deletePetById(@PathVariable(value = "id") String id) {
		
		service.deletePet(id);
			
		return ResponseEntity.ok("Entity deleted successfully.");
			
	}
	
}
