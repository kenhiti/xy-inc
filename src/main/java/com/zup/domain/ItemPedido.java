package com.zup.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.zup.utils.MsgConstants;

@Entity
@Table(name="item_pedido")
public class ItemPedido {
	
	private Long idItemPedido;
	private Produto produto;
	private Long quantidade;
	
	public ItemPedido() {
		
	}
	
	public ItemPedido(Produto produto) {
		this.produto = produto;
	}

	public ItemPedido(Long idItemPedido, Produto produto, Long quantidade) {
		this.idItemPedido = idItemPedido;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_item_pedido")
	public Long getIdItemPedido() {
		return idItemPedido;
	}
	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	@NotNull(message=MsgConstants.PROPRIEDADE_PRODUTO_NAO_PODE_SER_NULO)
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Column(name="quantidade")
	@Min(value = 1l, message=MsgConstants.ERRO_QUANTIDADE_MINIMA)
	@NotNull(message=MsgConstants.PROPRIEDADE_QUANTIDADE_NAO_PODE_SER_NULO)
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItemPedido == null) ? 0 : idItemPedido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (idItemPedido == null) {
			if (other.idItemPedido != null)
				return false;
		} else if (!idItemPedido.equals(other.idItemPedido))
			return false;
		return true;
	}
}
