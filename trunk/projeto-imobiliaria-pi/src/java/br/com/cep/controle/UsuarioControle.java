/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.MunicipioDAO;
import br.com.cep.dao.MunicipioDAOImp;
import br.com.cep.dao.PerfilDAO;
import br.com.cep.dao.PerfilDAOImp;
import br.com.cep.dao.UsuarioDAO;
import br.com.cep.dao.UsuarioDAOImp;
import br.com.cep.entidade.Funcionario;
import br.com.cep.entidade.Municipio;
import br.com.cep.entidade.Perfil;
import br.com.cep.entidade.Usuario;
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
public class UsuarioControle {
    private Usuario usuario;
    private UsuarioDAO usuarioDao;
    private Perfil perfil;
    private DataModel model;
    private DataModel modelFuncionario;
    private boolean pesquisa = false;
    private Funcionario funcionario;
    
    public Funcionario getFuncionario() {
        if(funcionario == null){
            funcionario = new Funcionario();
        }
        return funcionario;
    }

    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }    
    
    public DataModel getModel() {
        return model;
    }

    public DataModel getModelFuncionario() {
        return modelFuncionario;
    }

    public Usuario getUsuario() {
        if(usuario == null){
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(boolean pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String salvar(){
        usuarioDao = new UsuarioDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        usuario.setFuncionario(funcionario);
        usuario.setPerfil(perfil);
            try {
                if(usuario.getId() == null){
                    usuarioDao.salva(usuario);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
                }else{
                   usuarioDao.altera(usuario); 
                   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
                }
            } catch (Exception ex) {
                System.out.println("Erro ao salvar estado" + ex.getMessage());
            }
        limpa();
        return "index";
    }
    
    public String pesquisar() {
        if(usuario != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqUsuarioAlterar";
    }
    
    public String pesquisarExcluir() {
        if(usuario != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqUsuarioExcluir";
    }
    
    public String excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Usuario) model.getRowData();
        usuarioDao = new UsuarioDAOImp();
        try {
            usuarioDao.excluir(usuario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
                System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }
    
    public List<SelectItem> getTodosPerfis() throws Exception {
        PerfilDAO perfDao = new PerfilDAOImp();
        List<Perfil> perfi = perfDao.listar();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Perfil perf : perfi) {
            listaCombo.add(new SelectItem(perf.getId(), perf.getNome()));
        }
        return listaCombo;
    }
    
    private void limpa() {
        usuario = null;
        funcionario = null;
    }
    
    public String novoUsuario() {
        usuario = new Usuario();
        pesquisa = false;
        return "cadUsuario";
    }
    
    public void pesquisaUsuario() {
        usuarioDao = new UsuarioDAOImp();
        if(usuario.getLogin()!= null){
            try {
            model = new ListDataModel(usuarioDao.procuraUsuarioPorLogin(usuario.getLogin()));
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
        limpa();
    }
    
    public List<SelectItem> getTodosMunicipios() throws Exception {
        MunicipioDAO muniDao = new MunicipioDAOImp();
        List<Municipio> municipios = muniDao.listar();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Municipio municipio : municipios) {
            listaCombo.add(new SelectItem(municipio.getId(), municipio.getNome()));
        }
        return listaCombo;
    }
    
    public String editar() {
        usuario = (Usuario) model.getRowData();
        setUsuario(usuario);
        return "cadUsuario";
    }
    
    public void carregaFuncionario() {
        funcionario = (Funcionario) modelFuncionario.getRowData();
        pesquisa = false;
    }
}
