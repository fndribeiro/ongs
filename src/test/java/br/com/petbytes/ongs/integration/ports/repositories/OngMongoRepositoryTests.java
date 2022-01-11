package br.com.petbytes.ongs.integration.ports.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.petbytes.ongs.application.datamappers.OngMongoDataMapper;
import br.com.petbytes.ongs.databuilders.OngDataBuilder;
import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.TipoIdentificacao;
import br.com.petbytes.ongs.ports.repositories.OngMongoRepository;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class OngMongoRepositoryTests {
	
	@Autowired 
	private OngMongoRepository repository;
	
	private OngDataBuilder dataBuilder = new OngDataBuilder();
	
	@Test
	@Order(1)
	@DisplayName("Given a Identificacao, when that Identificacao exists, return Ong.")
	public void findOngByIdentificacao() {
		
		// GIVEN
		String numeroIdentificacao = "40062067044";
		
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, numeroIdentificacao);
		
		Ong ong = dataBuilder
			.withNome("Gatinhos Fofinhos")
			.withEndereco()
			.withIdentificacao(identificacao)
			.build();
		
		OngMongoDataMapper ongDataMapper = new OngMongoDataMapper(ong);
		
		repository.save(ongDataMapper);
		
		// WHEN
		OngMongoDataMapper ongFound = repository.findByIdentificacao(identificacao);

		// THEN
		assertThat(ongDataMapper.getIdentificacao()).isEqualTo(ongFound.getIdentificacao());
		
		// FINALLY
		repository.delete(ongDataMapper);
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Given a Identificacao, when that Identificacao does not exist, return null.")
	public void findOngByIdentificacaoAndReturnNullWhenOngIsNotFound() {
		
		// GIVEN
		String numeroIdentificacao = "123456789";
		
		Identificacao identificacao = new Identificacao(TipoIdentificacao.CPF, numeroIdentificacao);
		
		// WHEN
		OngMongoDataMapper ongFound = repository.findByIdentificacao(identificacao);

		// THEN
		assertThat(ongFound).isNull();
		
	}

}
