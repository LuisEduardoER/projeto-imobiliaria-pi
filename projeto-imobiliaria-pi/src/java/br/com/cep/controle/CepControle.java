/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.BairroDAO;
import br.com.cep.dao.BairroDAOImp;
import br.com.cep.dao.CepDAO;
import br.com.cep.dao.CepDAOImp;
import br.com.cep.entidade.Bairro;
import br.com.cep.entidade.Cep;
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
public class CepControle {
    private Cep cep;
    private CepDAO cepDao;
    private DataModel model;
    private DataModel modelBairro;
    private Bairro bairro;
    private boolean pesquisa = false;
    
    public Bairro getBairro() {
        if(bairro == null){
            bairro = new Bairro();
        }
        return bairro;
    }

    public DataModel getModel() {
        return model;
    }

    public DataModel getModelBairro() {
        return modelBairro;
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
    
    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        cepDao = new CepDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        cep.setBairro(bairro);
            try {
                if(cep.getId() == null){
                    cepDao.salva(cep);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   cepDao.altera(cep); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(cep != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqCepAlterar";
    }
    
    public String pesquisarExcluir() {
        if(cep != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqCepExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        cep = (Cep) model.getRowData();
        cepDao = new CepDAOImp();
        try {
            cepDao.excluir(cep);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        cep = null;
        bairro = null;
    }
    
    public String novoCep() {
        cep = new Cep();
        pesquisa = false;
        return "cadCep";
    }
    
    public void pesquisaCep() {
        cepDao = new CepDAOImp();
        if(cep.getCep()!= null){
            try {
             cep = cepDao.procuraCepPorCep(cep.getCep());
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public void pesquisaCepLogradouro() {
        cepDao = new CepDAOImp();
        if(cep.getLogradouro()!= null){
            try {
            model = new ListDataModel(cepDao.procuraCepPorLogradouro(cep.getLogradouro()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public void pesquisaBairro() {
        EnderecoUtil endUtil;
        if(bairro.getNome() != null){
            endUtil = new EnderecoUtil();
            List bairros = endUtil.pesquisaBairro(bairro.getNome());
            modelBairro = new ListDataModel(bairros);
        }
        limpa();
    }
    
    public List<SelectItem> getTodosBairros() throws Exception {
        BairroDAO bairroDao = new BairroDAOImp();
        List<Bairro> bairros = bairroDao.listar();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Bairro bairro : bairros) {
            listaCombo.add(new SelectItem(bairro.getId(), bairro.getNome()));
        }
        return listaCombo;
    }
    
    public String editar() {
        cep = (Cep) model.getRowData();
        setCep(cep);
        return "cadCep";
    }
    
    public void carregaBairro() {
        bairro = (Bairro) modelBairro.getRowData();
        pesquisa = false;
    }
}