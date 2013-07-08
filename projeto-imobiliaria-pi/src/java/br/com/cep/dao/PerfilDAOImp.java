/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Perfil;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Guilherme
 */
public class PerfilDAOImp extends BaseDAOImp<Perfil, Long> implements PerfilDAO{

    @Override
    public Perfil pesquisaPorId(Long id) {
        abreConexao();
        Perfil perfil = (Perfil) session.get(Perfil.class, id);
        session.close();
        return perfil;
    }

    @Override
    public List<Perfil> listar() {
       abreConexao();
        Query query = session.createQuery("from Perfil");
        List<Perfil> perfis = query.list();
        session.close();
        return perfis;
    }


    @Override
    public List<Perfil> procuraPerfilPorNome(String nome) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Perfil per WHERE per.nome like :nome");
        query.setString("nome", "%" +nome+ "%");
        List<Perfil> perfis = query.list();
        session.close();
        return perfis;
    }

}
