/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Imovel;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class ImovelDAOImp extends BaseDAOImp<Imovel, Long> implements ImovelDAO{

    @Override
    public Imovel pesquisaPorId(Long id) {
        abreConexao();
        Imovel imovel = (Imovel) session.get(Imovel.class, id);
        session.close();
        return imovel;
    }

    @Override
    public List<Imovel> listar() {
        abreConexao();
        Query query = session.createQuery("from Imovel");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> procuraImovelPorCodigo(String codigo) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Imovel imo WHERE imo.codigo like :codigo");
        query.setString("codigo", "%" +codigo+ "%");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisCasa(String tipoImovel) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Imovel WHERE id_tipoImovel = 2");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisApartamento(String tipoImovel) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Imovel WHERE id_tipoImovel = 1");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisComercial(String tipoImovel) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Imovel WHERE id_tipoImovel = 3");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisTerreno(String tipoImovel) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Imovel WHERE id_tipoImovel = 4");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisSitio(String tipoImovel) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Imovel WHERE id_tipoImovel = 6");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }
    
}
