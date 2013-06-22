/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.BairroDAO;
import br.com.cep.dao.BairroDAOImp;
import br.com.cep.dao.MunicipioDAO;
import br.com.cep.dao.MunicipioDAOImp;
import br.com.cep.entidade.Bairro;
import br.com.cep.entidade.Municipio;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Aluno
 */
public class EnderecoUtil {
    public List<Municipio> pesquisaMunicipio(String nome) {
        List<Municipio> municipios = new ArrayList();
        MunicipioDAO municipioDao = new MunicipioDAOImp();
            try {
            municipios = municipioDao.procuraMunicipioPorNome(nome);
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        return municipios;
    }
    
    public List<Bairro> pesquisaBairro(String nome) {
        List<Bairro> bairros = new ArrayList();
        BairroDAO bairroDao = new BairroDAOImp();
            try {
            bairros = bairroDao.procuraBairroPorNome(nome);
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        return bairros;
    }
}
