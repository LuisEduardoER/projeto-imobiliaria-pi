/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.StatusDAO;
import br.com.cep.dao.StatusDAOImp;
import br.com.cep.entidade.Status;
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
public class StatusControle {
    private Status status;
    private StatusDAO statusDao;
    private DataModel model;
    private boolean pesquisa = false;

    public DataModel getModel() {
        return model;
    }

    public Status getStatus() {
        if(status == null){
            status = new Status();
        }
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        statusDao = new StatusDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
            try {
                if(status.getId() == null){
                    statusDao.salva(status);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   statusDao.altera(status); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar status" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(status != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqStatusAlterar";
    }
    
    public String pesquisarExcluir() {
        if(status != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqStatusExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        status = (Status) model.getRowData();
        statusDao = new StatusDAOImp();
        try {
            statusDao.excluir(status);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        status = null;
    }
    
    public String novoStatus() {
        status = new Status();
        pesquisa = false;
        return "cadStatus";
    }
    
    public void pesquisaStatus() {
        statusDao = new StatusDAOImp();
        if(status.getStatus() != null){
            try {
            model = new ListDataModel(statusDao.procuraStatusPorStatus(status.getStatus()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public String editar() {
        status = (Status) model.getRowData();
        setStatus(status);
        return "cadStatus";
    }
}
