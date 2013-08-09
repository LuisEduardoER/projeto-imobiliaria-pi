/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.CepDAO;
import br.com.cep.dao.CepDAOImp;
import br.com.cep.dao.ImovelDAO;
import br.com.cep.dao.ImovelDAOImp;
import br.com.cep.dao.StatusDAO;
import br.com.cep.dao.StatusDAOImp;
import br.com.cep.dao.TipoImovelDAO;
import br.com.cep.dao.TipoImovelDAOImp;
import br.com.cep.entidade.Arquivos;
import br.com.cep.entidade.Cep;
import br.com.cep.entidade.Cliente;
import br.com.cep.entidade.Endereco;
import br.com.cep.entidade.Imovel;
import br.com.cep.entidade.Status;
import br.com.cep.entidade.TipoImovel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class ImovelControle {

    private Imovel imovel;
    private ImovelDAO imovelDao;
    private TipoImovel tipoImovel;
    private Status status;
    private DataModel model;
    private boolean pesquisa = false;
    private Endereco endereco;
    private Cep cep;
    private Cliente cliente;
    private DataModel modelCliente;
    private List<Imovel> imov;
    private List<Imovel> imveisDestaques;
    private List<Imovel> buscaPorCodigo;
    private List<Arquivos> arquivos;

    public List<Imovel> getBuscaPorCodigo() {
        return buscaPorCodigo;
    }
    
    public List<Arquivos> getArquivos() {
        return arquivos;
    }

    public List<Imovel> getImveisDestaques() {
        return imveisDestaques;
    }

    public List<Imovel> getImov() {
        return imov;
    }

    public void setImov(List<Imovel> imov) {
        this.imov = imov;
    }

    public DataModel getModelCliente() {
        return modelCliente;
    }

    public void setModelCliente(DataModel modelCliente) {
        this.modelCliente = modelCliente;
    }

    public ImovelDAO getImovelDao() {
        return imovelDao;
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setImovelDao(ImovelDAO imovelDao) {
        this.imovelDao = imovelDao;
    }

    public Status getStatus() {
        if (status == null) {
            status = new Status();
        }
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public Cep getCep() {
        if (cep == null) {
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

    public Imovel getImovel() {
        if (imovel == null) {
            imovel = new Imovel();
        }
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public TipoImovel getTipoImovel() {
        if (tipoImovel == null) {
            tipoImovel = new TipoImovel();
        }
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
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

    public void GerarCodigo() {
        Random gerador = new Random();
        int numero = gerador.nextInt(1000000);
        imovel.setCodigo(Integer.toString(numero));
    }

    public String salvar() {
        imovelDao = new ImovelDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        endereco.setCep(cep);
        imovel.setEndereco(endereco);
        imovel.setTipoImovel(tipoImovel);
        imovel.setStatus(status);
        imovel.setCliente(cliente);
        try {
            if (imovel.getId() == null) {
                GerarCodigo();
                imovelDao.salva(imovel);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso!", ""));
            } else {
                imovelDao.altera(imovel);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com Sucesso!", ""));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao salvar estado" + ex.getMessage());
        }
        limpa();
        return "index";
    }

    public List<SelectItem> getTodosTipoImovel() throws Exception {
        TipoImovelDAO tipImovelDao = new TipoImovelDAOImp();
        List<TipoImovel> tipImovel = tipImovelDao.listar();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (TipoImovel tipImo : tipImovel) {
            listaCombo.add(new SelectItem(tipImo.getId(), tipImo.getTipo()));
        }
        return listaCombo;
    }

    public List<SelectItem> getTodosStatus() throws Exception {
        StatusDAO statusDao = new StatusDAOImp();
        List<Status> stat = statusDao.listar();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Status sta : stat) {
            listaCombo.add(new SelectItem(sta.getId(), sta.getStatus()));
        }
        return listaCombo;
    }

    public String pesquisar() {
        if (imovel != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqImovelAlterar";
    }

    public String pesquisarImovelFoto() {
        if (imovel != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqArquivoMostrar";
    }

    public String pesquisarExcluir() {
        if (imovel != null) {
            limpa();
            model = null;
        }
        pesquisa = false;
        return "pesqImovelExcluir";
    }

    public String editar() {
        imovel = (Imovel) model.getRowData();
        endereco = imovel.getEndereco();
        cep = endereco.getCep();
        cliente = imovel.getCliente();
        tipoImovel = imovel.getTipoImovel();
        status = imovel.getStatus();
        setImovel(imovel);
        return "cadImovel";
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        imovel = (Imovel) model.getRowData();
        imovelDao = new ImovelDAOImp();
        try {
            imovelDao.excluir(imovel);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluido com Sucesso!", ""));
        } catch (Exception ex) {
            System.out.println("Erro ao excluir" + ex.getMessage());
        }
        return "index";
    }

    private void limpa() {
        imovel = null;
        cliente = null;
        endereco = null;
        cep = null;
        tipoImovel = null;
        status = null;
    }

    public String novoImovel() {
        imovel = new Imovel();
        pesquisa = false;
        return "cadImovel";
    }

    public void pesquisaImovel() {
        imovelDao = new ImovelDAOImp();
        if (imovel.getCodigo() != null) {
            try {
                model = new ListDataModel(imovelDao.procuraImovelPorCodigo(imovel.getCodigo()));
            } catch (Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public void buscaPorCodigo() {
        imovelDao = new ImovelDAOImp();
        if (imovel.getCodigo() != null) {
            try {
                imov = imovelDao.buscarPorCodigo(imovel.getCodigo());
            } catch (Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
    
    public List<Imovel> getListaTodosImoveis() {
        imovelDao = new ImovelDAOImp();
        try {
            imov = imovelDao.listaTodosImoveis();
            buscaPorCodigo();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return imov;
    }
    
    
    public List<Imovel> getListaImoveisCasa() {
        imovelDao = new ImovelDAOImp();
        try {
            imov = imovelDao.todosImoveisCasa();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return imov;
    }

    public List<Imovel> getListaImoveisApartamento() {
        imovelDao = new ImovelDAOImp();
        try {
            imov = imovelDao.todosImoveisApartamento();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return imov;
    }

    public List<Imovel> getListaImoveisComercial() {
        imovelDao = new ImovelDAOImp();
        try {
             imov = imovelDao.todosImoveisComercial();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return imov;
    }

    public List<Imovel> getListaImoveisTerreno() {
        imovelDao = new ImovelDAOImp();
        try {
            imov = imovelDao.todosImoveisTerreno();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return imov;
    }

    public String imovelSelecionado(Long id) {
        imovelDao = new ImovelDAOImp();
        try {
            imovel = imovelDao.imovelSelecionado(id);
            arquivos = imovel.getArquivos();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return "imovelSelecionado";
    }

    public List<Imovel> getListaImoveisSitio() {
        imovelDao = new ImovelDAOImp();
        try {
            imov = imovelDao.todosImoveisSitio();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return imov;
    }

    public List<Imovel> getImoveisDestaque() {
        imovelDao = new ImovelDAOImp();
        try {
            imveisDestaques = imovelDao.imoveisDestaque();
        } catch (Exception ex) {
            System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
        }
        return imveisDestaques;
    }

    public void pesquisaCliente() {
        EnderecoUtil endUtil;
        if (cliente.getNome() != null) {
            endUtil = new EnderecoUtil();
            List clientes = endUtil.pesquisaCliente(cliente.getNome());
            modelCliente = new ListDataModel(clientes);
        }
    }

    public void carregaImovel() {
        cliente = (Cliente) modelCliente.getRowData();
        pesquisa = false;
    }

    public void pesquisaCep() {
        CepDAO cepDao = new CepDAOImp();
        if (cep.getCep() != null) {
            try {
                cep = cepDao.procuraCepPorCep(cep.getCep());
            } catch (Exception ex) {
                System.out.println("Erro ao pesquisar todos os dados" + ex.getMessage());
            }
        }
    }
}
