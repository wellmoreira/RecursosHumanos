package br.com.fiap.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.rh.model.Funcionario;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {


}
