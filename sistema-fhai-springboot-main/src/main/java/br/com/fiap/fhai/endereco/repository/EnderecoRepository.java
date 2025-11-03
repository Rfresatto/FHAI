package br.com.fiap.fhai.endereco.repository;

import br.com.fiap.fhai.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByUsuarioId(int idUsuario);
}
