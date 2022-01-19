package br.com.petbytes.ongs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PetbytesOngsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetbytesOngsApplication.class, args);
	}

}
