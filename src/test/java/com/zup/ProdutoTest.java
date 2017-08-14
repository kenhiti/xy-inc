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

import com.zup.dataBuilder.ProdutoTestDataBuilder;
import com.zup.domain.Produto;
import com.zup.repository.ProdutoRepository;
import com.zup.services.ProdutoService;
import com.zup.utils.MsgConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoTest {

	@TestConfiguration
    static class produtoTestContextConfiguration {
  
        @Bean
        public ProdutoService produtoService() {
            return new ProdutoService();
        }
    }
	
	@Autowired
	private ProdutoService service;
	
	@MockBean
	private ProdutoRepository repository;
	
	@Mock
	private Produto mockObj;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveProduto_IllegalArgumentExceptionTest() {		
		try {
			service.save(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void saveProduto_nonNullObjectTest() {	
		Produto response = null;		
		try {
			Mockito.when(repository.save(any(Produto.class))).thenReturn(new ProdutoTestDataBuilder().build());
			response = service.save(mockObj);
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);		
	}
	
	@Test
	public void updateProduto_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(null,mockObj);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void updateProduto_objetoNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(1l,null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void updateProduto_divergencia_BusinessExceptionTest() {		
		try {
			service.update(2l,new ProdutoTestDataBuilder().build());
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO));
		}
	}
	
	@Test
	public void updateProduto_nonNullObjectTest() {	
		Boolean control = false;		
		try {
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new ProdutoTestDataBuilder().build());
			Mockito.when(repository.save(any(Produto.class))).thenReturn(new ProdutoTestDataBuilder().build());
			service.update(1l, new ProdutoTestDataBuilder().build());
			control = true;
		} catch (Exception e) {
		}
		Assert.assertTrue(control);
	}
	
	@Test
	public void deleteProduto_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.delete(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void deleteProduto_nonNullTest() {	
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
		List<Produto> response = null;		
		try {
			Mockito.when(repository.findAll(Matchers.any(Example.class))).thenReturn(new ProdutoTestDataBuilder().buildList());
			response = service.findAll(null, null,null);			
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}
	
	@Test
	public void findByIdProduto_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.findById(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void findByIdProduto_Test() {
		Produto response = null;
		try {
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new ProdutoTestDataBuilder().build());
			response = service.findById(1l);
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}

	
}
