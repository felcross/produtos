package com.felcross.produtos.business.mapper;

import com.felcross.produtos.api.dto.ProdutoRequest;
import com.felcross.produtos.api.dto.ProdutoResponse;
import com.felcross.produtos.domain.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Produto toEntity(ProdutoRequest request);

    ProdutoResponse toResponse(Produto produto);
}