/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Municipio;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class MunicipioDAOImp extends BaseDAOImp<Municipio, Long> implements MunicipioDAO{

    @Override
    public Municipio pesquisaPorId(Long id) {
        abreConexao();
        Municipio municipio = (Municipio) session.get(Municipio.class, id);
        session.close();
        return municipio;
    }

    @Override
    public List<Municipio> listar() {
        abreConexao();
        Query query = session.createQuery("from Municipio");
        List<Municipio> municipios = query.list();
        session.close();
        return municipios;
    }

    @Override
    public List<Municipio> procuraMunicipioPorNome(String nome) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Municipio muni WHERE muni.nome like :nome");
        query.setString("nome", "%" +nome+ "%");
        List<Municipio> municipios = query.list();
        session.close();
        return municipios;
    }
    
}
