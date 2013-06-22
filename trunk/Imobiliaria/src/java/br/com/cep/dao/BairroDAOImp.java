/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Bairro;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class BairroDAOImp extends BaseDAOImp<Bairro, Long> implements BairroDAO{

    @Override
    public Bairro pesquisaPorId(Long id) {
        abreConexao();
        Bairro bairro = (Bairro) session.get(Bairro.class, id);
        session.close();
        return bairro;
    }

    @Override
    public List<Bairro> listar() {
        abreConexao();
        Query query = session.createQuery("from Bairro");
        List<Bairro> bairros = query.list();
        session.close();
        return bairros;
    }

    @Override
    public List<Bairro> procuraBairroPorNome(String nome) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Bairro ba WHERE ba.nome like :nome");
        query.setString("nome", "%" +nome+ "%");
        List<Bairro> bairros = query.list();
        session.close();
        return bairros;
    }
    
}
