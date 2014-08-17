/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.panel.components.tables.tablemodel;

import java.util.List;
import unibaveopencode.model.vo.AutorVO;

/**
 *
 * @author Stéfani
 */
public class AutorTableModel extends TableModel<AutorVO> {

    public AutorTableModel(List<AutorVO> lista) {
        super(new String[] {"Código", "Nome"}, lista);
    }
    
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AutorVO autor = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return autor.getCodAutor();
            case 1:
                return autor.getNomAutor();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return null;
        }
    }
}
