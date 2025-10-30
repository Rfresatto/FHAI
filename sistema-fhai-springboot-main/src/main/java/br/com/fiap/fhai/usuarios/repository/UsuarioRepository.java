package br.com.fiap.fhai.usuarios.repository;

import br.com.fiap.fhai.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
