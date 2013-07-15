/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Arquivos;
import br.com.cep.entidade.Imovel;
import br.com.cep.entidade.Status;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Guilherme
 */
public class ArquivosDAOImp extends BaseDAOImp<Arquivos, Long> implements ArquivosDAO{

    @Override
    public Arquivos pesquisaPorId(Long id) {
        abreConexao();
        Arquivos arquivo = (Arquivos) session.get(Arquivos.class, id);
        session.close();
        return arquivo;
    }

    @Override
    public List<Arquivos> listar() {
       abreConexao();
        Query query = session.createQuery("from Arquivos");
        List<Arquivos> arquivos = query.list();
        session.close();
        return arquivos;
    }

    @Override
    public List<Arquivos> procuraArquivosPorNome(String nome) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
