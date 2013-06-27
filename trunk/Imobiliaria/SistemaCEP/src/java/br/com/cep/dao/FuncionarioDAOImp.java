/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Bairro;
import br.com.cep.entidade.Funcionario;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Aluno
 */
public class FuncionarioDAOImp extends BaseDAOImp<Funcionario, Long> implements FuncionarioDAO{

    @Override
    public Funcionario pesquisaPorId(Long id) {
        abreConexao();
        Funcionario funcionario = (Funcionario) session.get(Funcionario.class, id);
        session.close();
        return funcionario;
    }

    @Override
    public List<Funcionario> listar() {
        abreConexao();
        Query query = session.createQuery("from Funcionario");
        List<Funcionario> funcionarios = query.list();
        session.close();
        return funcionarios;
    }

    @Override
    public List<Funcionario> procuraFuncPorNome(String nome) throws Exception {
        abreConexao();
        Query query = session.createQuery("from Funcionario func WHERE func.nome like :nome");
        query.setString("nome", "%" +nome+ "%");
        List<Funcionario> funcionarios = query.list();
        session.close();
        return funcionarios;
    }
    
}
