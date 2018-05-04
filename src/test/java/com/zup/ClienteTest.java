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
import com.zup.domain.Cliente;
import com.zup.repository.ClienteRepository;
import com.zup.services.ClienteService;
import com.zup.utils.MsgConstants;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTest {
	
	@TestConfiguration
    static class clienteTestContextConfiguration {
  
        @Bean
        public ClienteService clienteService() {
            return new ClienteService();
        }
    }
	
	@Autowired
	private ClienteService service;
	
	@MockBean
	private ClienteRepository repository;
	
	@Mock
	private Cliente mockObj;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		FixtureFactoryLoader.loadTemplates("com.zup.fixture");
	}
	
	@Test
	public void saveCliente_IllegalArgumentExceptionTest() {		
		try {
			service.save(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void saveCliente_cpfInvalido_BusinessExceptionTest() {		
		
		try {
			service.save(mockObj);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.CPF_INVALIDO));
		}
	}
	
	@Test
	public void saveCliente_nonNullObjectTest() {	
		Cliente response = null;		
		try {
			//Mockito.when(repository.save(any(Cliente.class))).thenReturn(new ClienteTestDataBuilder().build());
			Mockito.when(repository.save(any(Cliente.class))).thenReturn(Fixture.from(Cliente.class).gimme("valid"));
			
			response = service.save(new Cliente(Matchers.anyString(), "01442452641"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);		
	}
	
	@Test
	public void updateCliente_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(null,mockObj);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void updateCliente_objetoNull_IllegalArgumentExceptionTest() {		
		try {
			service.update(1l,null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.OBJETO_OBRIGATORIO_NAO_INFORMADO));
		}
	}
	
	@Test
	public void updateCliente_divergencia_BusinessExceptionTest() {		
		try {
			//service.update(2l,new ClienteTestDataBuilder().build());
			
			service.update(2l,Fixture.from(Cliente.class).gimme("valid"));
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO));
		}
	}
	
	@Test
	public void updateCliente_nonNullObjectTest() {	
		Boolean control = false;		
		try {
			/*Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new ClienteTestDataBuilder().build());
			Mockito.when(repository.save(any(Cliente.class))).thenReturn(new ClienteTestDataBuilder().build());
			service.update(1l, new ClienteTestDataBuilder().build());*/
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(Fixture.from(Cliente.class).gimme("valid"));
			Mockito.when(repository.save(any(Cliente.class))).thenReturn(Fixture.from(Cliente.class).gimme("valid"));
			service.update(1l, Fixture.from(Cliente.class).gimme("valid"));
			control = true;
		} catch (Exception e) {
		}
		Assert.assertTrue(control);
	}
	
	@Test
	public void deleteCliente_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.delete(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void deleteCliente_nonNullTest() {	
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
		List<Cliente> response = null;		
		try {
			//Mockito.when(repository.findAll(Matchers.any(Example.class))).thenReturn(new ClienteTestDataBuilder().buildList());
			Mockito.when(repository.findAll(Matchers.any(Example.class))).thenReturn(Fixture.from(Cliente.class).gimme(5, "valid"));
			response = service.findAll(null, null);			
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}
	
	@Test
	public void findByIdCliente_idNull_IllegalArgumentExceptionTest() {		
		try {
			service.findById(null);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals(MsgConstants.IDENTIFICADOR_NAO_PODE_SER_NULO));
		}
	}
	
	@Test
	public void findByIdCliente_Test() {
		Cliente response = null;
		try {
			//Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(new ClienteTestDataBuilder().build());
			Mockito.when(repository.findOne(Matchers.anyLong())).thenReturn(Fixture.from(Cliente.class).gimme("valid"));
			response = service.findById(1l);
		} catch (Exception e) {
		}
		Assert.assertNotNull(response);
	}
}
