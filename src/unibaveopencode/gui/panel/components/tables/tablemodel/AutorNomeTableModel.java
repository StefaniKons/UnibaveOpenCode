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
public class AutorNomeTableModel extends TableModel<AutorVO>{
    
    public AutorNomeTableModel(List<AutorVO> lista) {
        super(new String[] {"Nome"}, lista);
    }
    
   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AutorVO autor = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return autor.getNomAutor();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return String.class;
            default:
                return null;
        }
    }
}
