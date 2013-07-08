/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.PerfilDAO;
import br.com.cep.dao.PerfilDAOImp;
import br.com.cep.dao.StatusDAO;
import br.com.cep.dao.StatusDAOImp;
import br.com.cep.entidade.Perfil;
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
public class PerfilControle {
    private Perfil perfil;
    private PerfilDAO perfilDao;
    private DataModel model;
    private boolean pesquisa = false;

    public DataModel getModel() {
        return model;
    }

    public Perfil getPerfil() {
        if(perfil == null){
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        perfilDao = new PerfilDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
            try {
                if(perfil.getId() == null){
                    perfilDao.salva(perfil);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   perfilDao.altera(perfil); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar status" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(perfil != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqPerfilAlterar";
    }
    
    public String pesquisarExcluir() {
        if(perfil != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqPerfilExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        perfil = (Perfil) model.getRowData();
        perfilDao = new PerfilDAOImp();
        try {
            perfilDao.excluir(perfil);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        perfil = null;
    }
    
    public String novoPerfil() {
        perfil = new Perfil();
        pesquisa = false;
        return "cadPerfil";
    }
    
    public void pesquisaPerfil() {
        perfilDao = new PerfilDAOImp();
        if(perfil.getNome() != null){
            try {
            model = new ListDataModel(perfilDao.procuraPerfilPorNome(perfil.getNome()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public String editar() {
        perfil = (Perfil) model.getRowData();
        setPerfil(perfil);
        return "cadPerfil";
    }
}
