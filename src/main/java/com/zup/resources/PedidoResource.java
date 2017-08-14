package com.zup.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zup.domain.Pedido;
import com.zup.domain.Status;
import com.zup.services.PedidoService;

import io.swagger.annotations.Api;

/**
 * Classe de servicos de negocio relacionados a Pedido
 * 
 * @author Francisco Fujimoto
 *
 */

@RestController
@RequestMapping("/pedidos")
@Api(value = "Pedidos", tags = { "CRUD de Pedido" })
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	/**
	 * Recupera uma entidade Pedido com base no identificador
	 * 
	 * @author Francisco Fujimoto
	 * @param id - Identificador da entidade
	 * @return Entidade Pedido recuperada
	 *
	 */	
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable("id") Long id){		
		return ResponseEntity.ok(service.findById(id));
	}
	
	/**
	 * Recupera todas as entidades Pedido
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param dataPedido
	 * @param status
	 * @param cdCliente
	 * 	
	 * @return Lista de entidades Pedido
	 *
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAll(@RequestParam(value="dataPedido", required=false) Date dataPedido, 
												@RequestParam(value="status", required=false) Status status, 
											    @RequestParam(value="cdCliente", required=false)Long cdCliente){		
		return ResponseEntity.ok(service.findAll(dataPedido, status, cdCliente));
	}
	
	/**
	 * Salva uma entidade Pedido informada
	 * 
	 * @author Francisco Fujimoto
	 * @param pedido - Objeto Pedido com os dados a serem persistidos.
	 *           
	 * @return URL do recurso criado
	 *
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Pedido pedido){
		pedido = service.save(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Atualiza a entidade Pedido informada
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param id - Identificador da entidade
	 * @param item - Objeto Pedido com os dados a serem persistidos.
	 *           
	 * @return void
	 *
	 */
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id")Long id, @Valid @RequestBody Pedido pedido){
		service.update(id, pedido);		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Exclui a entidade Pedido pelo identificador
	 * 
	 * @author Francisco Fujimoto
	 * @param id - Identificador do dado a ser excluido.
	 *           
	 * @return void
	 *
	 */
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}
}
