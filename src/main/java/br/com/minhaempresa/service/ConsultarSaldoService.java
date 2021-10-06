package br.com.minhaempresa.service;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;
import br.com.minhaempresa.repository.ContaRepository;

public class ConsultarSaldoService {

    public double consultaSaldo(Conta conta) {
        // Consultando o saldo

        ContaRepository contaRepository = new ContaRepository();
        // Delegando à ContaRepository a consulta do saldo
        return contaRepository.consultarSaldo();
    }

}
