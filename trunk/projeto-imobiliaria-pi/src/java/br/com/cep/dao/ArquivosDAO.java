/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Arquivos;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public interface ArquivosDAO extends BaseDAO<Arquivos, Long>{ 
    public List<Arquivos> procuraArquivosPorNome(String nome) throws Exception;
}
