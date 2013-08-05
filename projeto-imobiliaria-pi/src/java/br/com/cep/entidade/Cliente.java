/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.entidade;

import java.util.Date;
import javax.persistence.Entity;
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
    @Temporal(TemporalType.DATE)       
    private Date dataRegistro = new java.util.Date();

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    
}
