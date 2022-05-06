package br.com.fiap.rh.service.tributacao;

import java.math.BigDecimal;

public interface ReajusteTributavel extends Reajuste {// principio de segregacao de interfaces
	
	BigDecimal valorImpostoDeRenda();// principio de segregacao de interfaces
	
}
