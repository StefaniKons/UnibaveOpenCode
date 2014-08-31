/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unibaveopencode.gui.panel.components.tables.tablemodel;

import java.util.List;
import unibaveopencode.model.vo.QrCodeVO;

/**
 *
 * @author Stéfani
 */
public class QrCodeTableModel extends TableModel<QrCodeVO> {

    public QrCodeTableModel(List<QrCodeVO> lista) {
        super(new String[] {"Nº de Tombo", "Título"}, lista);
    }
    
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QrCodeVO qrCode = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return qrCode.getNumTombo();
            case 1:
                return qrCode.getNomTitulo();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return Long.class;
            case 1:
                return String.class;
            default:
                return null;
        }
    }
}
