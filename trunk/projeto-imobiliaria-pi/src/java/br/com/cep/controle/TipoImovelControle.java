/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.TipoImovelDAO;
import br.com.cep.dao.TipoImovelDAOImp;
import br.com.cep.entidade.TipoImovel;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class TipoImovelControle {
    private TipoImovel tipo;
    private TipoImovelDAO tipoDao;
    private DataModel model;
    private boolean pesquisa = false;

    public DataModel getModel() {
        return model;
    }

    public TipoImovel getTipoImovel() {
        if(tipo == null){
            tipo = new TipoImovel();
        }
        return tipo;
    }

    public void setTipoImovel(TipoImovel tipo) {
        this.tipo = tipo;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        tipoDao = new TipoImovelDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
            try {
                if(tipo.getId() == null){
                    tipoDao.salva(tipo);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   tipoDao.altera(tipo); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar Tipo de Imóvel" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(tipo != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqTipoAlterar";
    }
    
    public String pesquisarExcluir() {
        if(tipo != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqTipoExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        tipo = (TipoImovel) model.getRowData();
        tipoDao = new TipoImovelDAOImp();
        try {
            tipoDao.excluir(tipo);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        tipo = null;
    }
    
    public String novoTipo() {
        tipo = new TipoImovel();
        pesquisa = false;
        return "cadTipo";
    }
    
    public void pesquisaTipo() {
        tipoDao = new TipoImovelDAOImp();
        if(tipo.getTipo() != null){
            try {
            model = new ListDataModel(tipoDao.procuraTipoImovel(tipo.getTipo()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os tipos" + ex.getMessage());
            }
        }
    }
    
    public String editar() {
        tipo = (TipoImovel) model.getRowData();
        setTipoImovel(tipo);
        return "cadTipo";
    }
}
