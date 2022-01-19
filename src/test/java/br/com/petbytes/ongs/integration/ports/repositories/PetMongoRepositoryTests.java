package br.com.petbytes.ongs.integration.ports.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.petbytes.ongs.application.datamappers.OngMongoDataMapper;
import br.com.petbytes.ongs.application.datamappers.PetMongoDataMapper;
import br.com.petbytes.ongs.databuilders.OngDataBuilder;
import br.com.petbytes.ongs.databuilders.PetDataBuilder;
import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.domain.entities.Pet;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.TipoIdentificacao;
import br.com.petbytes.ongs.domain.valueobjects.pet.SexoPet;
import br.com.petbytes.ongs.domain.valueobjects.pet.TipoPet;
import br.com.petbytes.ongs.ports.repositories.OngMongoRepository;
import br.com.petbytes.ongs.ports.repositories.PetMongoRepository;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class PetMongoRepositoryTests {
	
	@Autowired 
	private OngMongoRepository ongRepository;
	
	@Autowired 
	private PetMongoRepository petRepository;
	
	private OngDataBuilder ongDataBuilder = new OngDataBuilder();
	private PetDataBuilder petDataBuilder = new PetDataBuilder();

	@Test
	@Order(1)
	@DisplayName("Given a ong with two pets, when findAllPetsByOngId is called, return two pets from that ong.")
	public void findAllPetsByOngId() {
		
		// GIVEN
		String numeroIdentificacao = "40062067044";
		
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, numeroIdentificacao);
		
		Ong ong = ongDataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		OngMongoDataMapper ongDataMapper = new OngMongoDataMapper(ong);
		
		ongDataMapper = ongRepository.save(ongDataMapper);
		
		Pet leo = petDataBuilder
				.withNome("Leo")
				.withTipoPet(TipoPet.GATO)
				.withSexoPet(SexoPet.MACHO)
				.withOngId(ongDataMapper.getId())
				.build();
		
		Pet pippo = petDataBuilder
				.withNome("Pippo")
				.withTipoPet(TipoPet.GATO)
				.withSexoPet(SexoPet.MACHO)
				.withOngId(ongDataMapper.getId())
				.build();
		
		PetMongoDataMapper leoPetDataMapper = new PetMongoDataMapper(leo);
		PetMongoDataMapper pippoPetDataMapper = new PetMongoDataMapper(pippo);
		
		petRepository.save(leoPetDataMapper);
		petRepository.save(pippoPetDataMapper);
		
		// WHEN
		List<PetMongoDataMapper> pets = petRepository.findAllByOngId(ongDataMapper.getId());

		// THEN
		assertThat(pets.size()).isEqualTo(2);
		
	}
	
}
