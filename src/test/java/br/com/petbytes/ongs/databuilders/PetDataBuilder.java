package br.com.petbytes.ongs.databuilders;

import br.com.petbytes.ongs.domain.entities.Pet;
import br.com.petbytes.ongs.domain.valueobjects.pet.SexoPet;
import br.com.petbytes.ongs.domain.valueobjects.pet.StatusAdocao;
import br.com.petbytes.ongs.domain.valueobjects.pet.TipoPet;

public class PetDataBuilder {
	
	private Pet pet = new Pet();
	
	public PetDataBuilder withNome(String nome) {
		
		pet.setNome(nome);
		
		return this;
	}
	
	public PetDataBuilder withTipoPet(TipoPet tipoPet) {
		
		pet.setTipoPet(tipoPet);
		
		return this;
	}
	
	public PetDataBuilder withSexoPet(SexoPet SexoPet) {
		
		pet.setSexoPet(SexoPet);
		
		return this;
	}
	
	public PetDataBuilder withCastrado(boolean isCastrado) {
		
		pet.setCastrado(isCastrado);
		
		return this;
	}
	
	public PetDataBuilder withStatusAdocao(StatusAdocao statusAdocao) {
		
		pet.setStatusAdocao(statusAdocao);
		
		return this;
	}
	
	public PetDataBuilder withOngId(String ongId) {
		
		pet.setOngId(ongId);
		
		return this;
	}
	
	public Pet build() {
		return pet;
	}

}
