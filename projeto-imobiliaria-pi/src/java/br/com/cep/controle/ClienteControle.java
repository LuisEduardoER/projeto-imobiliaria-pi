/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.CepDAO;
import br.com.cep.dao.CepDAOImp;
import br.com.cep.dao.ClienteDAO;
import br.com.cep.dao.ClienteDAOImp;
import br.com.cep.entidade.Cep;
import br.com.cep.entidade.Cliente;
import br.com.cep.entidade.Endereco;
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
public class ClienteControle {
    private Cliente cliente;
    private ClienteDAO clienteDao;
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

    public Cliente getCliente() {
        if(cliente == null){
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        clienteDao = new ClienteDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        endereco.setCep(cep);
        cliente.setEndereco(endereco);
            try {
                if(cliente.getId() == null){
                    clienteDao.salva(cliente);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   clienteDao.altera(cliente); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(cliente != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqClienteAlterar";
    }
    
    public String pesquisarExcluir() {
        if(cliente != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqClienteExcluir";
    }
    
    public String editar() {
        cliente = (Cliente) model.getRowData();
        endereco = cliente.getEndereco();
        cep = endereco.getCep();
        setCliente(cliente);
        return "cadCliente";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        cliente = (Cliente) model.getRowData();
        clienteDao = new ClienteDAOImp();
        try {
            clienteDao.excluir(cliente);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        cliente = null;
        endereco = null;
    }
    
    public String novoCliente() {
        cliente = new Cliente();
        pesquisa = false;
        return "cadCliente";
    }
    
    public void pesquisaCliente() {
        clienteDao = new ClienteDAOImp();
        if(cliente.getNome() != null){
            try {
            model = new ListDataModel(clienteDao.procuraClientePorNome(cliente.getNome()));
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
        limpa();
    }
}
