package br.com.fiap.fhai.autenticacao.service;

import br.com.fiap.fhai.autenticacao.model.LoginRequest;
import br.com.fiap.fhai.autenticacao.model.UsuarioResponse;
import br.com.fiap.fhai.usuarios.model.Usuario;
import br.com.fiap.fhai.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse autenticar(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository
                .findByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos!"));

        // Converte Usuario para UsuarioResponse (sem senha)
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome()
        );
    }
}