package br.com.study.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository
// @EntityScan
public interface ProdutoRepository {//extends JpaRepository<Produtos, Integer> {
    Produto findByCodigo(String codigo);
}