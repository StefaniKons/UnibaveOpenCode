/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.panel.components.tables.tablemodel;

import java.util.List;
import unibaveopencode.model.vo.ClassificacaoVO;

/**
 *
 * @author Stéfani
 */
public class ClassificacaoTableModel extends TableModel<ClassificacaoVO> {

    public ClassificacaoTableModel(List<ClassificacaoVO> lista) {
        super(new String[]{"Código", "Descrição"}, lista);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClassificacaoVO classificacao = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return classificacao.getCodClassificacao();
            case 1: 
                return classificacao.getDesClassificacao();
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
