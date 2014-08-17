/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.panel.components.tables.tablemodel;

import java.util.List;
import unibaveopencode.model.vo.EditoraVO;

/**
 *
 * @author Stéfani
 */
public class EditoraTableModel extends TableModel<EditoraVO> {

    public EditoraTableModel(List<EditoraVO> lista) {
        super(new String[]{"Código", "Editora"}, lista);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EditoraVO editora = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return editora.getCodEditora();
            case 1:
                return editora.getNomEditora();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return null;
        }
    }
}
