/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.iframe.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import unibaveopencode.gui.iframe.screens.JIFEditora;
import unibaveopencode.gui.iframe.screens.JIFLivro;
import unibaveopencode.gui.panel.components.search.JPEditoraSearch;
import unibaveopencode.gui.panel.components.tables.tablemodel.EditoraTableModel;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.model.vo.EditoraVO;
import unibaveopencode.util.Messages;
import unibaveopencode.util.WindowUtil;
import unibaveopencode.util.impl.VerificaNumerosItemImpl;

/**
 *
 * @author Stéfani
 */
public class JIFConsultaEditora extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFConsultaEditora
     */
    private JIFEditora editora;
    private JFPrincipal principal;
    private JPEditoraSearch editoraSearch;
    private List<EditoraVO> lista;
    private JIFLivro livro;

    private void initListener() {
        new VerificaNumerosItemImpl(editoraSearch.jtfConsulta, editoraSearch.jcbPor, "Código");
    }

    public JIFConsultaEditora(JIFLivro livro) {
        initEditora();
        this.livro = livro;
        editoraSearch.jPbotaoConsulta.jbAlterar.setText("Adicionar");
    }

    //Chama a consulta dentro de JIFEditora
    public JIFConsultaEditora(JIFEditora editora) {
        initEditora();
        this.editora = editora;
    }

    //Chama a consulta dentro de JFPrincipal
    public JIFConsultaEditora(JFPrincipal principal) {
        initEditora();
        this.principal = principal;
        this.editora = new JIFEditora(principal);
    }

    private void initEditora() {
        editoraSearch = new JPEditoraSearch(getConsulta());
        this.getContentPane().add(editoraSearch);
        initComponents();
        initBotoes(editoraSearch);
        initListener();
    }

    private void initBotoes(final JPEditoraSearch editoraSearch) {
        editoraSearch.jPbotaoConsulta.jbFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        editoraSearch.jPbotaoConsulta.jbAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = editoraSearch.jPtabelaConsulta.jTable.getSelectedRow();
                if (selectedRow == -1) {
                    Messages.getWarningMessage("consultaVazia");
                    return;
                }

                Integer codigo = (Integer) editoraSearch.jPtabelaConsulta.jTable.getModel().getValueAt(selectedRow, 0);
                EditoraVO selectedEditora = null;
                for (EditoraVO current : getConsulta()) {
                    if (current.getCodEditora() == codigo) {
                        selectedEditora = current;
                        break;
                    }
                }
                if (livro != null) {
                    livro.setEditoraConsultada(selectedEditora);
                } else {
                    editora.jPCadastroEditora.jtfCodigo.setText(selectedEditora.getCodEditora().toString());
                    editora.jPCadastroEditora.jtfNome.setText(selectedEditora.getNomEditora());
                    if (principal != null) {
                        principal.removeItem(editora);
                        principal.addItem(editora);
                    }
                }
                dispose();
            }
        });

        editoraSearch.jbConsulta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                switch (editoraSearch.jcbPor.getSelectedIndex()) {
                    case 0:
                        if (!editoraSearch.jtfConsulta.getText().equals("")) {
                            getConsulta(EditoraVO.FIND_COD_EDITORA, "codEditora", Integer.parseInt(editoraSearch.jtfConsulta.getText()));
                        }
                        return;
                    case 1:
                        getConsulta(EditoraVO.FIND_NOME_EDITORA, "nomEditora", editoraSearch.jtfConsulta.getText());
                }
            }
        });
    }

    private void getConsulta(String metodo, String parametro, Object valor) {
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<EditoraVO> query = em.createNamedQuery(metodo, EditoraVO.class).setParameter(parametro, valor);
            editoraSearch.jPtabelaConsulta.jTable.setModel(new EditoraTableModel(query.getResultList()));
            new WindowUtil().atualizarTabela(editoraSearch);
        } catch (Exception e) {
            Messages.getErrorMessage(Messages.tratarMsg(e.getMessage()));
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    private List<EditoraVO> getConsulta() {
        if (lista != null) {
            return lista;
        }
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<EditoraVO> query = em.createNamedQuery(EditoraVO.FIND_ALL, EditoraVO.class);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
