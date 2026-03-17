package com.felcross.produtos.api.controller;

import com.felcross.produtos.api.dto.*;
import com.felcross.produtos.business.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos")
@SecurityRequirement(name = "bearerAuth")
public class ProdutoController {
    private final ProdutoService service;

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar produto")
    public ProdutoResponse criar(@Valid @RequestBody ProdutoRequest req) { return service.criar(req); }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public ProdutoResponse buscar(@PathVariable String id) { return service.buscarPorId(id); }

    @GetMapping
    @Operation(summary = "Listar produtos")
    public List<ProdutoResponse> listar() { return service.listar(); }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto")
    public ProdutoResponse atualizar(@PathVariable String id, @Valid @RequestBody ProdutoRequest req) {
        return service.atualizar(id, req);
    }

    @PatchMapping("/{id}/estoque/{quantidade}")
    @Operation(summary = "Decrementar estoque")
    public void atualizarEstoque(@PathVariable String id, @PathVariable int quantidade) {
        service.atualizarEstoque(id, quantidade);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar produto")
    public void deletar(@PathVariable String id) { service.deletar(id); }
}
