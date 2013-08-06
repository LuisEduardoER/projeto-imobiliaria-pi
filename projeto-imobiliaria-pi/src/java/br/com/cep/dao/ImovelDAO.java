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
    public List<Imovel> todosImoveisCasa() throws Exception;
    public List<Imovel> todosImoveisApartamento() throws Exception;
    public List<Imovel> todosImoveisComercial() throws Exception;
    public List<Imovel> todosImoveisTerreno() throws Exception;
    public List<Imovel> todosImoveisSitio() throws Exception;
    public List<Imovel> todosImoveis() throws Exception;
    public List<Imovel> listaTodosImoveis() throws Exception;
    public List<Imovel> imoveisDestaque() throws Exception;
    Imovel imovelSelecionado(Long id) throws Exception;
    public List<Imovel> buscarPorCodigo(String codigo) throws Exception;
}
