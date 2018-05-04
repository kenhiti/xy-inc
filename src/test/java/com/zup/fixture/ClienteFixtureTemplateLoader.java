package com.zup.fixture;

import java.text.SimpleDateFormat;

import com.zup.domain.Cliente;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ClienteFixtureTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Cliente.class).addTemplate("valid", new Rule() {{			
			add("idCliente",random(Long.class, range(1l,200l)));
			add("nome", firstName());
			add("dataNascimento", beforeDate("2000-05-01", new SimpleDateFormat("yyyy-MM-dd")));
			add("cpf", random("65584441198","20344425398"));
			add("endereco", random("Rua Um, 1", "Avendida Dois, 1"));
		}});
		
		Fixture.of(Cliente.class).addTemplate("invalid", new Rule() {{			
			add("idCliente",random(Long.class, new Object[] {null}));
			add("nome", firstName());
			add("dataNascimento", afterDate("2000-05-01", new SimpleDateFormat("yyyy-MM-dd")));
			add("cpf", random("11111111111"));
			add("endereco", random("Rua Um, 1", "Avendida Dois, 1"));
		}});	
	}

}
