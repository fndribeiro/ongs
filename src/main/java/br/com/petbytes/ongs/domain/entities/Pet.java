package br.com.petbytes.ongs.domain.entities;

import br.com.petbytes.ongs.domain.valueobjects.pet.SexoPet;
import br.com.petbytes.ongs.domain.valueobjects.pet.StatusAdocao;
import br.com.petbytes.ongs.domain.valueobjects.pet.TipoPet;

public class Pet {
	
	private String nome;
	private TipoPet tipoPet;
	private SexoPet sexoPet;
	private boolean castrado;
	private StatusAdocao statusAdocao;
	private String ongId;
	private String adotanteId;
	
	public Pet() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPet getTipoPet() {
		return tipoPet;
	}

	public void setTipoPet(TipoPet tipoPet) {
		this.tipoPet = tipoPet;
	}

	public SexoPet getSexoPet() {
		return sexoPet;
	}

	public void setSexoPet(SexoPet sexoPet) {
		this.sexoPet = sexoPet;
	}

	public boolean isCastrado() {
		return castrado;
	}

	public void setCastrado(boolean castrado) {
		this.castrado = castrado;
	}

	public StatusAdocao getStatusAdocao() {
		return statusAdocao;
	}

	public void setStatusAdocao(StatusAdocao statusAdocao) {
		this.statusAdocao = statusAdocao;
	}

	public String getOngId() {
		return ongId;
	}

	public void setOngId(String ongId) {
		this.ongId = ongId;
	}

	public String getAdotanteId() {
		return adotanteId;
	}

	public void setAdotanteId(String adotanteId) {
		this.adotanteId = adotanteId;
	}

}
