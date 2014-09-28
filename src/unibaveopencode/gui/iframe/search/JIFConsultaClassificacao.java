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
import unibaveopencode.gui.iframe.screens.JIFClassificacao;
import unibaveopencode.gui.iframe.screens.JIFLivro;
import unibaveopencode.gui.panel.components.search.JPClassificacaoSearch;
import unibaveopencode.gui.panel.components.tables.tablemodel.ClassificacaoTableModel;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.model.vo.ClassificacaoVO;
import unibaveopencode.util.Messages;
import unibaveopencode.util.WindowUtil;
import unibaveopencode.util.impl.VerificaNumerosItemImpl;

/**
 *
 * @author Stéfani
 */
public class JIFConsultaClassificacao extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFConsultaClassificacao
     */
    private JIFClassificacao classificacao;
    private JFPrincipal principal;
    private JPClassificacaoSearch classificacaoSearch;
    private List<ClassificacaoVO> lista;
    private JIFLivro livro;
    
    private void initListener() {
        new VerificaNumerosItemImpl(classificacaoSearch.jtfConsulta, classificacaoSearch.jcbPor, "Código");
    }

    public JIFConsultaClassificacao(JIFLivro livro) {
        initClassificacao();
        this.livro = livro;
        classificacaoSearch.jPbotaoConsulta.jbAlterar.setText("Adicionar");
    }

    //Chama a consulta dentro de JIFAutor
    public JIFConsultaClassificacao(JIFClassificacao classificacao) {
        initClassificacao();
        this.classificacao = classificacao;
    }

    //Chama a consulta dentro de JFPrincipal
    public JIFConsultaClassificacao(JFPrincipal principal) {
        initClassificacao();
        this.principal = principal;
        this.classificacao = new JIFClassificacao(principal);
    }

    private void initClassificacao() {
        classificacaoSearch = new JPClassificacaoSearch(getConsulta());
        this.getContentPane().add(classificacaoSearch);
        initComponents();
        initBotoes(classificacaoSearch);
        initListener();
    }

    private void initBotoes(final JPClassificacaoSearch classificacaoSearch) {
        classificacaoSearch.jPbotaoConsulta.jbFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        classificacaoSearch.jPbotaoConsulta.jbAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = classificacaoSearch.jPtabelaConsulta.jTable.getSelectedRow();
                if (selectedRow == -1) {
                    Messages.getWarningMessage("consultaVazia");
                    return;
                }

                Integer codigo = (Integer) classificacaoSearch.jPtabelaConsulta.jTable.getModel().getValueAt(selectedRow, 0);
                ClassificacaoVO selectedClassificacao = null;
                for (ClassificacaoVO current : getConsulta()) {
                    if (current.getCodClassificacao() == codigo) {
                        selectedClassificacao = current;
                        break;
                    }
                }
                if (livro != null) {
                    livro.setClassificacaoConsultada(selectedClassificacao);
                } else {
                    classificacao.jPCadastroClassificacao.jtfCodigo.setText(selectedClassificacao.getCodClassificacao().toString());
                    classificacao.jPCadastroClassificacao.jtfDescricao.setText(selectedClassificacao.getDesClassificacao());
                    if (principal != null) {
                        principal.removeItem(classificacao);
                        principal.addItem(classificacao);
                    }
                }
                dispose();
            }
        });

        classificacaoSearch.jbConsulta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                switch (classificacaoSearch.jcbPor.getSelectedIndex()) {
                    case 0:
                        getConsulta(ClassificacaoVO.FIND_COD_CLASSIFICACAO, "codClassificacao", Integer.parseInt(classificacaoSearch.jtfConsulta.getText()));
                        return;
                    case 1:
                        getConsulta(ClassificacaoVO.FIND_DESCRICAO_CLASSIFICACAO, "desClassificacao", classificacaoSearch.jtfConsulta.getText());
                }
            }
        });
    }

    private void getConsulta(String metodo, String parametro, Object valor) {
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<ClassificacaoVO> query = em.createNamedQuery(metodo, ClassificacaoVO.class).setParameter(parametro, valor);
            classificacaoSearch.jPtabelaConsulta.jTable.setModel(new ClassificacaoTableModel(query.getResultList()));
            new WindowUtil().atualizarTabela(classificacaoSearch);
        } catch (Exception e) {
            Messages.getErrorMessage(Messages.tratarMsg(e.getMessage()));
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }

    private List<ClassificacaoVO> getConsulta() {
        if (lista != null) {
            return lista;
        }
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("uocPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<ClassificacaoVO> query = em.createNamedQuery(ClassificacaoVO.FIND_ALL, ClassificacaoVO.class);
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
