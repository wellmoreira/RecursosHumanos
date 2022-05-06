package br.com.fiap.rh.service.tributacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Reajuste {// principio de segregacao de interfaces
	
	BigDecimal valor();
	LocalDate data();

}
