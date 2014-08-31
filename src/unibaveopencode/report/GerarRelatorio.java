/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unibaveopencode.report;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Stéfani
 */
public class GerarRelatorio {
    public void gerar(String caminho, Collection<?> list){
        Map parameters = new HashMap<>();
        try {
            JasperPrint print = JasperFillManager.fillReport(caminho, parameters, new JRBeanCollectionDataSource(list));
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            //TODO
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
