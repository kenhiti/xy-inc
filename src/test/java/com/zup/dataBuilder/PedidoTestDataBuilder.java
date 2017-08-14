package com.zup.dataBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zup.domain.Pedido;
import com.zup.domain.Status;

public class PedidoTestDataBuilder {
	
	public Pedido build() {
        return new Pedido(1l, new Date(), Status.EM_ANDAMENTO, new ClienteTestDataBuilder().build(),new ItemPedidoTestDataBuilder().buildList(), BigDecimal.TEN);
    }
 
    public List<Pedido> buildList(){
    	List<Pedido> list = new ArrayList<>();
		list.add(new Pedido(1l, new Date(), Status.EM_ANDAMENTO, new ClienteTestDataBuilder().build(),new ItemPedidoTestDataBuilder().buildList(), BigDecimal.TEN));
		return list;
    }
}
