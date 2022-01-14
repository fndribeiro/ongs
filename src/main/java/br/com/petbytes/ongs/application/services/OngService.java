package br.com.petbytes.ongs.application.services;

import br.com.petbytes.ongs.application.dtos.OngDTO;
import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.domain.valueobjects.endereco.Endereco;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;
import io.micrometer.core.instrument.util.StringUtils;

public interface OngService {

	public OngDTO createOng(Ong ongRequest);
	
	public OngDTO findOngById(String ongId);
	
	public OngDTO findOngByIdentificacao(Identificacao identificacao);
	
	public OngDTO updateOng(String ongId, Ong ongRequest);
	
	public void deleteOng(String ongId);
	
	default boolean isInvalidEndereco(Endereco endereco) {
		return endereco == null
				|| endereco.getLogradouro() == null
				|| StringUtils.isBlank(endereco.getNome())
				|| StringUtils.isBlank(endereco.getNumero())
				|| StringUtils.isBlank(endereco.getCep())
				|| endereco.getCidade() == null;
	}
	
}
