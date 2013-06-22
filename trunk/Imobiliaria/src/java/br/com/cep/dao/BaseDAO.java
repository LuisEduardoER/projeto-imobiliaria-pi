/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import java.util.List;

/**
 *
 * @author Aluno
 */
public interface BaseDAO<T, ID> {
    
    T salva(T entidade) throws Exception;
    
    T pesquisaPorId(ID id);
    
    void excluir(T entidade);
    
    void altera(T entidade);
    
    List<T> listar();
}
