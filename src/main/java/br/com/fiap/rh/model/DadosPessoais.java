package br.com.fiap.rh.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class DadosPessoais { //LISKOV 
	private String nome;
	private String cpf;
	private Cargo cargo;
	private BigDecimal salario;
}
