package com.zup.dataBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zup.domain.Cliente;

public class ClienteTestDataBuilder {

	public Cliente build() {
        return new Cliente(1l, "nome", new Date(), "01442452641", "endereco");
    }
 
    public List<Cliente> buildList(){
    	List<Cliente> list = new ArrayList<>();
		list.add(new Cliente(1l, "nome", new Date(), "00000000000", "endereco"));
		return list;
    }
}
