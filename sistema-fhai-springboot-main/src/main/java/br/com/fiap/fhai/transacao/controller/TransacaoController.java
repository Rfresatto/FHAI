package br.com.fiap.fhai.transacao.controller;

import br.com.fiap.fhai.transacao.model.Transacao;
import br.com.fiap.fhai.transacao.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario/{idUsuario}/transacoes")
@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        allowCredentials = "true"
)
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao cadastrar(
            @PathVariable Long idUsuario,
            @RequestBody Transacao transacao
    ) {
        return transacaoService.cadastrar(idUsuario, transacao);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transacao> buscarTodos(@PathVariable Long idUsuario) {
        return transacaoService.buscarTodosPorUsuario(idUsuario);
    }

    @GetMapping("/{idTransacao}")
    @ResponseStatus(HttpStatus.OK)
    public Transacao buscarPorId(
            @PathVariable Long idUsuario,
            @PathVariable Long idTransacao
    ) {
        return transacaoService.buscarPorId(idUsuario, idTransacao);
    }

    @PutMapping("/{idTransacao}")
    @ResponseStatus(HttpStatus.OK)
    public Transacao atualizar(
            @PathVariable Long idUsuario,
            @PathVariable Long idTransacao,
            @RequestBody Transacao transacao
    ) {
        return transacaoService.atualizar(idUsuario, idTransacao, transacao);
    }

    @DeleteMapping("/{idTransacao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable Long idUsuario,
            @PathVariable Long idTransacao
    ) {
        transacaoService.excluir(idUsuario, idTransacao);
    }
}