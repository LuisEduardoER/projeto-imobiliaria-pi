/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Cep;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class CepDAOImp extends BaseDAOImp<Cep, Long> implements CepDAO {

    @Override
    public Cep pesquisaPorId(Long id) {
        abreConexao();
        Cep cep = (Cep) session.get(Cep.class, id);
        session.close();
        return cep;
    }

    @Override
    public List<Cep> listar() {
        abreConexao();
        Query query = session.createQuery("from Cep");
        List<Cep> ceps = query.list();
        session.close();
        return ceps;
    }

    @Override
    public Cep procuraCepPorCep(String numero) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Cep c WHERE c.cep = :cep");
        query.setString("cep", numero);
        Cep cep = (Cep) query.uniqueResult();
        session.close();
        return cep;
    }

    @Override
    public List<Cep> procuraCepPorLogradouro(String logradouro) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Cep c WHERE logradouro like :logradouro");
        query.setString("logradouro", "%" +logradouro+ "%");
        List<Cep> ceps = query.list();
        session.close();
        return ceps;
    }
    
}
