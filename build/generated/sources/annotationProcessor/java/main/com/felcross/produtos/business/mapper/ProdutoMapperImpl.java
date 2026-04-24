package com.felcross.produtos.business.mapper;

import com.felcross.produtos.api.dto.ProdutoRequest;
import com.felcross.produtos.api.dto.ProdutoResponse;
import com.felcross.produtos.domain.entity.Produto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-17T21:53:32-0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.0.0.jar, environment: Java 21.0.10 (Ubuntu)"
)
@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public Produto toEntity(ProdutoRequest request) {
        if ( request == null ) {
            return null;
        }

        Produto.ProdutoBuilder produto = Produto.builder();

        produto.nome( request.getNome() );
        produto.descricao( request.getDescricao() );
        produto.preco( request.getPreco() );
        produto.estoque( request.getEstoque() );

        return produto.build();
    }

    @Override
    public ProdutoResponse toResponse(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        ProdutoResponse.ProdutoResponseBuilder produtoResponse = ProdutoResponse.builder();

        produtoResponse.id( produto.getId() );
        produtoResponse.nome( produto.getNome() );
        produtoResponse.descricao( produto.getDescricao() );
        produtoResponse.preco( produto.getPreco() );
        produtoResponse.estoque( produto.getEstoque() );
        produtoResponse.createdAt( produto.getCreatedAt() );

        return produtoResponse.build();
    }
}
