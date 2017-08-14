package com.zup.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.zup.utils.MsgConstants;

@Entity
@Table(name="produto")
public class Produto {

	private Long idProduto;
	private String nome;
	private BigDecimal valor;
	private String descricao;
	private Categoria Categoria;
	private Long quantidade;
	
	public Produto() {
		super();
	}
	
	public Produto(String nome, BigDecimal valor, String descricao) {		
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
	}

	public Produto(Long idProduto, String nome, BigDecimal valor, String descricao, com.zup.domain.Categoria categoria,
			Long quantidade) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		Categoria = categoria;
		this.quantidade = quantidade;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="valor")
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Column(name="descricao")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name="quantidade")
	@NotNull(message=MsgConstants.PROPRIEDADE_QUANTIDADE_NAO_PODE_SER_NULO)
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	@Enumerated(EnumType.STRING)
	public Categoria getCategoria() {
		return Categoria;
	}	

	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

}
