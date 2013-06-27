/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.EstadoDAO;
import br.com.cep.dao.EstadoDAOImp;
import br.com.cep.entidade.Estado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.jasper.tagplugins.jstl.core.Catch;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class EstadoControle {
    
    private Estado estado;
    private EstadoDAO estadoDao;
    private DataModel model;
    private boolean pesquisa = false;
    
    public Estado getEstado() {
        if(estado == null){
            estado = new Estado();
        }
        return estado;
    }

    public DataModel getModel() {
        return model;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        estadoDao = new EstadoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
            try {
                if(estado.getId() == null){
                    estadoDao.salva(estado);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   estadoDao.altera(estado); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        return "index";
    }
    
    public String pesquisar() {
        if(estado != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqEstadoAlterar";
    }
    
    public String pesquisarExcluir() {
        if(estado != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqEstadoExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        estado = (Estado) model.getRowData();
        estadoDao = new EstadoDAOImp();
        try {
            estadoDao.excluir(estado);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        estado = null;
    }
    
    public String novoEstado() {
        estado = new Estado();
        pesquisa = false;
        return "cadEstado";
    }
    
    public void pesquisaEstado() {
        estadoDao = new EstadoDAOImp();
        if(estado.getNome() != null){
            try {
            model = new ListDataModel(estadoDao.procuraUfPorNome(estado.getNome()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public String editar() {
        estado = (Estado) model.getRowData();
        setEstado(estado);
        return "cadEstado";
    }
    
}
