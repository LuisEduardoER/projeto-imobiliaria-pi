/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Bairro;
import br.com.cep.entidade.Cliente;
import br.com.cep.entidade.Funcionario;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class ClienteDAOImp extends BaseDAOImp<Cliente, Long> implements ClienteDAO{

    @Override
    public Cliente pesquisaPorId(Long id) {
        abreConexao();
        Cliente cliente = (Cliente) session.get(Cliente.class, id);
        session.close();
        return cliente;
    }

    @Override
    public List<Cliente> listar() {
        abreConexao();
        Query query = session.createQuery("from Cliente");
        List<Cliente> clientes = query.list();
        session.close();
        return clientes;
    }

    @Override
    public List<Cliente> procuraClientePorNome(String nome) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Cliente func WHERE func.nome like :nome");
        query.setString("nome", "%" +nome+ "%");
        List<Cliente> clientes = query.list();
        session.close();
        return clientes;
    }
    
}
