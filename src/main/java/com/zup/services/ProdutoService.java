package com.zup.services;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.zup.domain.Produto;
import com.zup.exceptions.BusinessException;
import com.zup.exceptions.ResourceNotFoundException;
import com.zup.repository.ProdutoRepository;
import com.zup.utils.MsgConstants;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Transactional
	public Produto save(Produto produto) {
		if (produto == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
		return repository.save(produto);
	}

	@Transactional
	public void update(Long id, Produto produto) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		if(produto == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
		
		if(!id.equals(produto.getIdProduto())){
			throw new BusinessException(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO);
		}
		
		findById(id);
		repository.save(produto);
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

	public List<Produto> findAll(String nome, BigDecimal valor, String descricao) {		
		return repository.findAll(Example.of(new Produto(nome, valor, descricao)));
	}

	public Produto findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		Produto produto = repository.findOne(id);

		if (produto == null) {
			throw new ResourceNotFoundException(MsgConstants.OBJETO_NAO_ENCONTRADO);
		}
		return produto;
	}
}
