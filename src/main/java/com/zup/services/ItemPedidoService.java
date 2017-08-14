package com.zup.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.zup.domain.ItemPedido;
import com.zup.domain.Produto;
import com.zup.exceptions.BusinessException;
import com.zup.exceptions.ResourceNotFoundException;
import com.zup.repository.ItemPedidoRepository;
import com.zup.utils.MsgConstants;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository repository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Transactional
	public ItemPedido save(ItemPedido item) {
		if (item == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
		
		validarParaPersistencia(item);
		
		return repository.save(item);
	}

	

	@Transactional
	public void update(Long id, ItemPedido item) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		if(item == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
		
		if(!id.equals(item.getIdItemPedido())){
			throw new BusinessException(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO);
		}
		
		//verifica  se o item é existente. Caso inexistente lança exceção.
		findById(id);
		
		validarParaPersistencia(item);
		
		repository.save(item);
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

	public List<ItemPedido> findAll(Long cdProduto) {	
		Produto produto = null;
		
		if(cdProduto != null) {
			produto = produtoService.findById(cdProduto);
		}
		return repository.findAll(Example.of(new ItemPedido(produto)));
	}

	public ItemPedido findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		ItemPedido item = repository.findOne(id);

		if (item == null) {
			throw new ResourceNotFoundException(MsgConstants.OBJETO_NAO_ENCONTRADO);
		}
		return item;
	}

	public void validarParaPersistencia(ItemPedido item) {
		//verifica  se o produto do item é existente. Caso inexistente lança exceção.
		Produto produto = produtoService.findById(item.getProduto().getIdProduto());
		
		//verifica  se o produto selecionado possui quantidade em estoque. Caso negativo lança exceção.
		if(produto.getQuantidade().compareTo(item.getQuantidade()) < 0 ) {
			throw new BusinessException(MsgConstants.PRODUTO_SEM_ESTOQUE);
		}
	}
}
