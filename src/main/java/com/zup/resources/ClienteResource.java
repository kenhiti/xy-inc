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

import com.zup.domain.Cliente;
import com.zup.services.ClienteService;

import io.swagger.annotations.Api;

/**
 * Classe de servicos de negocio relacionados a Cliente
 * 
 * @author Francisco Fujimoto
 *
 */
@RestController
@RequestMapping("/clientes")
@Api(value = "Cliente", tags = { "CRUD de Cliente" })
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	
	/**
	 * Recupera uma entidade Cliente com base no identificador
	 * 
	 * @author Francisco Fujimoto
	 * @param id - Identificador da entidade
	 * @return Entidade Cliente recuperada
	 *
	 */
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable("id") Long id){		
		return ResponseEntity.ok(service.findById(id));
	}
	
	/**
	 * Recupera todas as entidades Cliente
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param nome
	 * @param dataNascimento
	 * @param CPF
	 * @param endereco
	
	 * @return Lista de entidades Cliente
	 *
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll(@RequestParam(value="nome", required=false) String nome,												
												 @RequestParam(value="CPF", required=false) String CPF){		
		return ResponseEntity.ok(service.findAll(nome, CPF));
	}
	
	/**
	 * Salva uma entidade Cliente informada
	 * 
	 * @author Francisco Fujimoto
	 * @param cliente - Objeto Cliente com os dados a serem persistidos.
	 *           
	 * @return URL do recurso criado
	 *
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Cliente cliente){
		cliente = service.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Atualiza a entidade Cliente informada
	 * 
	 * @author Francisco Fujimoto
	 * 
	 * @param id - Identificador da entidade
	 * @param cliente - Objeto Cliente com os dados a serem persistidos.
	 *           
	 * @return void
	 *
	 */
	@CrossOrigin
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id")Long id,@Valid @RequestBody Cliente cliente){
		service.update(id, cliente);		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Exclui a entidade Cliente pelo identificador
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
