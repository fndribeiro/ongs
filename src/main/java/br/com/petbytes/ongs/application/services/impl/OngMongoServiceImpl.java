package br.com.petbytes.ongs.application.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.petbytes.ongs.application.datamappers.OngMongoDataMapper;
import br.com.petbytes.ongs.application.dtos.OngDTO;
import br.com.petbytes.ongs.application.services.OngService;
import br.com.petbytes.ongs.domain.entities.Ong;
import br.com.petbytes.ongs.domain.valueobjects.identificacao.Identificacao;
import br.com.petbytes.ongs.ports.repositories.OngMongoRepository;
import io.micrometer.core.instrument.util.StringUtils;

@Service
public class OngMongoServiceImpl implements OngService {
	
	private OngMongoRepository repository;
	
	public OngMongoServiceImpl(OngMongoRepository repositorio) {
		this.repository = repositorio;
	}

	@Override
	public OngDTO createOng(Ong ongRequest) {
		
		var dataMapper = new OngMongoDataMapper(ongRequest);
		
		OngMongoDataMapper ong = repository.save(dataMapper);
		
		return new OngDTO(
				ong.getId(), 
				ong.getNome(), 
				ong.getEndereco());
	}

	@Override
	public OngDTO findOngById(String ongId) {
		
		Optional<OngMongoDataMapper> ong = repository.findById(ongId);
		
		if (ong.isEmpty()) {
			return null;
		}
		
		return new OngDTO(
				ong.get().getId(), 
				ong.get().getNome(), 
				ong.get().getEndereco());
	}
	
	@Override
	public OngDTO findOngByIdentificacao(Identificacao identificacao) {
		
		OngMongoDataMapper ong = repository.findByIdentificacao(identificacao);
		
		if (ong == null) {
			return null;
		}
		
		return new OngDTO(
				ong.getId(), 
				ong.getNome(), 
				ong.getEndereco());
	}

	@Override
	public OngDTO updateOng(String ongId, Ong ongRequest) {
		
		Optional<OngMongoDataMapper> ong = repository.findById(ongId);
		
		if (ong.isEmpty()) {
			return null;
		}
		
		ong = ong.map(o -> {
			
			if (StringUtils.isNotBlank(ongRequest.getNome())) {
				o.setNome(ongRequest.getNome());
			}
			
			if (ongRequest.getEndereco() != null) {
				o.setEndereco(ongRequest.getEndereco());
			}
			
			if (ongRequest.getIdentificacao() != null) {
				o.setIdentificacao(ongRequest.getIdentificacao());
			}
			
			return repository.save(o);
			
		});
		
		return new OngDTO(
				ong.get().getId(), 
				ong.get().getNome(), 
				ong.get().getEndereco());
	}

	@Override
	public void deleteOng(String ongId) {
		repository.deleteById(ongId);
	}
	
}
