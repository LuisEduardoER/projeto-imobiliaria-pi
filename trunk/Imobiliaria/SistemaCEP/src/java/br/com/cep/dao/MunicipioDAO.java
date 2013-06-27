/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Municipio;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface MunicipioDAO extends BaseDAO<Municipio, Long> {
    public List<Municipio> procuraMunicipioPorNome(String nome) throws Exception;
}
