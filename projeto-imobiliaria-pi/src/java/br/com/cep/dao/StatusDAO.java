/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Status;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public interface StatusDAO extends BaseDAO<Status, Long>{
    public List<Status> procuraStatusPorStatus(String status) throws Exception;   
}
