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

import com.zup.dataBuilder.ItemPedidoTestDataBuilder;
import com.zup.dataBuilder.ProdutoTestDataBuilder;
import com.zup.domain.ItemPedido;
import com.zup.repository.ItemPedidoRepository;
import com.zup.repository.ProdutoRepository;
import com.zup.services.ItemPedidoService;
import com.zup.utils.MsgConstants;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemPedidoTest {
	
	@TestConfiguration
    static class itemTestContextConfiguration {
  
        @Bean
        public ItemPedidoService itemPedidoService() {
            return new ItemPedidoService();
        }
    }
	
	@Autowired
	private ItemPedidoService service;
	
	@MockBean
	private ItemPedidoRepository repository;
	
	@MockBean
	private ProdutoRepository produtoRepository;
	
	@Mock
	private ItemPedido mockObj;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveItemPedido_IllegalArgumentExceptionTest() {		
		try {
			service.save(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void saveItemPedido_nonNullObjectTest() {	
		ItemPedido response = null;		
		try {
			Mockito.when(produtoRepository.findOne(Matchers.anyLong())).thenReturn(new ProdutoTestDataBuilder().build());
			Mockito.when(repository.save(any(ItemPedido.class))).thenReturn(new ItemPedidoTestDataBuilder().build());
			response = service.save(new ItemPedidoTestDataBuilder().build());
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);		
	}
	
	@Test
	public void updateItemPedido_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(null,mockObj);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void updateItemPedido_objetoNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(1l,null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void updateItemPedido_divergencia_BusinessExceptionTest() {		
		try {
			service.update(2l,new ItemPedidoTestDataBuilder().build());
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO));
		}
	}
	
	@Test
	public void updateItemPedido_nonNullObjectTest() {	
		Boolean control = false;		
		try {
			Mockito.when(produtoRepository.findOne(Matchers.anyLong())).thenReturn(new ProdutoTestDataBuilder().build());
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new ItemPedidoTestDataBuilder().build());
			Mockito.when(repository.save(any(ItemPedido.class))).thenReturn(new ItemPedidoTestDataBuilder().build());
			service.update(1l, new ItemPedidoTestDataBuilder().build());
			control = true;
		} catch (Exception e) {
		}
		Assert.assertTrue(control);
	}
	
	@Test
	public void deleteItemPedido_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.delete(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void deleteItemPedido_nonNullTest() {	
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
		List<ItemPedido> response = null;		
		try {
			Mockito.when(repository.findAll(Matchers.any(Example.class))).thenReturn(new ItemPedidoTestDataBuilder().buildList());
			response = service.findAll(null);			
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}
	
	@Test
	public void findByIdItemPedido_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.findById(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void findByIdItemPedido_Test() {
		ItemPedido response = null;
		try {
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new ItemPedidoTestDataBuilder().build());
			response = service.findById(1l);
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}

}
