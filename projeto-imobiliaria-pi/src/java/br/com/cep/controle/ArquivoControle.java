/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

import br.com.cep.dao.ArquivosDAO;
import br.com.cep.dao.ArquivosDAOImp;
import br.com.cep.dao.StatusDAO;
import br.com.cep.dao.StatusDAOImp;
import br.com.cep.entidade.Arquivos;
import br.com.cep.entidade.Status;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Aluno
 */
@ManagedBean
@SessionScoped
public class ArquivoControle {
    private static final long serialVersionUID = 1L;  
    private Arquivos arquivo = new Arquivos();  
    private List<Arquivos> arquivos = new ArrayList<Arquivos>();  
    @SuppressWarnings("unused")  
    private StreamedContent file;  
  
    public String fileUploadAction(FileUploadEvent event) throws IOException {  
        try {  
            arquivo.setNome(event.getFile().getFileName());  
            byte[] conteudo = event.getFile().getContents();  
            String caminho = "D:\\arquivos\\" + event.getFile().getFileName();  
            FileOutputStream fos = new FileOutputStream(caminho);  
            fos.write(conteudo);  
            fos.close();  
            ArquivosDAO arquivoDao = new ArquivosDAOImp();  
            arquivo.setCaminho(caminho);  
            arquivo.setTamanho(conteudo.length);  
            String nomeArquivo = arquivo.getNome();  
            int e = nomeArquivo.lastIndexOf(".");  
            arquivo.setTipo(nomeArquivo.substring(e));  
            arquivoDao.salva(arquivo);  
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo Salvo!", arquivo.getNome()));  
        } catch (Exception e) {  
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " " + e));  
        } finally {  
            arquivo = new Arquivos();  
            arquivos = new ArrayList<Arquivos>();  
            file = null;  
        }  
        return "Arquivos.xhtml";  
    }  
    
    public List<Arquivos> getListArquivos() {  
        ArquivosDAO hibernateDao = new ArquivosDAOImp();  
        arquivos = hibernateDao.listar();  
        return arquivos;  
    }  
  
    public void remove() {  
        try {  
            ArquivosDAO arquivoDao = new ArquivosDAOImp();  
            Long id = arquivo.getId();  
            arquivo = arquivoDao.pesquisaPorId(id);  
            new File(arquivo.getCaminho()).delete();  
            arquivoDao.excluir(arquivo);  
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo Deletado!", arquivo.getNome()));  
        } catch (Exception e) {  
            FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " " + e));  
        } finally {  
            arquivo = new Arquivos();  
            arquivos = new ArrayList<Arquivos>();  
            file = null;  
        }  
    }  
  
    public StreamedContent getFile() throws FileNotFoundException {  
        ArquivosDAO hibernateDao = new ArquivosDAOImp();  
        Long id = arquivo.getId();  
        arquivo = hibernateDao.pesquisaPorId(id);  
        String caminho = arquivo.getCaminho();  
        FileInputStream stream = new FileInputStream(caminho);  
        return file = new DefaultStreamedContent(stream, caminho, arquivo.getNome());  
    }  
  
    public void setFile(StreamedContent file) {  
        this.file = file;  
    }  
  
    public Arquivos getArquivo() {  
        return arquivo;  
    }  
  
    public void setArquivo(Arquivos arquivo) {  
        this.arquivo = arquivo;  
    }  
  
    public List<Arquivos> getArquivos() {  
        return arquivos;  
    }  
  
    public void setArquivos(List<Arquivos> arquivos) {  
        this.arquivos = arquivos;  
    }  
}  