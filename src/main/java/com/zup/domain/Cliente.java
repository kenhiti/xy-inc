package com.zup.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.zup.utils.MsgConstants;

@Entity
@Table(name="cliente")
public class Cliente {
	
	private Long idCliente;
	private String nome;
	private Date dataNascimento;
	private String cpf;
	private String endereco;
	
	public Cliente() {
		
	}	
	
	public Cliente(String nome, String cpf) {
		this.nome = nome;		
		this.cpf = cpf;		
	}

	public Cliente(Long idCliente, String nome, Date dataNascimento, String cpf, String endereco) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name="nome")
	@NotEmpty(message = MsgConstants.PROPRIEDADE_NOME_NAO_PODE_SER_NULO)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Column(name="cpf")
	@NotEmpty(message=MsgConstants.PROPRIEDADE_CPF_NAO_PODE_SER_NULO)
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(name="endereco")
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}
	

}
