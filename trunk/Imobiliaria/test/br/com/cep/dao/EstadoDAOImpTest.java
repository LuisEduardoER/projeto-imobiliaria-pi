/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Estado;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class EstadoDAOImpTest {
    
    public EstadoDAOImpTest() {
    }
    
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Estado estado = new Estado();
        estado.setNome("Estado Teste");
        estado.setSigla("ET");
        EstadoDAO estadoDao = new EstadoDAOImp();
        Estado estado1 = estadoDao.salva(estado);
        assertNotNull(estado1.getId());
    }
    
    //@Test
    public void testAltera() throws Exception {
        System.out.println("altera");
        Long id = 28L;
        EstadoDAO estadoDao = new EstadoDAOImp();
        Estado estado = estadoDao.pesquisaPorId(id);
        estado.setNome("Alterado Estado Teste");
        estado.setSigla("AT");
        estadoDao.altera(estado);
    }
    
    //@Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        Long id = 29L;
        EstadoDAO estadoDao = new EstadoDAOImp();
        Estado estado = estadoDao.pesquisaPorId(id);
        estadoDao.excluir(estado);
    }
    
    //@Test
    public void testPesquisaPorId() {
        System.out.println("pesquisaPorId");
        Long id = 28L;
        EstadoDAO estadoDao=  new EstadoDAOImp();
        Estado est = estadoDao.pesquisaPorId(id);
        System.out.println("Nome: " + est.getNome());
        System.out.println("Sigla: " + est.getSigla());
    }

    //@Test
    public void testListar() {
        System.out.println("listar");
        EstadoDAO instance = new EstadoDAOImp();
        List<Estado> estados = instance.listar();
        System.out.println("Aqui vai mostrar a lista:");
        System.out.println("");
        for (Estado estado : estados) {
            System.out.println("Nome: " + estado.getNome());
            System.out.println("Sigla: " + estado.getSigla());
            System.out.println("");
        }
    }
}
