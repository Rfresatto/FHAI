package br.com.fiap.fhai.transacao.repository;

import br.com.fiap.fhai.transacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

}
