/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.iframe.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import unibaveopencode.gui.iframe.screens.JIFAutor;
import unibaveopencode.gui.iframe.screens.JIFLivro;
import unibaveopencode.gui.panel.components.search.JPAutorSearch;
import unibaveopencode.gui.panel.components.tables.tablemodel.AutorTableModel;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.model.vo.AutorVO;
import unibaveopencode.util.Messages;
import unibaveopencode.util.WindowUtil;

/**
 *
 * @author Stéfani
 */
public class JIFConsultaAutor extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFConsultaAutor
     */
    private JIFAutor autor;
    private JFPrincipal principal;
    private JPAutorSearch autorSearch;
    private List<AutorVO> lista;
    private JIFLivro livro;

    public JIFConsultaAutor(JIFLivro livro) {
        initAutor();
        this.livro = livro;
        autorSearch.jPbotaoConsulta.jbAlterar.setText("Adicionar");
        autorSearch.jPtabelaConsulta.jTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        autorSearch.jPtabelaConsulta.jTable.setRowSelectionAllowed(true);
    }

    //Chama a consulta dentro de JIFAutor
    public JIFConsultaAutor(JIFAutor autor) {
        initAutor();
        this.autor = autor;
    }

    //Chama a consulta dentro de JFPrincipal
    public JIFConsultaAutor(JFPrincipal principal) {
        initAutor();
        this.principal = principal;
        this.autor = new JIFAutor(principal);
    }

    private void initAutor() {
        autorSearch = new JPAutorSearch(getConsulta());
        this.getContentPane().add(autorSearch);
        initComponents();
        initBotoes(autorSearch);
    }

    private void initBotoes(final JPAutorSearch autorSearch) {
        autorSearch.jPbotaoConsulta.jbFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        autorSearch.jPbotaoConsulta.jbAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (livro != null) {
                    int[] selectedRows = autorSearch.jPtabelaConsulta.jTable.getSelectedRows();
                    if (selectedRows.length == 0) {
                        Messages.getWarningMessage("consultaVazia");
                        return;
                    }
                    LinkedHashSet<AutorVO> autores = livro.getAutoresConsultados();
                    if (autores == null) {
                        autores = new LinkedHashSet<>();
                    }
                    for (int row : selectedRows) {
                        AutorVO autor = getAutor(row);
                        if (!autores.add(autor)) {
                            Messages.getWarningMessage("autorJaAdicionado", autor.getNomAutor());
                        }
                    }
                    livro.setAutoresConsultados(autores);
                    dispose();
                    return;
                }

                int selectedRow = autorSearch.jPtabelaConsulta.jTable.getSelectedRow();
                if (selectedRow == -1) {
                    Messages.getWarningMessage("consultaVazia");
                    return;
                }

                AutorVO selectedAutor = getAutor(selectedRow);
                autor.jPCadastroAutor.jtfCodigo.setText(selectedAutor.getCodAutor().toString());
                autor.jPCadastroAutor.jtfNome.setText(selectedAutor.getNomAutor());
                if (principal != null) {
                    principal.removeItem(autor);
                    principal.addItem(autor);
                }
                dispose();
            }
        });

        autorSearch.jbConsulta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                switch (autorSearch.jcbPor.getSelectedIndex()) {
                    case 0:
                        getConsulta(AutorVO.FIND_COD_AUTOR, "codAutor", Integer.parseInt(autorSearch.jtfConsulta.getText()));
                        return;
                    case 1:
                        getConsulta(AutorVO.FIND_NOME_AUTOR, "nomAutor", autorSearch.jtfConsulta.getText());
                }
            }
        });
    }

    private void getConsulta(String metodo, String parametro, Object valor) {
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<AutorVO> query = em.createNamedQuery(metodo, AutorVO.class).setParameter(parametro, valor);
            autorSearch.jPtabelaConsulta.jTable.setModel(new AutorTableModel(query.getResultList()));
            new WindowUtil().atualizarTabela(autorSearch);
        } catch (Exception e) {
            Messages.getErrorMessage(Messages.tratarMsg(e.getMessage()));
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    private List<AutorVO> getConsulta() {
        if (lista != null) {
            return lista;
        }
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<AutorVO> query = em.createNamedQuery(AutorVO.FIND_ALL, AutorVO.class);
            lista = query.getResultList();
            return query.getResultList();
        } catch (Exception e) {
            Messages.getErrorMessage(Messages.tratarMsg(e.getMessage()));
            return null;
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    public AutorVO getAutor(int row) {
        Integer codigo = (Integer) autorSearch.jPtabelaConsulta.jTable.getModel().getValueAt(row, 0);
        AutorVO selectedAutor = null;
        for (AutorVO current : getConsulta()) {
            if (current.getCodAutor() == codigo) {
                selectedAutor = current;
                break;
            }
        }
        return selectedAutor;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setIconifiable(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
