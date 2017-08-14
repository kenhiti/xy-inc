package com.zup.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.zup.utils.MsgConstants;

@Entity
@Table(name="pedido")
public class Pedido {
	
	private Long idPedido;
	private Date dataPedido;
	private Status status;
	private Cliente cliente;
	private List<ItemPedido> itensPedido;
	private BigDecimal valorTotal;
	
	public Pedido() {		
		
	}	
	
	public Pedido(Date dataPedido, Status status, Cliente cliente) {
		this.dataPedido = dataPedido;
		this.status = status;
		this.cliente = cliente;
	}

	public Pedido(Long idPedido, Date dataPedido, Status status, Cliente cliente, List<ItemPedido> itensPedido,
			BigDecimal valorTotal) {
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.status = status;
		this.cliente = cliente;
		this.itensPedido = itensPedido;
		this.valorTotal = valorTotal;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_pedido")
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	
	@Column(name="data_pedido")
	@NotNull(message=MsgConstants.DATA_PEDIDO_NAO_PODE_SER_NULO)
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = MsgConstants.STATUS_NAO_PODE_SER_NULO)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	@NotNull(message = MsgConstants.CLIENTE_NAO_PODE_SER_NULO)
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@OneToMany
	@JoinColumn(name="itens_pedido")
	@NotNull(message = MsgConstants.ITENS_NAO_PODE_SER_NULO)
	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	
	@Column(name="valor_total")
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
