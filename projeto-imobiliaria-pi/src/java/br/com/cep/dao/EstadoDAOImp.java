/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Estado;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class EstadoDAOImp extends BaseDAOImp<Estado, Long> implements EstadoDAO {

    @Override
    public Estado pesquisaPorId(Long id) {
        abreConexao();
        Estado estado = (Estado) session.get(Estado.class, id);
        session.close();
        return estado;
    }

    @Override
    public List<Estado> listar() {
        abreConexao();
        Query query = session.createQuery("from Estado");
        List<Estado> estados = query.list();
        session.close();
        return estados;
    }

    @Override
    public List<Estado> procuraUfPorNome(String nome) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Estado es WHERE es.nome like :nome");
        query.setString("nome", "%" +nome+ "%");
        List<Estado> estados = query.list();
        session.close();
        return estados;
    }
}
