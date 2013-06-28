/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.TipoImovel;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Eduardo
 */
public class TipoImovelDAOImp extends BaseDAOImp<TipoImovel, Long> implements TipoImovelDAO{

    @Override
    public TipoImovel pesquisaPorId(Long id) {
        abreConexao();
        TipoImovel tipo = (TipoImovel) session.get(TipoImovel.class, id);
        session.close();
        return tipo;
    }

    @Override
    public List<TipoImovel> listar() {
        abreConexao();
        Query query = session.createQuery("from TipoImovel");
        List<TipoImovel> tipos = query.list();
        session.close();
        return tipos;
    }

    @Override
    public List<TipoImovel> procuraTipoImovel(String tipo) throws Exception {
        abreConexao();
        Query query = session.createQuery("from TipoImovel ti WHERE ti.tipo like :tipo");
        query.setString("tipo", "%" +tipo+ "%");
        List<TipoImovel> tipos = query.list();
        session.close();
        return tipos;
    }
    
}
