/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Perfil;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public interface PerfilDAO extends BaseDAO<Perfil, Long>{
    public List<Perfil> procuraPerfilPorNome(String nome) throws Exception;   
}
