package com.zup.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.zup.domain.Cliente;
import com.zup.domain.ItemPedido;
import com.zup.domain.Pedido;
import com.zup.domain.Status;
import com.zup.exceptions.BusinessException;
import com.zup.exceptions.ResourceNotFoundException;
import com.zup.repository.PedidoRepository;
import com.zup.utils.MsgConstants;
@Service
public class PedidoService {
	
	private static final Long ZERO = 0l;
	

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ItemPedidoService itemService;
	
	@Transactional
	public Pedido save(Pedido pedido) {
		if (pedido == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
		
		validarParaPersistencia(pedido);
		
		return repository.save(pedido);
	}

	

	@Transactional
	public void update(Long id, Pedido pedido) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		if(pedido == null) {
			throw new IllegalArgumentException(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO);
		}
		
		if(!id.equals(pedido.getIdPedido())){
			throw new BusinessException(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO);
		}
		
		findById(id);
		validarParaPersistencia(pedido);
		repository.save(pedido);
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

	public List<Pedido> findAll(Date dataPedido, Status status, Long cdCliente) {	
		
		Cliente cliente = null;
		
		if(cdCliente != null) {
			cliente = clienteService.findById(cdCliente);
		}
		
		return repository.findAll(Example.of(new Pedido(dataPedido, status, cliente)));
	}

	public Pedido findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO);
		}
		
		Pedido pedido = repository.findOne(id);

		if (pedido == null) {
			throw new ResourceNotFoundException(MsgConstants.OBJETO_NAO_ENCONTRADO);
		}
		return pedido;
	}

	private void validarParaPersistencia(Pedido pedido) {
		clienteService.findById(pedido.getCliente().getIdCliente());		
		
		List<ItemPedido> itensFiltrados = pedido.getItensPedido().stream().filter(p -> p.getQuantidade() == null || p.getQuantidade().equals(ZERO)).collect(Collectors.toList());
		
		if(CollectionUtils.isNotEmpty(itensFiltrados)) {
			throw new BusinessException(MsgConstants.PROPRIEDADE_QUANTIDADE_NAO_PODE_SER_NULO);
		}
		
		pedido.getItensPedido().forEach(item -> {			
			itemService.validarParaPersistencia(item);
		});
		
		Double x = pedido.getItensPedido().stream()
				.mapToDouble(p -> p.getProduto().getValor().doubleValue() * p.getQuantidade())
				.sum();
		
		BigDecimal totalizador = new BigDecimal(x);
		
		if(pedido.getValorTotal().compareTo(totalizador) != 0) {
			throw new BusinessException(MsgConstants.TOTAL_DOS_ITENS_DIVERGENTE);
		}
	}
}
