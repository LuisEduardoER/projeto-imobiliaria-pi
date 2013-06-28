/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.TipoImovel;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public interface TipoImovelDAO extends BaseDAO<TipoImovel, Long>{
    public List<TipoImovel> procuraTipoImovel(String tipo) throws Exception;
}
