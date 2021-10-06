package br.com.minhaempresa.service;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;

public class DepositarService {
    public void depositar(Conta conta, double valor) {
        conta.depositar(valor);
    }
}
