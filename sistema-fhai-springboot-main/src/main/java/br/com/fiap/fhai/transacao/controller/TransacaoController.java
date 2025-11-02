package br.com.fiap.fhai.transacao.controller;

import br.com.fiap.fhai.transacao.model.Transacao;
import br.com.fiap.fhai.transacao.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacao")
@CrossOrigin(
        origins = "http://localhost:3000",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*",
        allowCredentials = "true"
)
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao cadastrar(@RequestBody Transacao transacao){
        return  transacaoService.cadastrar(transacao);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transacao> buscarTodos(){
        return transacaoService.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transacao buscarPorId(@PathVariable int id){
        return transacaoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable int id ){
        transacaoService.excluir(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transacao atualizar (@PathVariable int id, @RequestBody Transacao transacao){
        return transacaoService.atualizar(id, transacao);
    }
}
