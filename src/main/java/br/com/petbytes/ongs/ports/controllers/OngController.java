package br.com.petbytes.ongs.ports.controllers;

import java.net.URI;
import java.text.MessageFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petbytes.ongs.application.dtos.OngDTO;
import br.com.petbytes.ongs.application.services.OngService;
import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.ports.controllers.exceptions.NotFoundException;
import io.micrometer.core.instrument.util.StringUtils;

@RestController
@RequestMapping("/ongs")
public class OngController {
	
	private OngService service;
	
	private final String notFoundMessage = "Ong not for found for the given id.";
	
	public OngController(OngService service) {
		this.service = service;
	}

	@PostMapping
	ResponseEntity<String> createOng(@RequestBody Ong ongRequest) {
		
		if (StringUtils.isBlank(ongRequest.getNome())) {
			return ResponseEntity.badRequest().body("Name must not be null or empty.");
		}
		
		if (service.isEnderecoInvalido(ongRequest.getEndereco())) {
			return ResponseEntity.badRequest().body("Invalid adress. Please check if adress has logradouro, nome, numero, cep and cidade.");
		}
		
		if (ongRequest.getIdentificacao() == null) {
			return ResponseEntity.badRequest().body("Identificacao must not be null.");
		}
		
		OngDTO ongFoundByIdentificacao = service.findOngByIdentificacao(ongRequest.getIdentificacao());
		
		if (ongFoundByIdentificacao != null) {
			return ResponseEntity.badRequest().body("The given Identificacao is already registered to " + ongFoundByIdentificacao.getNome() + ".");
		};
		
		OngDTO ong = service.createOng(ongRequest);
		
		URI location = URI.create(MessageFormat.format("/ongs/{0}", ong.getId()));
			
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/{id}")
	ResponseEntity<OngDTO> findOngById(@PathVariable(value = "id") String id) {
		
		OngDTO ong = service.findOngById(id);
		
		if (ong == null) {
			throw new NotFoundException(notFoundMessage);
		}
		
		return ResponseEntity.ok(ong);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<OngDTO> updateOngById(@PathVariable(value = "id") String id, @RequestBody Ong ongRequest) {
		
		OngDTO ong = service.updateOng(id, ongRequest);
		
		if (ong == null) {
			throw new NotFoundException(notFoundMessage);
		}
		
		return ResponseEntity.ok(ong);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteOngById(@PathVariable(value = "id") String id) {
		
		service.deleteOng(id);
			
		return ResponseEntity.ok("Entity deleted successfully.");
			
	}
	
}
