/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guilherme
 */
@Entity
public class Imovel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(nullable = false)
    private String codigo;
    @Column(nullable = false)
    private String sala;
    @Column(nullable = false)
    private String dormitorio;
    @Column(nullable = false)
    private String banheiro;
    @Column(nullable = false)
    private String suite;
    @Column(nullable = false)
    private String garagem;
    
    @Temporal(TemporalType.DATE)
    private Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
    
    @Column(nullable = false)
    private double valor;
    
    @Column(nullable = false)
    private double areaTotal;
    
    @ManyToOne
    @JoinColumn(name = "id_tipoImovel")
    private TipoImovel tipoImovel;
    
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "imovel")
    private List<Arquivos> arquivos;
    
    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL)
    private List<Venda> vendas;

    public List<Arquivos> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivos> arquivos) {
        this.arquivos = arquivos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getDormitorio() {
        return dormitorio;
    }

    public void setDormitorio(String dormitorio) {
        this.dormitorio = dormitorio;
    }

    public String getBanheiro() {
        return banheiro;
    }

    public void setBanheiro(String banheiro) {
        this.banheiro = banheiro;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getGaragem() {
        return garagem;
    }

    public void setGaragem(String garagem) {
        this.garagem = garagem;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imovel)) {
            return false;
        }
        Imovel other = (Imovel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cep.entidade.Imovel[ id=" + id + " ]";
    }
}
