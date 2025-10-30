package br.com.fiap.fhai.contaBancaria.repository;

import br.com.fiap.fhai.contaBancaria.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

}
