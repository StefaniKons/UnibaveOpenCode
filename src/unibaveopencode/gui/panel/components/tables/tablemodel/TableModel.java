/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.panel.components.tables.tablemodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import unibaveopencode.util.Messages;

/**
 *
 * @author Stéfani
 * @param <T> 
 */
public abstract class TableModel<T> extends AbstractTableModel {

    protected String[] colunas;
    protected List<T> lista;

    public TableModel(String[] colunas, List<T> lista) {
        this.colunas = colunas;
        this.lista = lista;
    }
    
    @Override
    public abstract Class<?> getColumnClass(int columnIndex);
    
    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    @Override
    public int getRowCount() {
        if(lista == null){
            Messages.getErrorMessage("[UOC:003] Erro ao obter os dados da tabela. Contate o administrador.");
            return -1;
        }
        return lista.size();
    }
    
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    public String[] getColunas() {
       return colunas;
    }

    public List<T> getLista() {
        return lista;
    }
}
