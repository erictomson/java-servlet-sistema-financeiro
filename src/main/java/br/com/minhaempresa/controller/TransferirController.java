package br.com.minhaempresa.controller;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;
import br.com.minhaempresa.exception.SaldoInsuficienteException;
import br.com.minhaempresa.service.ConsultarSaldoService;
import br.com.minhaempresa.service.TransferirService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;

@WebServlet(urlPatterns = "/transferir")
public class TransferirController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lendo dados via requisição
        String nome = req.getParameter("nome");
        String sobrenome = req.getParameter("sobrenome");
        double valor = Double.valueOf(req.getParameter("valor"));

        // Instanciando cliente e conta de Origem
        Cliente cliente = new Cliente(nome,sobrenome);
        Conta conta = new ContaCorrente(cliente);

        // Instanciando cliente e conta de Destino
        Cliente clienteDestino = new Cliente("Ciclano", "de Tal");
        ContaCorrente contaDestino = new ContaCorrente(clienteDestino);

        // Confirmando dados da transferência
        ConsultarSaldoService consultarSaldoService = new ConsultarSaldoService();
        double saldoOrigem = consultarSaldoService.consultaSaldo(conta);
        double saldoDestino = consultarSaldoService.consultaSaldo(contaDestino);

        resp.getWriter().println("Conta de origem");
        resp.getWriter().println("Cliente......: " + nome + " " + sobrenome);
        resp.getWriter().println("Saldo inicial: " + NumberFormat.getCurrencyInstance().format(saldoOrigem));
        resp.getWriter().println("================");
        resp.getWriter().println("Conta de destino");
        resp.getWriter().println("Cliente......: " + clienteDestino.toString());
        resp.getWriter().println("Saldo inicial: " + NumberFormat.getCurrencyInstance().format(saldoDestino));
        resp.getWriter().println("================");
        //Efetuando a transferência
        resp.getWriter().println("Transferência: " + NumberFormat.getCurrencyInstance().format(valor));
        TransferirService transferirService = new TransferirService();
        try {
            transferirService.transferir(conta,contaDestino,valor);
        } catch (SaldoInsuficienteException e) {
            resp.getWriter().println("Saldo insuficiente na conta de origem");
        }
        resp.getWriter().println("================");
        resp.getWriter().println("Saldos atualizados");
        resp.getWriter().println("Conta de Origem: " + NumberFormat.getCurrencyInstance().format(saldoOrigem));
        resp.getWriter().println("Conta de Destino: " + NumberFormat.getCurrencyInstance().format(saldoDestino));
    }


}
