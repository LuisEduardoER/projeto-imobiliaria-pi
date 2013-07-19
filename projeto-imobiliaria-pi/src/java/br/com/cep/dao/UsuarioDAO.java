/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Usuario;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface UsuarioDAO extends BaseDAO<Usuario, Long>{
    public List<Usuario> procuraUsuarioPorLogin(String login) throws Exception;
}
