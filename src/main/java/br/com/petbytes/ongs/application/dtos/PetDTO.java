package br.com.petbytes.ongs.application.dtos;

import br.com.petbytes.ongs.domain.valueobjects.pet.SexoPet;
import br.com.petbytes.ongs.domain.valueobjects.pet.StatusAdocao;
import br.com.petbytes.ongs.domain.valueobjects.pet.TipoPet;

public class PetDTO {
	
	private String id;
	private String nome;
	private TipoPet tipoPet;
	private SexoPet sexoPet;
	private boolean castrado;
	private StatusAdocao statusAdocao;
	private String ongId;
	private String adotanteId;
	
	public PetDTO(String id, String nome, TipoPet tipoPet, SexoPet sexoPet, boolean castrado, StatusAdocao statusAdocao, String ongId, String adotanteId) {
		this.id = id;
		this.nome = nome;
		this.tipoPet = tipoPet;
		this.sexoPet = sexoPet;
		this.castrado = castrado;
		this.statusAdocao = statusAdocao;
		this.ongId = ongId;
		this.adotanteId = adotanteId;
	}

	public String getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public TipoPet getTipoPet() {
		return tipoPet;
	}
	
	public SexoPet getSexoPet() {
		return sexoPet;
	}
	
	public boolean isCastrado() {
		return castrado;
	}
	
	public StatusAdocao getStatusAdocao() {
		return statusAdocao;
	}
	
	public String getOngId() {
		return ongId;
	}
	
	public String getAdotanteId() {
		return adotanteId;
	}
	
}
