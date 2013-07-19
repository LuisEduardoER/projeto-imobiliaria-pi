/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Usuario;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class UsuarioDAOImp extends BaseDAOImp<Usuario, Long> implements UsuarioDAO{

    @Override
    public Usuario pesquisaPorId(Long id) {
        abreConexao();
        Usuario usuario = (Usuario) session.get(Usuario.class, id);
        session.close();
        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        abreConexao();
        Query query = session.createQuery("from Usuario");
        List<Usuario> usuarios = query.list();
        session.close();
        return usuarios;
    }

    @Override
    public List<Usuario> procuraUsuarioPorLogin(String login) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Usuario usu WHERE usu.login like :login");
        query.setString("login", "%" +login+ "%");
        List<Usuario> usuarios = query.list();
        session.close();
        return usuarios;
    }
    
}
