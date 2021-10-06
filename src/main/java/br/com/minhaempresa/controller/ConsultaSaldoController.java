package br.com.minhaempresa.controller;

import br.com.minhaempresa.domain.Cliente;
import br.com.minhaempresa.domain.Conta;
import br.com.minhaempresa.domain.ContaCorrente;
import br.com.minhaempresa.service.ConsultarSaldoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/consultar-saldo")
public class ConsultaSaldoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = req.getParameter("nome");
        String sobrenome = req.getParameter("sobrenome");

        // Instanciando cliente e conta
        Cliente cliente = new Cliente(nome,sobrenome);
        Conta conta = new ContaCorrente(cliente);

        ConsultarSaldoService consultarSaldoService = new ConsultarSaldoService();
        double saldo = consultarSaldoService.consultaSaldo(conta);

        resp.getWriter().println("Cliente: " + nome + " " + sobrenome);
        resp.getWriter().println("Saldo atual: " + saldo);
    }
}
