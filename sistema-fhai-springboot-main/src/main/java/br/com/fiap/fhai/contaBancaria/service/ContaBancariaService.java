package br.com.fiap.fhai.contaBancaria.service;

import br.com.fiap.fhai.contaBancaria.model.ContaBancaria;
import br.com.fiap.fhai.contaBancaria.repository.ContaBancariaRepository;
import br.com.fiap.fhai.usuarios.model.Usuario;
import br.com.fiap.fhai.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarConta(int idUsuario, ContaBancaria conta) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(idUsuario);
        if (usuarioAtual.isPresent()) {
            Usuario usuario = usuarioAtual.get();

            if (usuario.getContaBancaria() != null) {
                throw new RuntimeException("Usuário já possui uma conta cadastrada!");
            }

            ContaBancaria contaSalva = contaBancariaRepository.save(conta);

            usuario.setContaBancaria(contaSalva);

            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public ContaBancaria buscarConta(int idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            ContaBancaria conta = usuario.get().getContaBancaria();
            if (conta != null) {
                return conta;
            } else {
                throw new RuntimeException("Usuário não possui conta cadastrada!");
            }
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public Usuario atualizarConta(int idUsuario, ContaBancaria contaAtualizada) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(idUsuario);
        if (usuarioAtual.isPresent()) {
            Usuario usuario = usuarioAtual.get();

            if (usuario.getContaBancaria() != null) {
                contaAtualizada.setId(usuario.getContaBancaria().getId());
            }

            ContaBancaria contaSalva = contaBancariaRepository.save(contaAtualizada);

            usuario.setContaBancaria(contaSalva);

            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public Usuario removerConta(int idUsuario) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(idUsuario);
        if (usuarioAtual.isPresent()) {
            Usuario usuario = usuarioAtual.get();
            usuario.setContaBancaria(null);
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}