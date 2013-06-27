/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Funcionario;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface FuncionarioDAO extends BaseDAO<Funcionario, Long>{
    public List<Funcionario> procuraFuncPorNome(String nome) throws Exception;
}
