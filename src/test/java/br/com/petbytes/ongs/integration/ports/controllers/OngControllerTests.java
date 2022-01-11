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
import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.TipoIdentificacao;

@SpringBootTest
@AutoConfigureMockMvc
public class OngControllerTests {
	
	@Autowired
    private MockMvc mvc;
	
	private OngDataBuilder dataBuilder = new OngDataBuilder();;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@Order(1)
	@DisplayName("Given a new ong, when create is called, then ong is created.")
	public void createOng() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Ong ong = dataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(ong);
		
		// WHEN
		mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
			.accept(MediaType.APPLICATION_JSON))
		
		// THEN
			.andExpect(status().isCreated());
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Given a ong, when findById is called, then return ong.")
	public void findOngById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Ong ong = dataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(ong);
		
		String locationHeader = mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
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
	@DisplayName("Given a ong, when updateOngById is called, then return ong with updated values.")
	public void updateOngById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Ong ong = dataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(ong);
		
		String locationHeader = mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
			.accept(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse()
			.getHeader("Location");
		
		String newName = "Bigodinhos";
		
		ong.setNome(newName);
		
		String newContent = mapper.writeValueAsString(ong);
		
		// WHEN
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
	@DisplayName("Given a ong, when deleteOngById is called, then delete ong.")
	public void deleteOngById() throws Exception {
		
		// GIVEN
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, RandomStringUtils.randomAlphanumeric(11));
		
		Ong ong = dataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		String content = mapper.writeValueAsString(ong);
		
		String locationHeader = mvc.perform(post("/ongs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
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
