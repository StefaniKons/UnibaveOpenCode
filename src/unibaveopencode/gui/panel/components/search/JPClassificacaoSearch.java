/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unibaveopencode.gui.panel.components.search;

import java.util.List;
import unibaveopencode.gui.panel.components.tables.tablemodel.ClassificacaoTableModel;
import unibaveopencode.model.vo.ClassificacaoVO;

/**
 *
 * @author Stéfani
 */
public class JPClassificacaoSearch extends JPSearch<ClassificacaoVO> {

    /**
     * Creates new form JPClassificacaoSearch
     * @param lista
     */
    public JPClassificacaoSearch(List<ClassificacaoVO> lista) {
       super("Consulta de Classificação", new ClassificacaoTableModel(lista));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
