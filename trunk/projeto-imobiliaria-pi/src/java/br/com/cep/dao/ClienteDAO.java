/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Cliente;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface ClienteDAO extends BaseDAO<Cliente, Long>{
    public List<Cliente> procuraClientePorNome(String nome) throws Exception;
}
