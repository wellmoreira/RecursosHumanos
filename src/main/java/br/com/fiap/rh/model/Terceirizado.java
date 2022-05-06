package br.com.fiap.rh.model;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Entity
@ToString
public class Terceirizado {//parece funcionario tem gosto de funcionario mas nao é
	
	
	// nao usamos heranca pois alguns dados de funcionario nao fazem sentido estarem na classe terceirizado ex: promocao e reajuste 
	// utilizamos composição em ambas classes
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded 
	DadosPessoais dadosPessoais;// utilizacao de composição e nao heranca (Liskov)
	private String empresa;

	public Terceirizado(String nome, String cpf, Cargo cargo, BigDecimal salario, String empresa) {
		this.dadosPessoais = new DadosPessoais( nome, cpf, cargo, salario);
		this.empresa = empresa;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNome() {
		return dadosPessoais.getNome();
	}

	public String getCpf() {
		return dadosPessoais.getCpf();
	}

	public Cargo getCargo() {
		return dadosPessoais.getCargo();
	}

	public BigDecimal getSalario() {
		return dadosPessoais.getSalario();
	}

}
