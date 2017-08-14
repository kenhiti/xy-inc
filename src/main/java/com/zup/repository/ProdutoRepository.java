package com.zup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
