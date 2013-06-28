/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Aluno
 */
public class GeradorTabelas {
    public static void main(String[] args) {
        EntityManagerFactory fabrica = Persistence . createEntityManagerFactory ("SistemaCEP");
        fabrica.close();
    }
}
