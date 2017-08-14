package com.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
