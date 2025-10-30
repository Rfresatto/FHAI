package br.com.fiap.fhai.usuarios.service;

import br.com.fiap.fhai.endereco.model.Endereco;
import br.com.fiap.fhai.usuarios.model.Usuario;
import br.com.fiap.fhai.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public void excluir(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioAtual = usuario.get();
            usuarioAtual.setEndereco(null);
            usuarioAtual.setContaBancaria(null);
            usuarioRepository.save(usuarioAtual);
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public Usuario atualizar(int id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
        if (usuarioAtual.isPresent()) {
            Usuario usuario = usuarioAtual.get();

            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setSexo(usuarioAtualizado.getSexo());
            usuario.setContato(usuarioAtualizado.getContato());

            if (usuarioAtualizado.getEndereco() != null) {
                usuario.setEndereco(usuarioAtualizado.getEndereco());
            }

            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }

    public Usuario cadastrarEndereco(int idUsuario, Endereco endereco){
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(idUsuario);
        if (usuarioAtual.isPresent()){
            Usuario usuario = usuarioAtual.get();
            usuario.setEndereco(endereco);
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }

    public Usuario atualizarEndereco(int idUsuario, Endereco enderecoAtualizado) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Endereco enderecoExistente = usuario.getEndereco();

        if (enderecoExistente == null) {
            usuario.setEndereco(enderecoAtualizado);
        } else {
            enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
            enderecoExistente.setNr_residencia(enderecoAtualizado.getNr_residencia());
            enderecoExistente.setCidade(enderecoAtualizado.getCidade());
            enderecoExistente.setEstado(enderecoAtualizado.getEstado());
            enderecoExistente.setCep(enderecoAtualizado.getCep());
            enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
            enderecoExistente.setReferencia(enderecoAtualizado.getReferencia());
        }

        return usuarioRepository.save(usuario);
    }

    public Endereco buscarEndereco(int idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            Endereco endereco = usuario.get().getEndereco();
            if (endereco != null) {
                return endereco;
            } else {
                throw new RuntimeException("Usuário não possui endereço cadastrado!");
            }
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }
}