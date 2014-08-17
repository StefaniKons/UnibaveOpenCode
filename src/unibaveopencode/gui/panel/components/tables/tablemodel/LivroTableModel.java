/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.panel.components.tables.tablemodel;

import java.util.List;
import org.hibernate.Hibernate;
import unibaveopencode.model.vo.AutorVO;
import unibaveopencode.model.vo.LivroVO;

/**
 *
 * @author Stéfani
 */
public class LivroTableModel extends TableModel<LivroVO> {

    public LivroTableModel(List<LivroVO> lista) {
        super(new String[]{"Nº Tombo", "Título", "Autor", "Editora", "Ano", "Classificação", "URL"}, lista);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LivroVO livro = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return livro.getNumTombo();
            case 1:
                return livro.getNomTitulo();
            case 2:
                StringBuilder autores = new StringBuilder();
                for (AutorVO autor : livro.getAutor()) {
                    System.out.println(autor);
                    if ("".equals(autores.toString())) {
                        autores.append(autor.getNomAutor());
                        continue;
                    } 
                    autores.append(", ").append(autor.getNomAutor());
                }
                return autores.toString();
            case 3:
                return livro.getEditora().getNomEditora();
            case 4:
                return livro.getDesAno();
            case 5:
                return livro.getClassificacao().getDesClassificacao();
            case 6:
                return livro.getDesUrl();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Long.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            default:
                return null;
        }
    }
}
