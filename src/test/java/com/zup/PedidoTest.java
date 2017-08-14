package com.zup;

import static org.mockito.Matchers.any;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.zup.dataBuilder.ClienteTestDataBuilder;
import com.zup.dataBuilder.PedidoTestDataBuilder;
import com.zup.dataBuilder.ProdutoTestDataBuilder;
import com.zup.domain.Pedido;
import com.zup.repository.ClienteRepository;
import com.zup.repository.PedidoRepository;
import com.zup.repository.ProdutoRepository;
import com.zup.services.PedidoService;
import com.zup.utils.MsgConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoTest {
	

	@TestConfiguration
    static class pedidoTestContextConfiguration {
  
        @Bean
        public PedidoService pedidoService() {
            return new PedidoService();
        }
    }
	
	@Autowired
	private PedidoService service;
	
	@MockBean
	private PedidoRepository repository;
	
	@MockBean
	private ClienteRepository clienteRepository;
	
	@MockBean
	private ProdutoRepository produtoRepository;
	
	@Mock
	private Pedido mockObj;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void savePedido_IllegalArgumentExceptionTest() {		
		try {
			service.save(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void savePedido_nonNullObjectTest() {	
		Pedido response = null;		
		try {
			Mockito.when(clienteRepository.findOne(Matchers.anyLong())).thenReturn(new ClienteTestDataBuilder().build());
			Mockito.when(produtoRepository.findOne(Matchers.anyLong())).thenReturn(new ProdutoTestDataBuilder().build());
			Mockito.when(repository.save(any(Pedido.class))).thenReturn(new PedidoTestDataBuilder().build());
			response = service.save(new PedidoTestDataBuilder().build());
		} catch (Exception e) {
			System.out.println("Erro");
		}
		Assert.assertNotNull(response);		
	}
	
	@Test
	public void updatePedido_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(null,mockObj);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void updatePedido_objetoNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(1l,null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void updatePedido_divergencia_BusinessExceptionTest() {		
		try {
			service.update(2l,new PedidoTestDataBuilder().build());
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO));
		}
	}
	
	@Test
	public void updatePedido_nonNullObjectTest() {	
		Boolean control = false;		
		try {
			Mockito.when(clienteRepository.findOne(Matchers.anyLong())).thenReturn(new ClienteTestDataBuilder().build());
			Mockito.when(produtoRepository.findOne(Matchers.anyLong())).thenReturn(new ProdutoTestDataBuilder().build());
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new PedidoTestDataBuilder().build());
			Mockito.when(repository.save(any(Pedido.class))).thenReturn(new PedidoTestDataBuilder().build());
			service.update(1l, new PedidoTestDataBuilder().build());
			control = true;
		} catch (Exception e) {
		}
		Assert.assertTrue(control);
	}
	
	@Test
	public void deletePedido_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.delete(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void deletePedido_nonNullTest() {	
		Boolean control = false;		
		try {
			Mockito.doNothing().when(repository).delete(Matchers.anyLong());
			service.delete(1l);
			control = true;
		} catch (Exception e) {
		}
		Assert.assertTrue(control);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void findall_Test() {	
		List<Pedido> response = null;		
		try {
			Mockito.when(repository.findAll(Matchers.any(Example.class))).thenReturn(new PedidoTestDataBuilder().buildList());
			response = service.findAll(null, null, null);			
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}
	
	@Test
	public void findByIdPedido_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.findById(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void findByIdPedido_Test() {
		Pedido response = null;
		try {
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new PedidoTestDataBuilder().build());
			response = service.findById(1l);
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}
}
