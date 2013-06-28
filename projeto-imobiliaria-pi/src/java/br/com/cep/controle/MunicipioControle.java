/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.EstadoDAO;
import br.com.cep.dao.EstadoDAOImp;
import br.com.cep.dao.MunicipioDAO;
import br.com.cep.dao.MunicipioDAOImp;
import br.com.cep.entidade.Estado;
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
public class MunicipioControle {
    private Municipio municipio;
    private MunicipioDAO municipioDao;
    private DataModel model;
    private boolean pesquisa = false;
    private Estado estado;
    
    public Municipio getMunicipio() {
        if(municipio == null){
            municipio = new Municipio();
        }
        return municipio;
    }

    public DataModel getModel() {
        return model;
    }

    public Estado getEstado() {
        if(estado == null){
            estado = new Estado();
        }
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        municipioDao = new MunicipioDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        municipio.setEstado(estado);
            try {
                if(municipio.getId() == null){
                    municipioDao.salva(municipio);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   municipioDao.altera(municipio); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        return "index";
    }
    
    public String pesquisar() {
        if(municipio != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqMunicipioAlterar";
    }
    
    public String pesquisarExcluir() {
        if(municipio != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqMunicipioExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        municipio = (Municipio) model.getRowData();
        municipioDao = new MunicipioDAOImp();
        try {
            municipioDao.excluir(municipio);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    private void limpa() {
        municipio = null;
    }
    
    public String novoMunicipio() {
        municipio = new Municipio();
        pesquisa = false;
        return "cadMunicipio";
    }
    
    public void pesquisaMunicipio() {
        EnderecoUtil endUtil;
        if(municipio.getNome() != null){
            endUtil = new EnderecoUtil();
            List municipios = endUtil.pesquisaMunicipio(municipio.getNome());
            model = new ListDataModel(municipios);
        }
    }
    
    public List<SelectItem> getTodosEstado() throws Exception {
        EstadoDAO estDao = new EstadoDAOImp();
        List<Estado> estados = estDao.listar();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Estado estado : estados) {
            listaCombo.add(new SelectItem(estado.getId(), estado.getNome() + " - " + estado.getSigla()));
        }
        return listaCombo;
    }
    
    public String editar() {
        municipio = (Municipio) model.getRowData();
        setMunicipio(municipio);
        return "cadMunicipio";
    }
    
}
