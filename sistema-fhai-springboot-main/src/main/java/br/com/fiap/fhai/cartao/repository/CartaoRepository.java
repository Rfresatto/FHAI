package br.com.fiap.fhai.cartao.repository;

import br.com.fiap.fhai.cartao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

}