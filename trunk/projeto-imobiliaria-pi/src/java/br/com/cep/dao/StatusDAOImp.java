/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Status;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Guilherme
 */
public class StatusDAOImp extends BaseDAOImp<Status, Long> implements StatusDAO{

    @Override
    public Status pesquisaPorId(Long id) {
        abreConexao();
        Status status = (Status) session.get(Status.class, id);
        session.close();
        return status;
    }

    @Override
    public List<Status> listar() {
       abreConexao();
        Query query = session.createQuery("from Status");
        List<Status> statuss = query.list();
        session.close();
        return statuss;
    }


    @Override
    public List<Status> procuraStatusPorStatus(String status) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Status sta WHERE sta.status like :status");
        query.setString("status", "%" +status+ "%");
        List<Status> statuss = query.list();
        session.close();
        return statuss;
    }

}
