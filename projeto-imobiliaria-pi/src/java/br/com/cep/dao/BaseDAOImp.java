/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Aluno
 */
public abstract class BaseDAOImp<T, ID> implements BaseDAO<T, ID> {
    protected Session session;
    protected Transaction tx;

    @Override
    public T salva(T entidade) throws Exception {
        abreConexao();
        session.save(entidade);
        fechaConexao();
        return entidade;
    }

    @Override
    public void excluir(T entidade) {
        abreConexao();
        session.delete(entidade);
        fechaConexao();
    }

    @Override
    public void altera(T entidade) {
        abreConexao();
        session.update(entidade);
        fechaConexao();
    }
    
    protected void abreConexao(){
        session = FabricaConexao.abreSessao();
        tx = session.beginTransaction();
    }
    
    protected void fechaConexao(){
        tx.commit();
        session.close();
    }
}
