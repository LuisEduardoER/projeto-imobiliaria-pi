/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Cep;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface CepDAO extends BaseDAO<Cep, Long> {

    public Cep procuraCepPorCep(String cep) throws Exception;

    public List<Cep> procuraCepPorLogradouro(String logradouro) throws Exception;
}
