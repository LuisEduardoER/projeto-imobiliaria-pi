/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Aluno
 */
public class FabricaConexao {

    private static SessionFactory fabrica;

    public static SessionFactory fabricaSessao() {
        //Cria objeto que receberá as configurações
        Configuration cfg = new AnnotationConfiguration();
        //Informe o arquivo XML que contém a configurações
        cfg.configure("/br/com/cep/dao/hibernate.cfg.xml");
        //Cria uma fábrica de sessões.
        //Deve existir apenas uma instância na aplicação
        fabrica = cfg.buildSessionFactory();
        return fabrica;
    }
    
    public static Session abreSessao(){
        if(fabrica == null){
            fabrica = fabricaSessao();
        }
        Session session = fabrica.openSession();
        return session;
    }
}
