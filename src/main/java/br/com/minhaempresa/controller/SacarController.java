package br.com.minhaempresa.controller;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;
import br.com.minhaempresa.exception.SaldoInsuficienteException;
import br.com.minhaempresa.service.ConsultarSaldoService;
import br.com.minhaempresa.service.DepositarService;
import br.com.minhaempresa.service.SacarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;

@WebServlet(urlPatterns = "/sacar")
public class SacarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Lendo dados via requisição
        String nome = req.getParameter("nome");
        String sobrenome = req.getParameter("sobrenome");
        double valor = Double.valueOf(req.getParameter("valor"));

        // Instanciando cliente e conta
        Cliente cliente = new Cliente(nome,sobrenome);
        Conta conta = new ContaCorrente(cliente);

        ConsultarSaldoService consultarSaldoService = new ConsultarSaldoService();
        double saldo = consultarSaldoService.consultaSaldo(conta);

        resp.getWriter().println("Cliente: " + nome + " " + sobrenome);
        resp.getWriter().println("Saldo inicial: " + NumberFormat.getCurrencyInstance().format(saldo));
        resp.getWriter().println("Saque: " + NumberFormat.getCurrencyInstance().format(valor));

        SacarService sacarService = new SacarService();
        try {
            sacarService.sacar(conta,valor);
        } catch (SaldoInsuficienteException e) {
            resp.getWriter().println("Saldo insuficiente na conta de origem");
        }

        saldo = consultarSaldoService.consultaSaldo(conta);
        resp.getWriter().println("Saldo final: " + NumberFormat.getCurrencyInstance().format(saldo));
    }
}
