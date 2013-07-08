/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.entidade;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Aluno
 */
@Entity
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Cliente extends Pessoa {
    @Temporal(TemporalType.TIMESTAMP)       
    private Date dataRegistro = new java.sql.Date(System.currentTimeMillis());
    @OneToMany(mappedBy = "cliente")
    private List<Imovel> imoveis;

    public List<Imovel> getImoveis() {
        return imoveis;
    }

    public void setImoveis(List<Imovel> imoveis) {
        this.imoveis = imoveis;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    
}
