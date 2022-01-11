package br.com.petbytes.ongs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.petbytes.ongs.ports.controllers.OngController;
import br.com.petbytes.ongs.ports.controllers.PetController;

@SpringBootTest
class PetbytesOngsApplicationTests {
	
	@Autowired
	private OngController ongController;
	
	@Autowired
	private PetController petController;

	@Test
	void contextLoads() {
		
		assertThat(ongController).isNotNull();
		
		assertThat(petController).isNotNull();
		
	}

}
