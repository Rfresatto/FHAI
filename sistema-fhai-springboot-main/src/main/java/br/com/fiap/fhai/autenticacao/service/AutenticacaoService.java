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

    @Autowired
    private JwtUtil jwtUtil;

    public UsuarioResponse autenticar(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository
                .findByEmailAndSenha(loginRequest.getEmail(), loginRequest.getSenha())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos!"));

        String token = jwtUtil.gerarToken(usuario.getId(), usuario.getEmail(), usuario.getNome());

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                token
        );
    }

    public boolean validarToken(String token) {
        try {
            String email = jwtUtil.extrairEmail(token);
            return !jwtUtil.tokenExpirado(token);
        } catch (Exception e) {
            return false;
        }
    }

    public UsuarioResponse obterUsuarioPorToken(String token) {
        try {
            Integer userId = jwtUtil.extrairUserId(token);
            Usuario usuario = usuarioRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

            return new UsuarioResponse(
                    usuario.getId(),
                    usuario.getNome(),
                    token
            );
        } catch (Exception e) {
            throw new RuntimeException("Token inválido!");
        }
    }
}