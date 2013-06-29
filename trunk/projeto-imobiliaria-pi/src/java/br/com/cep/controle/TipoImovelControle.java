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
 * @author Eduardo
 */
@ManagedBean
@SessionScoped
public class TipoImovelControle {

    private TipoImovel tipo;
    private TipoImovelDAO tipoDAO;
    private DataModel model;
    private boolean pesquisa;

    public TipoImovel getTipo() {
        if (tipo == null) {
            tipo = new TipoImovel();
        }
        return tipo;
    }

    public void setTipo(TipoImovel tipo) {
        this.tipo = tipo;
    }

    public TipoImovelDAO getTipoDAO() {
        return tipoDAO;
    }

    public void setTipoDAO(TipoImovelDAO tipoDAO) {
        this.tipoDAO = tipoDAO;
    }
    
    public DataModel getData() {
        return model;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String salvar() {
        tipoDAO = new TipoImovelDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (tipo.getId() == null) {
                tipoDAO.salva(tipo);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
            } else {
                tipoDAO.altera(tipo);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao salvar Tipo de Imovel" + ex.getMessage());
        }
        return "index";
    }

    public String pesquisar() {
        if (tipo != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqTipoAlterar";
    }

    public String pesquisarExcluir() {
        if (tipo != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqTipoExcluir";
    }

    private void limpa() {
        tipo = null;
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        tipo = (TipoImovel) model.getRowData();
        tipoDAO = new TipoImovelDAOImp();
        try {
            tipoDAO.excluir(tipo);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    public String novoTipo() {
        tipo = new TipoImovel();
        pesquisa = false;
        return "cadTipo";
    }
    
    public void pesquisaTipo() {
        tipoDAO = new TipoImovelDAOImp();
        if(tipo.getTipo() != null){
            try {
            model = new ListDataModel(tipoDAO.procuraTipoImovel(tipo.getTipo()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public String editar() {
        tipo = (TipoImovel) model.getRowData();
        setTipo(tipo);
        return "cadTipo";
    }
}
