/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Bairro;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface BairroDAO extends BaseDAO<Bairro, Long>{
    public List<Bairro> procuraBairroPorNome(String nome) throws Exception;
}
