package br.com.minhaempresa.controller;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;
import br.com.minhaempresa.service.ConsultarSaldoService;
import br.com.minhaempresa.service.DepositarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;

@WebServlet(urlPatterns = "/depositar")
public class DepositarController extends HttpServlet {

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

        DepositarService depositarService = new DepositarService();
        depositarService.depositar(conta,valor);

        saldo = consultarSaldoService.consultaSaldo(conta);
        resp.getWriter().println("Deposito: " + NumberFormat.getCurrencyInstance().format(valor));
        resp.getWriter().println("Saldo final: " + NumberFormat.getCurrencyInstance().format(saldo));
    }
}
