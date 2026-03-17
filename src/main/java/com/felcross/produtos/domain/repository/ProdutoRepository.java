package com.felcross.produtos.domain.repository;

import com.felcross.produtos.domain.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {}