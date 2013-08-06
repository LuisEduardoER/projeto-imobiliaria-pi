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
    public List<Imovel> todosImoveis() throws Exception {
        abreConexao();
        Query query = session.createQuery("from Imovel");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisCasa() throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos WHERE imo.tipoImovel = 2");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }
    
    @Override
    public List<Imovel> todosImoveisApartamento() throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos WHERE imo.tipoImovel = 1");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisComercial() throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos WHERE imo.tipoImovel = 3");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisTerreno() throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos WHERE imo.tipoImovel = 4");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> todosImoveisSitio() throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos WHERE imo.tipoImovel = 6");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public Imovel imovelSelecionado(Long id) throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos WHERE imo.id = :id");
        query.setLong("id", id);
        Imovel imoveis = (Imovel) query.uniqueResult();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> listaTodosImoveis() throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos");
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }

    @Override
    public List<Imovel> imoveisDestaque() throws Exception {
        abreConexao();
        Query query = session.createQuery("SELECT DISTINCT imo from Imovel imo JOIN FETCH imo.arquivos ORDER BY imo.id").setFirstResult(12).setMaxResults(4);    
        List<Imovel> imoveis = query.list();
        session.close();
        return imoveis;
    }
}
