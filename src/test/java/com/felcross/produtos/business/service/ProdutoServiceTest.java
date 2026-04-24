package com.felcross.produtos.business.service;

import com.felcross.produtos.api.dto.ProdutoRequest;
import com.felcross.produtos.api.dto.ProdutoResponse;
import com.felcross.produtos.business.mapper.ProdutoMapper;
import com.felcross.produtos.domain.entity.Produto;
import com.felcross.produtos.domain.repository.ProdutoRepository;
import com.felcross.produtos.infrastructure.exception.BusinessException;
import com.felcross.produtos.infrastructure.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoMapper mapper;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    @DisplayName("Deve criar um produto com sucesso")
    void criarComSucesso() {
        ProdutoRequest req = ProdutoRequest.builder()
                .nome("Teclado Mecânico").preco(new BigDecimal("350.00")).estoque(10).build();

        when(mapper.toEntity(any())).thenReturn(new Produto());
        when(repository.save(any())).thenReturn(new Produto());
        when(mapper.toResponse(any())).thenReturn(ProdutoResponse.builder().id("p1").build());

        ProdutoResponse res = produtoService.criar(req);

        assertNotNull(res.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar ID inexistente")
    void erroBuscaId() {
        when(repository.findById("invalido")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> produtoService.buscarPorId("invalido"));
    }

    @Test
    @DisplayName("Deve atualizar estoque corretamente")
    void atualizarEstoqueSucesso() {
        // GIVEN
        String id = "p1";
        Produto produto = Produto.builder().id(id).nome("Mouse").estoque(10).build();
        when(repository.findById(id)).thenReturn(Optional.of(produto));

        // WHEN
        produtoService.atualizarEstoque(id, 4);

        // THEN
        assertEquals(6, produto.getEstoque());
        verify(repository).save(produto);
    }

    @Test
    @DisplayName("Deve lançar BusinessException quando estoque for menor que o solicitado")
    void erroEstoqueInsuficiente() {
        // GIVEN
        String id = "p1";
        Produto produto = Produto.builder().id(id).nome("Mouse").estoque(2).build();
        when(repository.findById(id)).thenReturn(Optional.of(produto));

        // WHEN & THEN
        BusinessException ex = assertThrows(BusinessException.class, () -> produtoService.atualizarEstoque(id, 5));
        assertTrue(ex.getMessage().contains("Estoque insuficiente"));
        verify(repository, never()).save(any());
    }
}