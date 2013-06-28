/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Estado;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface EstadoDAO extends BaseDAO<Estado, Long> {
    public List<Estado> procuraUfPorNome(String nome) throws Exception;
}
