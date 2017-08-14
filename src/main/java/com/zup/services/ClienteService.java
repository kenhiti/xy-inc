package com.zup.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.zup.domain.Cliente;
import com.zup.exceptions.BusinessException;
import com.zup.exceptions.ResourceNotFoundException;
import com.zup.repository.ClienteRepository;
import com.zup.utils.CNP;
import com.zup.utils.MsgConstants;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Transactional
	public Cliente save(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
			
		validarCPF(cliente.getCpf());
		return repository.save(cliente);
	}

	@Transactional
	public void update(Long id, Cliente cliente) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		if(cliente == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
		
		if(!id.equals(cliente.getIdCliente())){
			throw new BusinessException(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO);
		}
		
		findById(id);
		
		validarCPF(cliente.getCpf());
		repository.save(cliente);
	}

	@Transactional
	public void delete(Long id) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}

		try {
			repository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MsgConstants.OBJETO_NAO_ENCONTRADO);
		}

	}

	public List<Cliente> findAll(String nome, String CPF) {	
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
		
		return repository.findAll(Example.of(new Cliente(nome, CPF), matcher));
	}

	public Cliente findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		Cliente cliente = repository.findOne(id);

		if (cliente == null) {
			throw new ResourceNotFoundException(MsgConstants.OBJETO_NAO_ENCONTRADO);
		}
		return cliente;
	}
	
	private void validarCPF(String cpf) {
		if(!CNP.isValidCPF(cpf)) {
			throw new BusinessException(MsgConstants.CPF_INVALIDO);
		}
	}
}
