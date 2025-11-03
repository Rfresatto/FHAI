package br.com.fiap.fhai.transacao.repository;

import br.com.fiap.fhai.transacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    List<Transacao> findByUsuario_Id(int idUsuario);  // Mudei de usuarioId para usuario_Id
    Optional<Transacao> findByIdAndUsuario_Id(int idTransacao, int idUsuario);  // Mesma mudança aqui
}