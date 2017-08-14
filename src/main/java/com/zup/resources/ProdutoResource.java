package com.zup.resources;

import java.math.BigDecimal;
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

import com.zup.domain.Produto;
import com.zup.services.ProdutoService;

import io.swagger.annotations.Api;

/**
 * Classe de servicos de negocio relacionados a Produto
 * 
 * @author Francisco Fujimoto
 *
 */
@RestController
@RequestMapping("/produtos")
@Api(value = "Produto", tags = { "CRUD de Produto" })
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	/**
	 * Recupera uma entidade Produto com base no identificador
	 * 
	 * @author Francisco Fujimoto
	 * @param id - Identificador da entidade
	 * @return Entidade Produto recuperada
	 *
	 */
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> findById(@PathVariable("id") Long id){		
		return ResponseEntity.ok(service.findById(id));
	}
	
	/**
	 * Recupera todas as entidades Produto
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param nome
	 * @param valor
	 * @param descricao
	 *
	 * @return Lista de entidades Produto
	 *
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll(@RequestParam(value="nome", required=false)String nome, 
			                                     @RequestParam(value="valor", required=false)BigDecimal valor, 
			                                     @RequestParam(value="descricao", required=false)String descricao){		
		return ResponseEntity.ok(service.findAll(nome, valor, descricao));
	}
	
	/**
	 * Salva uma entidade Produto informada
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param produto - Objeto Produto com os dados a serem persistidos.
	 *           
	 * @return URL do recurso criado
	 *
	 */	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Produto produto){
		produto = service.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getIdProduto()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Atualiza a entidade Produto informada
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param id - Identificador da entidade
	 * @param produto - Objeto Produto com os dados a serem persistidos.
	 *           
	 * @return void
	 *
	 */
	@CrossOrigin
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id")Long id,@Valid @RequestBody Produto produto){
		service.update(id, produto);		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Exclui a entidade Produto pelo identificador
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
