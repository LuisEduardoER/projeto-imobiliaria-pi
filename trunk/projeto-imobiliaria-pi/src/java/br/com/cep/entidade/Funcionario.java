/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.entidade;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Aluno
 */
@Entity
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Funcionario extends Pessoa {
    @Column(nullable=false)
    private String cresci;
    @Column(nullable=false)
    private double salario;
    @ManyToOne
    @JoinColumn(name="id_funcao")
    private Funcao funcao;
    
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Venda> venda;

    public String getCresci() {
        return cresci;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
    public void setCresci(String cresci) {
        this.cresci = cresci;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
