/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.VendaDAO;
import br.com.cep.dao.VendaDAOImp;
import br.com.cep.entidade.Funcionario;
import br.com.cep.entidade.Imovel;
import br.com.cep.entidade.Venda;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VendaControle {
    private Venda venda;
    private VendaDAO vendaDao;
    private DataModel model;
    private DataModel modelFuncionario;
    private DataModel modelImovel;
    private DataModel modelRel;
    private boolean pesquisa = false;
    private Funcionario funcionario;
    private Imovel imovel;
    
    public Funcionario getFuncionario() {
        if(funcionario == null){
            funcionario = new Funcionario();
        }
        return funcionario;
    }

    public DataModel getModelRel() {
        return modelRel;
    }

    public Imovel getImovel() {
        if(imovel == null){
            imovel = new Imovel();
        }
        return imovel;
    }
    
    public DataModel getModel() {
        return model;
    }

    public DataModel getModelFuncionario() {
        return modelFuncionario;
    }
    
    public DataModel getModelImovel() {
        return modelImovel;
    }

    public Venda getVenda() {
        if(venda == null){
            venda = new Venda();
        }
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        vendaDao = new VendaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        venda.setFuncionario(funcionario);
        venda.setImovel(imovel);
            try {
                if(venda.getId() == null){
                    vendaDao.salva(venda);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   vendaDao.altera(venda); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(venda != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqVendaAlterar";
    }
    
    public String pesquisarExcluir() {
        if(venda != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqVendaExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        venda = (Venda) model.getRowData();
        vendaDao = new VendaDAOImp();
        try {
            vendaDao.excluir(venda);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        venda = null;
        funcionario = null;
    }
    
    public String novoVenda() {
        venda = new Venda();
        pesquisa = false;
        return "cadVenda";
    }
    
    public void pesquisaVenda() {
        vendaDao = new VendaDAOImp();
        if(venda.getDataVenda()!= null){
            try {
            model = new ListDataModel(vendaDao.procuraVendaPorData(venda.getDataVenda()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public void pesquisaFuncionario() {
        EnderecoUtil endUtil;
        if(funcionario.getNome() != null){
            endUtil = new EnderecoUtil();
            List funcionais = endUtil.pesquisaFuncionario(funcionario.getNome());
            modelFuncionario = new ListDataModel(funcionais);
        }
    }
    
    public void pesquisaImovel() {
        EnderecoUtil endUtil;
        if(imovel.getCodigo() != null){
            endUtil = new EnderecoUtil();
            List imoveis = endUtil.pesquisaImovel(imovel.getCodigo());
            modelImovel = new ListDataModel(imoveis);
        }
    }
    
    public String editar() {
        venda = (Venda) model.getRowData();
        setVenda(venda);
        return "cadVenda";
    }
    
    public void carregaFuncionario() {
        funcionario = (Funcionario) modelFuncionario.getRowData();
        pesquisa = false;
    }
    
    public void carregaImovel() {
        imovel = (Imovel) modelImovel.getRowData();
        pesquisa = false;
    }
    
    //relatorio vendas
    
    public String pesquisarRel() {
        venda = new Venda();
        pesquisa = false;
        return "geraRelatorio";
    }
    
    public void pesquisaRelatorio() {
        vendaDao = new VendaDAOImp();
        if(venda.getObservacao() != null){
            try {
            modelRel = new ListDataModel(vendaDao.procuraVendaPorCresci(venda.getFuncionario().getCresci()));
            } catch(Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
}
