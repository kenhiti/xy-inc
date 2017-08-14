package com.zup.dataBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.zup.domain.Categoria;
import com.zup.domain.Produto;

public class ProdutoTestDataBuilder {

	public Produto build() {
        return new Produto(1l, "nome", BigDecimal.TEN, "descricao", Categoria.ACESSORIOS, 1l);
    }
 
    public List<Produto> buildList(){
    	List<Produto> list = new ArrayList<>();
		list.add(new Produto(1l, "nome", BigDecimal.TEN, "descricao", Categoria.ACESSORIOS, 1l));
		return list;
    }
}
