package br.com.minhaempresa.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

    // Gerenciando entidades que serão tratadas no banco de dados
    // A entidade representará o database/schema

    // Declarando EntityManagerFactory (entidade para gerenciar a conexão)
    private static EntityManagerFactory entityManagerFactory=null;
    // Declarando EntityManager (entidade para gerenciar o database/schema)
    private static EntityManager entityManager=null;

    public static EntityManager getConexao() {

        // Verifica se o EntityManagerFactory já foi criado
        if(entityManagerFactory == null) {
            // Criar e gerenciar conexão com o banco de dados
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sistema-financeiro");
        }
        // Verifica se o EntityManager já foi criado
        if(entityManager ==  null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        // Retornando a conexão
        return entityManagerFactory.createEntityManager();
    }
}
