package br.com.minhaempresa.service;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;

public class ConsultarSaldoService {

    public double consultaSaldo(Conta conta) {
        // Consultando o saldo
        return conta.consultarSaldo();
    }

}
