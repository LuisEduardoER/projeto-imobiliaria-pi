/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Funcao;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Guilherme
 */
public class FuncaoDAOImp extends BaseDAOImp<Funcao, Long> implements FuncaoDAO{

    @Override
    public Funcao pesquisaPorId(Long id) {
        abreConexao();
        Funcao funcao = (Funcao) session.get(Funcao.class, id);
        session.close();
        return funcao;
    }

    @Override
    public List<Funcao> listar() {
       abreConexao();
        Query query = session.createQuery("from Funcao");
        List<Funcao> funcoes = query.list();
        session.close();
        return funcoes;
    }

    @Override
    public List<Funcao> procuraFuncaoPorNome(String nome) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Funcao func WHERE func.nome like :nome");
        query.setString("nome", "%" +nome+ "%");
        List<Funcao> funcoes = query.list();
        session.close();
        return funcoes;
    }
    
}
