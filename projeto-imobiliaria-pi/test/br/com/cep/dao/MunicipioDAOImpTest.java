/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Estado;
import br.com.cep.entidade.Municipio;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class MunicipioDAOImpTest {
    
    //@Test
    public void testSalva() throws Exception {
        System.out.println("salva");
        Municipio municipio = new Municipio();
        Estado estado = new Estado();
        estado.setId(1L);
        municipio.setNome("MUNICIPIOOOOO");
        municipio.setEstado(estado);
        
        MunicipioDAO mDao = new MunicipioDAOImp();
        mDao.salva(municipio);
    }
    
    //@Test
    public void testAltera() throws Exception {
        System.out.println("altera");
        Long id = 12758L;
        MunicipioDAO municipioDao = new MunicipioDAOImp();
        Municipio municipio = municipioDao.pesquisaPorId(id);
        municipio.setNome("NOMEE ALTERADOO");
        municipioDao.altera(municipio);
    }
    
    //@Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        Long id = 12758L;
        MunicipioDAO muni = new MunicipioDAOImp();
        Municipio municipio = muni.pesquisaPorId(id);
        muni.excluir(municipio);
    }
    
    //@Test
    public void testPesquisaPorId() throws Exception {
        System.out.println("pesquisaPorId");
        Long id = 1L;
        MunicipioDAO muniDao = new MunicipioDAOImp();
        Municipio municipio = muniDao.pesquisaPorId(id);
        System.out.println("Municipio: " + municipio.getNome());
        System.out.println("Estado: " + municipio.getEstado().getNome());
    }

    //@Test
    public void testListar() throws Exception {
        System.out.println("listar");
        MunicipioDAO instance = new MunicipioDAOImp();
        List<Municipio> municipios = instance.listar();
        System.out.println("Aqui vai mostrar a lista:");
        System.out.println("");
        for (Municipio muni : municipios) {
            System.out.println("Nome: " + muni.getNome());
            System.out.println("Descrição: " + muni.getEstado().getNome());
            System.out.println("");
        }
    }
}
