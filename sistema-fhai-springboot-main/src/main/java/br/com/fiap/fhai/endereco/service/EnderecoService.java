package br.com.fiap.fhai.endereco.service;

import br.com.fiap.fhai.endereco.model.Endereco;
import br.com.fiap.fhai.endereco.repository.EnderecoRepository;
import br.com.fiap.fhai.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Endereco cadastrar(Endereco endereco, int idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(usuario -> {
                    endereco.setUsuario(usuario);
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public Endereco buscarPorId(int id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            return endereco.get();
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    public void excluir(int idUsuario, int idEndereco) {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .filter(e -> e.getUsuario().getId() == idUsuario)
                .orElseThrow(() -> new RuntimeException("Endereço não pertence ao usuário"));

        enderecoRepository.delete(endereco);
    }

    public Endereco atualizar(int idUsuario, int idEndereco, Endereco atualizado) {
        return enderecoRepository.findById(idEndereco)
                .filter(e -> e.getUsuario().getId() == idUsuario)
                .map(endereco -> {
                    endereco.setLogradouro(atualizado.getLogradouro());
                    endereco.setNr_residencia(atualizado.getNr_residencia());
                    endereco.setComplemento(atualizado.getComplemento());
                    endereco.setCep(atualizado.getCep());
                    endereco.setCidade(atualizado.getCidade());
                    endereco.setEstado(atualizado.getEstado());
                    endereco.setReferencia(atualizado.getReferencia());
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() ->
                        new RuntimeException("Endereço não encontrado para este usuário."));
    }

    public List<Endereco> buscarTodosPorUsuario(int idUsuario) {
        return enderecoRepository.findByUsuarioId(idUsuario);
    }
}
