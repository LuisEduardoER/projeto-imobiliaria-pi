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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class BairroControle {
    private Bairro bairro;
    private BairroDAO bairroDao;
    private DataModel model;
    private DataModel modelMunicipio;
    private boolean pesquisa = false;
    private Municipio municipio;
    
    public Municipio getMunicipio() {
        if(municipio == null){
            municipio = new Municipio();
        }
        return municipio;
    }

    public DataModel getModel() {
        return model;
    }

    public DataModel getModelMunicipio() {
        return modelMunicipio;
    }

    public Bairro getBairro() {
        if(bairro == null){
            bairro = new Bairro();
        }
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        bairroDao = new BairroDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        bairro.setMunicipio(municipio);
            try {
                if(bairro.getId() == null){
                    bairroDao.salva(bairro);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   bairroDao.altera(bairro); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(bairro != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqBairroAlterar";
    }
    
    public String pesquisarExcluir() {
        if(bairro != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqBairroExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        bairro = (Bairro) model.getRowData();
        bairroDao = new BairroDAOImp();
        try {
            bairroDao.excluir(bairro);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        bairro = null;
        municipio = null;
    }
    
    public String novoBairro() {
        bairro = new Bairro();
        pesquisa = false;
        return "cadBairro";
    }
    
    public void pesquisaBairro() {
        bairroDao = new BairroDAOImp();
        if(bairro.getNome() != null){
            try {
            model = new ListDataModel(bairroDao.procuraBairroPorNome(bairro.getNome()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public void pesquisaMunicipio() {
        EnderecoUtil endUtil;
        if(municipio.getNome() != null){
            endUtil = new EnderecoUtil();
            List municipios = endUtil.pesquisaMunicipio(municipio.getNome());
            modelMunicipio = new ListDataModel(municipios);
        }
        limpa();
    }
    
    public List<SelectItem> getTodosMunicipios() throws Exception {
        MunicipioDAO muniDao = new MunicipioDAOImp();
        List<Municipio> municipios = muniDao.listar();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Municipio municipio : municipios) {
            listaCombo.add(new SelectItem(municipio.getId(), municipio.getNome()));
        }
        return listaCombo;
    }
    
    public String editar() {
        bairro = (Bairro) model.getRowData();
        setBairro(bairro);
        return "cadBairro";
    }
    
    public void carregaMunicipio() {
        municipio = (Municipio) modelMunicipio.getRowData();
        pesquisa = false;
    }
}
