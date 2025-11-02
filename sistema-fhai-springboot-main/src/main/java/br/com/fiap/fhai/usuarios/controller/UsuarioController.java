package br.com.fiap.fhai.usuarios.controller;

import br.com.fiap.fhai.endereco.model.Endereco;
import br.com.fiap.fhai.usuarios.model.Usuario;
import br.com.fiap.fhai.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(
        origins = "http://localhost:3000",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowedHeaders = "*",
        allowCredentials = "true"
)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarPorId(@PathVariable int id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable int id) {
        usuarioService.excluir(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        return usuarioService.atualizar(id, usuario);
    }

    @PostMapping("/{id}/endereco")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarEndereco(@PathVariable int id, @RequestBody Endereco endereco){
        return  usuarioService.cadastrarEndereco(id, endereco);
    }

    @PutMapping("/{id}/endereco")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizarEndereco(@PathVariable int id, @RequestBody Endereco endereco) {
        return usuarioService.atualizarEndereco(id, endereco);
    }

    @GetMapping("/{id}/endereco")
    @ResponseStatus(HttpStatus.OK)
    public Endereco buscarEndereco(@PathVariable int id){
        return usuarioService.buscarEndereco(id);
    }
}

