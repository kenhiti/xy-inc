package com.zup.dataBuilder;

import java.util.ArrayList;
import java.util.List;
import com.zup.domain.ItemPedido;

public class ItemPedidoTestDataBuilder {
	
	public ItemPedido build() {
        return new ItemPedido(1l, new ProdutoTestDataBuilder().build(), 1l);
    }
 
    public List<ItemPedido> buildList(){
    	List<ItemPedido> list = new ArrayList<>();
		list.add(new ItemPedido(1l, new ProdutoTestDataBuilder().build(), 1l));
		return list;
    }
}
