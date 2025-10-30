package br.com.fiap.fhai.cartao.controller;

import br.com.fiap.fhai.cartao.model.Cartao;
import br.com.fiap.fhai.cartao.service.CartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cartao cadastrar(@RequestBody Cartao cartao) {
        return cartaoService.cadastrar(cartao);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cartao> buscarTodos() {
        return cartaoService.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cartao buscarPorId(@PathVariable int id) {
        return cartaoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable int id) {
        cartaoService.excluir(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cartao atualizar(@PathVariable int id, @RequestBody Cartao cartao) {
        return cartaoService.atualizar(id, cartao);
    }
}