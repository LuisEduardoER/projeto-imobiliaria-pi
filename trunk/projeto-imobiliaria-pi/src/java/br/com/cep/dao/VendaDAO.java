/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Funcionario;
import br.com.cep.entidade.Venda;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface VendaDAO extends BaseDAO<Venda, Long>{
    public List<Venda> procuraVendaPorData(Date dataVenda) throws Exception;
    public Funcionario procuraVendaPorCresci(String cresci) throws Exception; 
}
