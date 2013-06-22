/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.CepDAO;
import br.com.cep.dao.CepDAOImp;
import br.com.cep.dao.FuncionarioDAO;
import br.com.cep.dao.FuncionarioDAOImp;
import br.com.cep.entidade.Cep;
import br.com.cep.entidade.Endereco;
import br.com.cep.entidade.Funcionario;
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
public class FuncionarioControle {
    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDao;
    private DataModel model;
    private boolean pesquisa = false;
    private Endereco endereco;
    private Cep cep;
    
    public Endereco getEndereco() {
        if(endereco == null){
            endereco = new Endereco();
        }
        return endereco;
    }

    public Cep getCep() {
        if(cep == null){
            cep = new Cep();
        }
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public DataModel getModel() {
        return model;
    }

    public Funcionario getFuncionario() {
        if(funcionario == null){
            funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        funcionarioDao = new FuncionarioDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        endereco.setCep(cep);
        funcionario.setEndereco(endereco);
            try {
                if(funcionario.getId() == null){
                    funcionarioDao.salva(funcionario);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   funcionarioDao.altera(funcionario); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(funcionario != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqFuncionarioAlterar";
    }
    
    public String pesquisarExcluir() {
        if(funcionario != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqFuncionarioExcluir";
    }
    
    public String editar() {
        funcionario = (Funcionario) model.getRowData();
        endereco = funcionario.getEndereco();
        cep = endereco.getCep();
        setFuncionario(funcionario);
        return "cadFuncionario";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        funcionario = (Funcionario) model.getRowData();
        funcionarioDao = new FuncionarioDAOImp();
        try {
            funcionarioDao.excluir(funcionario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        funcionario = null;
        endereco = null;
    }
    
    public String novoFuncionario() {
        funcionario = new Funcionario();
        pesquisa = false;
        return "cadFuncionario";
    }
    
    public void pesquisaFuncionario() {
        funcionarioDao = new FuncionarioDAOImp();
        if(funcionario.getNome() != null){
            try {
            model = new ListDataModel(funcionarioDao.procuraFuncPorNome(funcionario.getNome()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public void pesquisaCep() {
        CepDAO cepDao = new CepDAOImp();
        if(cep.getCep() != null){
            try {
                cep = cepDao.procuraCepPorCep(cep.getCep());
            } catch (Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
}
