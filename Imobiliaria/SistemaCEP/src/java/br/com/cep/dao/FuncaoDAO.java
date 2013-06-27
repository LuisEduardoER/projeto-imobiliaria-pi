/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Funcao;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public interface FuncaoDAO extends BaseDAO<Funcao, Long>{
    public List<Funcao> procuraFuncaoPorNome(String nome) throws Exception;   
}
