package com.felcross.produtos.business.service;

import com.felcross.produtos.api.dto.*;
import com.felcross.produtos.business.mapper.ProdutoMapper;
import com.felcross.produtos.domain.entity.Produto;
import com.felcross.produtos.domain.repository.ProdutoRepository;
import com.felcross.produtos.infrastructure.exception.BusinessException;
import com.felcross.produtos.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoResponse criar(ProdutoRequest req) {
        return mapper.toResponse(repository.save(mapper.toEntity(req)));
    }

    public ProdutoResponse buscarPorId(String id) {
        return mapper.toResponse(findOrThrow(id));
    }

    public List<ProdutoResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public ProdutoResponse atualizar(String id, ProdutoRequest req) {
        Produto p = findOrThrow(id);
        p.setNome(req.getNome());
        p.setDescricao(req.getDescricao());
        p.setPreco(req.getPreco());
        p.setEstoque(req.getEstoque());
        return mapper.toResponse(repository.save(p));
    }

    public void atualizarEstoque(String id, int quantidade) {
        Produto p = findOrThrow(id);
        if (p.getEstoque() < quantidade)
            throw new BusinessException("Estoque insuficiente para o produto: " + p.getNome());        p.setEstoque(p.getEstoque() - quantidade);
        repository.save(p);
    }

    public void deletar(String id) { repository.delete(findOrThrow(id)); }

    private Produto findOrThrow(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));    }
}
