/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Venda;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class VendaDAOImp extends BaseDAOImp<Venda, Long> implements VendaDAO{
    
    private List<Venda> listaVenda;

    @Override
    public Venda pesquisaPorId(Long id) {
        abreConexao();
        Venda venda = (Venda) session.get(Venda.class, id);
        session.close();
        return venda;
    }

    @Override
    public List<Venda> listar() {
        abreConexao();
        Query query = session.createQuery("from Venda");
        List<Venda> vendas = query.list();
        session.close();
        return vendas;
    }

    @Override
    public List<Venda> procuraVendaPorData(Date dataVenda) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Venda vend WHERE vend.dataVenda like :dataVenda");
        query.setString("dataVenda", "%" +dataVenda+ "%");
        List<Venda> vendas = query.list();
        session.close();
        return vendas;
    }

    @Override
    public List<Venda> procuraVendaPorCresci(String cresci) throws Exception {
        abreConexao();
        Query query = session.createQuery("from venda v inner join funcionario f where f.cresci like :cresci");
        query.setString("cresci", "%" +cresci+ "%");
        List<Venda> vendas = query.list();
        session.close();
        return vendas;
    }

}
