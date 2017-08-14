package com.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
