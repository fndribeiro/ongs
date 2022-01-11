package br.com.petbytes.ongs.integration.ports.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.petbytes.ongs.databuilders.OngDataBuilder;
import br.com.petbytes.ongs.databuilders.PetDataBuilder;
import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.domain.entities.Pet;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.TipoIdentificacao;
import br.com.petbytes.ongs.domain.valueobjects.pet.SexoPet;
import br.com.petbytes.ongs.domain.valueobjects.pet.TipoPet;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTests {

	@Autowired
    private MockMvc mvc;
	
	private OngDataBuilder ongDataBuilder = new OngDataBuilder();
	private PetDataBuilder petDataBuilder = new PetDataBuilder();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@Order(1)
	@DisplayName("Given a new pet, when create is called, then pet is created.")
	public void createPet() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));

		Ong ong = ongDataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();

		String ongContent = mapper.writeValueAsString(ong);

		String ongId = mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(ongContent)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location")
			.replace("/ongs/", "");
		
		Pet pet = petDataBuilder
			.withNome("Leo")
			.withTipoPet(TipoPet.GATO)
			.withSexoPet(SexoPet.MACHO)
			.withOngId(ongId)
			.build();
		
		String petContent = mapper.writeValueAsString(pet);
		
		// WHEN
		mvc.perform(post("/pets")
			.contentType(MediaType.APPLICATION_JSON)
			.content(petContent)
			.accept(MediaType.APPLICATION_JSON))
		
		// THEN
			.andExpect(status().isCreated());
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Given a pet, when findById is called, then return pet.")
	public void findPetById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));

		Ong ong = ongDataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();

		String ongContent = mapper.writeValueAsString(ong);

		String ongId = mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(ongContent)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location")
			.replace("/ongs/", "");
		
		Pet pet = petDataBuilder
			.withNome("Pippo")
			.withTipoPet(TipoPet.GATO)
			.withSexoPet(SexoPet.MACHO)
			.withOngId(ongId)
			.build();
		
		String petContent = mapper.writeValueAsString(pet);
		
		String locationHeader = mvc.perform(post("/pets")
			.contentType(MediaType.APPLICATION_JSON)
			.content(petContent)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location");
		
		// WHEN
		mvc.perform(get(locationHeader)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
	
		// THEN
			.andExpect(status().isOk());
		
	}
	
	@Test
	@Order(3)
	@DisplayName("Given a pet, when updatePetById is called, then return pet with updated values.")
	public void updateOngById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));

		Ong ong = ongDataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();

		String ongContent = mapper.writeValueAsString(ong);

		String ongId = mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(ongContent)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location")
			.replace("/ongs/", "");
		
		Pet pet = petDataBuilder
			.withNome("Pippo")
			.withTipoPet(TipoPet.GATO)
			.withSexoPet(SexoPet.MACHO)
			.withOngId(ongId)
			.build();
		
		String petContent = mapper.writeValueAsString(pet);
		
		String locationHeader = mvc.perform(post("/pets")
			.contentType(MediaType.APPLICATION_JSON)
			.content(petContent)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location");
		
		// WHEN
		String newName = "Thor";
		
		pet.setNome(newName);
		
		String newContent = mapper.writeValueAsString(pet);
		
		String responseBody = mvc.perform(put(locationHeader)
			.contentType(MediaType.APPLICATION_JSON)
			.content(newContent)
			.accept(MediaType.APPLICATION_JSON))
	
		// THEN
			.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
				
		assertThat(responseBody).contains(newName);
		
	}
	
	@Test
	@Order(4)
	@DisplayName("Given a pet, when deletePetById is called, then delete pet.")
	public void deletePetById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));

		Ong ong = ongDataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();

		String ongContent = mapper.writeValueAsString(ong);

		String ongId = mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(ongContent)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location")
			.replace("/ongs/", "");
				
		Pet pet = petDataBuilder
			.withNome("Pippo")
			.withTipoPet(TipoPet.GATO)
			.withSexoPet(SexoPet.MACHO)
			.withOngId(ongId)
			.build();
				
		String petContent = mapper.writeValueAsString(pet);
				
		String locationHeader = mvc.perform(post("/pets")
			.contentType(MediaType.APPLICATION_JSON)
			.content(petContent)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location");
		
		// WHEN
		mvc.perform(delete(locationHeader)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
		
		// THEN
			.andExpect(status().isOk());
		
	}
	
}
