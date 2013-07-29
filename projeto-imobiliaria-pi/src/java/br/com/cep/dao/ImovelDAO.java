/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import br.com.cep.entidade.Imovel;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface ImovelDAO extends BaseDAO<Imovel, Long>{
    public List<Imovel> procuraImovelPorCodigo(String codigo) throws Exception;
    public List<Imovel> todosImoveisCasa(String tipoImovel) throws Exception;
    public List<Imovel> todosImoveisApartamento() throws Exception;
    public List<Imovel> todosImoveisComercial(String tipoImovel) throws Exception;
    public List<Imovel> todosImoveisTerreno(String tipoImovel) throws Exception;
    public List<Imovel> todosImoveisSitio(String tipoImovel) throws Exception;
}
