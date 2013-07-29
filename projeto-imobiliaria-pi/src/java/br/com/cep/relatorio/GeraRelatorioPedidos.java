/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.relatorio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Eduardo
 */
public class GeraRelatorioPedidos extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String[] vlParametro = request.getParameterValues("nome");

        // obtém a conexão com o banco de dados  
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistemacep", // coloque o IP se não for usado localhost  
                    "root", // mude para o nome do usuário do seu bd  
                    "");   // mude para a senha deste usuário  
        } catch (Exception e) {
            System.out.println("Erro ao obter conexao via DriverManager: "
                    + e.getMessage());
        }

        // gera o relatório  
        ServletContext context = getServletContext();
        byte[] bytes = null;
        try {


            // carrega os arquivos jasper  
            JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(
                    context.getRealPath("/WEB-INF/reports/relatorioVenda.jasper"));

            // parâmetros, se houverem  
            Map parametros = new HashMap();
            parametros.put("nr_op", vlParametro[0]);

            // direciona a saída do relatório para um stream  
            bytes = JasperRunManager.runReportToPdf(relatorioJasper, parametros, conn);
        } catch (JRException e) {
            e.printStackTrace();
        }
        if (bytes != null && bytes.length > 0) {
            // envia o relatório em formato PDF para o browser  
            response.setContentType("application/pdf");

            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        }

    }
}


    // Na variavel pathJasper ficara o caminho do diretório para os relatórios compilados (.jasper)  
    //        String pathJasper = getServletContext().getRealPath("/WEB-INF/classes/projetoteste")+ "/";  
      
            // Parametros do relatorio  
     //       Map parametros = new HashMap();  
      //      parametros.put("", pathJasper + "TesteArrayList.jasper");  
       //     try {  
      
     //               JRDataSource jr = new JRBeanArrayDataSource(array.toArray());  
      
                    // Aqui ele cria o relatório e exibe no JasperViewer  
     //               JasperPrint impressao = JasperFillManager.fillReport(pathJasper + "/TesteArrayList.jasper", parametros,jr);  
     //               JasperViewer jrviewer = new JasperViewer(impressao,false);  
      //              jrviewer.show();  
      
      
      //          } catch (Exception e) {  
       //             res.getWriter().println("Erro ao gerar o relatório: " + e);  
        //            e.printStackTrace();  
            //    }  
