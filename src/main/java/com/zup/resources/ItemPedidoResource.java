package com.zup.resources;

import java.net.URI;
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

import com.zup.domain.ItemPedido;
import com.zup.services.ItemPedidoService;

import io.swagger.annotations.Api;

/**
 * Classe de servicos de negocio relacionados a Itens de pedido
 * 
 * @author Francisco Fujimoto
 *
 */
@RestController
@RequestMapping("/itensPedido")
@Api(value = "Itens de pedido", tags = { "CRUD de Itens de pedido" })
public class ItemPedidoResource {
	
	@Autowired
	private ItemPedidoService service;
	
	/**
	 * Recupera uma entidade ItemPedido com base no identificador
	 * 
	 * @author Francisco Fujimoto
	 * @param id - Identificador da entidade
	 * @return Entidade ItemPedido recuperada
	 *
	 */	
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<ItemPedido> findById(@PathVariable("id") Long id){		
		return ResponseEntity.ok(service.findById(id));
	}
	
	/**
	 * Recupera todas as entidades ItemPedido
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param cdProduto
	
	 * @return Lista de entidades ItemPedido
	 *
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ItemPedido>> findAll(@RequestParam(value="cdProduto", required=false) Long cdProduto){		
		return ResponseEntity.ok(service.findAll(cdProduto));
	}
	
	/**
	 * Salva uma entidade ItemPedido informada
	 * 
	 * @author Francisco Fujimoto
	 * @param item - Objeto ItemPedido com os dados a serem persistidos.
	 *           
	 * @return URL do recurso criado
	 *
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody ItemPedido item){
		item = service.save(item);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getIdItemPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Atualiza a entidade ItemPedido informada
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param id - Identificador da entidade
	 * @param item - Objeto ItemPedido com os dados a serem persistidos.
	 *           
	 * @return void
	 *
	 */
	@CrossOrigin
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id")Long id, @Valid @RequestBody ItemPedido item){
		service.update(id,item);		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Exclui a entidade ItemPedido pelo identificador
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
