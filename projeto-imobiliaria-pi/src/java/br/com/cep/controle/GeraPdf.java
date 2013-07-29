/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.controle;

/**
 *
 * @author Eduardo
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeraPdf {
    private Collection<?> arrayList;
    private Map<String, Object> parameters;

    public void executeReport() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("caminho_arquivo.jasper");

        response.setContentType("application/pdf");

        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");

        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();

            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(arrayList);

            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameters, datasource);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            facesContext.responseComplete();
        }
    }
}
