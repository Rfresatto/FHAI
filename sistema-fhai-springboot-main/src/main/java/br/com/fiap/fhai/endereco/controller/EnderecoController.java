package br.com.fiap.fhai.endereco.controller;

import br.com.fiap.fhai.endereco.model.Endereco;
import br.com.fiap.fhai.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario/{idUsuario}/endereco")
@CrossOrigin(
        origins = "http://localhost:3000",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*",
        allowCredentials = "true"
)
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco cadastrar(
            @PathVariable int idUsuario,
            @RequestBody Endereco endereco
    ) {
        return enderecoService.cadastrar(endereco, idUsuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Endereco> buscarTodos(@PathVariable int idUsuario) {
        return enderecoService.buscarTodosPorUsuario(idUsuario);
    }

    @GetMapping("/{idEndereco}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco buscarPorId(@PathVariable int idEndereco) {
        return enderecoService.buscarPorId(idEndereco);
    }

    @PutMapping("/{idEndereco}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco atualizar(
            @PathVariable int idUsuario,
            @PathVariable int idEndereco,
            @RequestBody Endereco endereco
    ) {
        return enderecoService.atualizar(idUsuario, idEndereco, endereco);
    }

    @DeleteMapping("/{idEndereco}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable int idUsuario,
            @PathVariable int idEndereco
    ) {
        enderecoService.excluir(idUsuario, idEndereco);
    }

}
