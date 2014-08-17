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
import unibaveopencode.gui.iframe.screens.JIFLivro;
import unibaveopencode.gui.panel.components.search.JPLivroSearch;
import unibaveopencode.gui.panel.components.tables.tablemodel.LivroTableModel;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.model.vo.AutorVO;
import unibaveopencode.model.vo.LivroVO;
import unibaveopencode.util.Messages;
import unibaveopencode.util.WindowUtil;

/**
 *
 * @author Stéfani
 */
public class JIFConsultaLivro extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFConsultaLivro
     */
    private JIFLivro livro;
    private JFPrincipal principal;
    private JPLivroSearch livroSearch;
    private List<LivroVO> lista;

    //Chama a consulta dentro de JIFLivro
    public JIFConsultaLivro(JIFLivro livro) {
        initLivro();
        this.livro = livro;
    }

    //Chama a consulta dentro de JFPrincipal
    public JIFConsultaLivro(JFPrincipal principal) {
        initLivro();
        this.principal = principal;
    }

    private void initLivro() {
        livroSearch = new JPLivroSearch(getConsulta());
        this.getContentPane().add(livroSearch);
        initComponents();
        initBotoes(livroSearch);
    }

    private void initBotoes(final JPLivroSearch livroSearch) {
        livroSearch.jPbotaoConsulta.jbFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        livroSearch.jPbotaoConsulta.jbAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = livroSearch.jPtabelaConsulta.jTable.getSelectedRow();
                if (selectedRow == -1) {
                    Messages.getWarningMessage("consultaVazia");
                    return;
                }

                LivroVO selectedLivro = getConsultaLivro(LivroVO.FIND_NUM_TOMBO, "numTombo", livroSearch.jPtabelaConsulta.jTable.getModel().getValueAt(selectedRow, 0));
                livro.jPCadastroLivro.jtfNumTombo.setText(String.valueOf(selectedLivro.getNumTombo()));
                livro.jPCadastroLivro.jtfTitulo.setText(selectedLivro.getNomTitulo());
                LinkedHashSet<AutorVO> autores = new LinkedHashSet<>();
                autores.addAll(selectedLivro.getAutor());
                livro.setAutoresConsultados(autores);
                livro.setEditoraConsultada(selectedLivro.getEditora());
                livro.jPCadastroLivro.jtfAno.setText(selectedLivro.getDesAno().toString());
                livro.setClassificacaoConsultada(selectedLivro.getClassificacao());
                livro.jPCadastroLivro.jtfUrl.setText(selectedLivro.getDesUrl());
                livro.setAlterar(true);
                if (principal != null) {
                    principal.removeItem(livro);
                    principal.addItem(livro);
                }
                dispose();
            }
        });

        livroSearch.jbConsulta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                switch (livroSearch.jcbPor.getSelectedIndex()) {
                    case 0:
                        getConsulta(LivroVO.FIND_NUM_TOMBO, "numTombo", Long.parseLong(livroSearch.jtfConsulta.getText()));
                        return;
                    case 1:
                        getConsulta(LivroVO.FIND_NOM_TITULO, "nomTitulo", livroSearch.jtfConsulta.getText());
                        return;
                    case 2:
                        getConsulta(LivroVO.FIND_NOM_AUTOR, "nomAutor", livroSearch.jtfConsulta.getText());
                        return;
                    case 3:
                        getConsulta(LivroVO.FIND_NOM_EDITORA, "nomEditora", livroSearch.jtfConsulta.getText());
                        return;
                    case 4:
                        getConsulta(LivroVO.FIND_DES_ANO, "desAno", Integer.parseInt(livroSearch.jtfConsulta.getText()));
                        return;
                    case 5:
                        getConsulta(LivroVO.FIND_DES_CLASSIFICACAO, "desClassificacao", livroSearch.jtfConsulta.getText());
                        return;
                    case 6:
                        getConsulta(LivroVO.FIND_DES_URL, "desUrl", livroSearch.jtfConsulta.getText());
                }
            }
        });
    }

    private void getConsulta(String metodo, String parametro, Object valor) {
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<LivroVO> query = em.createNamedQuery(metodo, LivroVO.class).setParameter(parametro, valor);
            livroSearch.jPtabelaConsulta.jTable.setModel(new LivroTableModel(query.getResultList()));
            new WindowUtil().atualizarTabela(livroSearch);
        } catch (Exception e) {
            Messages.getErrorMessage(Messages.tratarMsg(e.getMessage()));
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    private LivroVO getConsultaLivro(String metodo, String parametro, Object valor) {
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<LivroVO> query = em.createNamedQuery(metodo, LivroVO.class).setParameter(parametro, valor);
            return query.getSingleResult();
        } catch (Exception e) {
            Messages.getErrorMessage(Messages.tratarMsg(e.getMessage()));
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
        return null;
    }

    private List<LivroVO> getConsulta() {
        if (lista != null) {
            return lista;
        }
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<LivroVO> query = em.createNamedQuery(LivroVO.FIND_ALL, LivroVO.class);
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

    public JIFConsultaLivro() {
        initComponents();
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
