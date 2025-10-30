package br.com.fiap.fhai.contaBancaria.controller;

import br.com.fiap.fhai.contaBancaria.model.ContaBancaria;
import br.com.fiap.fhai.contaBancaria.service.ContaBancariaService;
import br.com.fiap.fhai.usuarios.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario/{idUsuario}/conta-bancaria")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar(
            @PathVariable int idUsuario,
            @RequestBody ContaBancaria conta) {
        return contaBancariaService.cadastrarConta(idUsuario, conta);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ContaBancaria buscar(@PathVariable int idUsuario) {
        return contaBancariaService.buscarConta(idUsuario);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@PathVariable int idUsuario, @RequestBody ContaBancaria conta) {
        return contaBancariaService.atualizarConta(idUsuario, conta);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable int idUsuario) {
        contaBancariaService.removerConta(idUsuario);
    }
}