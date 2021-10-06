package br.com.minhaempresa.service;

import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.exception.SaldoInsuficienteException;

public class TransferirService {

    public void transferir(Conta contaOrigem, Conta contaDestino, double valor) throws SaldoInsuficienteException {
        contaOrigem.transferir(valor,contaDestino);
    }
}
