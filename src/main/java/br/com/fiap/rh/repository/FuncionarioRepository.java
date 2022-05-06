package br.com.fiap.rh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.rh.model.Funcionario;
@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {


}
