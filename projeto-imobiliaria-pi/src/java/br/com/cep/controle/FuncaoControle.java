/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.BairroDAO;
import br.com.cep.dao.BairroDAOImp;
import br.com.cep.dao.FuncaoDAO;
import br.com.cep.dao.FuncaoDAOImp;
import br.com.cep.dao.MunicipioDAO;
import br.com.cep.dao.MunicipioDAOImp;
import br.com.cep.entidade.Bairro;
import br.com.cep.entidade.Funcao;
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
public class FuncaoControle {
    private Funcao funcao;
    private FuncaoDAO funcaoDao;
    private DataModel model;
    private boolean pesquisa = false;

    public DataModel getModel() {
        return model;
    }

    public Funcao getFuncao() {
        if(funcao == null){
            funcao = new Funcao();
        }
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        funcaoDao = new FuncaoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
            try {
                if(funcao.getId() == null){
                    funcaoDao.salva(funcao);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   funcaoDao.altera(funcao); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(funcao != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqFuncaoAlterar";
    }
    
    public String pesquisarExcluir() {
        if(funcao != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqFuncaoExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        funcao = (Funcao) model.getRowData();
        funcaoDao = new FuncaoDAOImp();
        try {
            funcaoDao.excluir(funcao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        funcao = null;
    }
    
    public String novaFuncao() {
        funcao = new Funcao();
        pesquisa = false;
        return "cadFuncao";
    }
    
    public void pesquisaFuncao() {
        funcaoDao = new FuncaoDAOImp();
        if(funcao.getNome() != null){
            try {
            model = new ListDataModel(funcaoDao.procuraFuncaoPorNome(funcao.getNome()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public String editar() {
        funcao = (Funcao) model.getRowData();
        setFuncao(funcao);
        return "cadFuncao";
    }
}
