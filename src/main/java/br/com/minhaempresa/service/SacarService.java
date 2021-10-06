package br.com.minhaempresa.service;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;
import br.com.minhaempresa.exception.SaldoInsuficienteException;

public class SacarService {

    public void sacar(Conta conta, double valor) throws SaldoInsuficienteException {
        conta.sacar(valor);
    }

}
