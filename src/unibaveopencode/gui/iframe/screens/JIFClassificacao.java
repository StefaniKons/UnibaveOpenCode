/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.iframe.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import unibaveopencode.exceptions.RequiredFieldException;
import unibaveopencode.gui.iframe.screens.impl.AbstractScreen;
import unibaveopencode.gui.iframe.search.JIFConsultaClassificacao;
import unibaveopencode.gui.panel.components.buttons.JPBotaoCadastro;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.util.Messages;
import unibaveopencode.model.vo.ClassificacaoVO;
import unibaveopencode.util.WindowUtil;

/**
 *
 * @author Stéfani
 */
public class JIFClassificacao extends AbstractScreen {

    private JFPrincipal principal;

    /**
     * Creates new form JIFClassificacao
     */
    public JIFClassificacao(JFPrincipal principal) {
        initComponents();
        jPCadastroClassificacao.jtfCodigo.setName("\"Código\"");
        jPCadastroClassificacao.jtfDescricao.setName("\"Descrição\"");
        this.principal = principal;
        initBotoes();
        jPCadastroClassificacao.jtfDescricao.getText();
    }

    private void initBotoes() {
        JPBotaoCadastro botoes = jPCadastroClassificacao.jPbotaoCadastro;
        botoes.jbSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    WindowUtil.verificaVazio(JIFClassificacao.this, jPCadastroClassificacao.jtfCodigo.getName());
                } catch (RequiredFieldException ex) {
                    Messages.getErrorMessage(ex.getMessage());
                    return;
                }
                EntityManagerFactory emf = null;
                EntityManager em = null;

                try {
                    emf = Persistence.createEntityManagerFactory("uocPU");
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    ClassificacaoVO classificacao = new ClassificacaoVO();
                    classificacao.setCodClassificacao("".equals(jPCadastroClassificacao.jtfCodigo.getText()) ? null : Integer.parseInt(jPCadastroClassificacao.jtfCodigo.getText()));
                    classificacao.setDesClassificacao(jPCadastroClassificacao.jtfDescricao.getText());
                    if (classificacao.getCodClassificacao() == null) {
                        em.persist(classificacao);
                    } else {
                        em.merge(classificacao);
                    }
                    em.getTransaction().commit();
                    Messages.getInfoMessage("salvarSucesso", "de classificação");
                    limpar();
                } catch (Exception e) {
                    if (em != null) {
                        em.getTransaction().rollback();
                    }
                    Messages.getErrorMessage("salvarErro", "de classificação", Messages.tratarMsg(e.getMessage()));
                } finally {
                    if (emf != null) {
                        emf.close();
                    }
                }
            }
        });

        botoes.jbExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                EntityManagerFactory emf = null;
                EntityManager em = null;

                try {
                    emf = Persistence.createEntityManagerFactory("uocPU");
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    Integer codigo = ("".equals(jPCadastroClassificacao.jtfCodigo.getText()) ? null : Integer.parseInt(jPCadastroClassificacao.jtfCodigo.getText()));
                    if (codigo != null) {
                        em.remove(em.getReference(ClassificacaoVO.class, codigo));
                        if (em.getTransaction().isActive()) {
                            em.getTransaction().commit();
                        }
                        Messages.getInfoMessage("excluirSucesso", "de classificação");
                    } else {
                        Messages.getInfoMessage("excluirVazio", "uma classificação");
                    }
                    limpar();
                } catch (PersistenceException e) {
                    Messages.getErrorMessage(e, "excluirErro", "de classificação");
                } catch (Exception e) {
                    if (em != null && em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    Messages.getErrorMessage("excluirErro", "de classificação", Messages.tratarMsg(e.getMessage()));
                } finally {
                    if (emf != null) {
                        emf.close();
                    }
                }
            }
        });

        botoes.jbFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        jPCadastroClassificacao.jbConsultaClassificacao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                getPrincipal().addItem(new JIFConsultaClassificacao(JIFClassificacao.this));
            }
        });

        botoes.jbLimpar.addActionListener(getLimparActionListener());
    }

    private JFPrincipal getPrincipal() {
        return principal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCadastroClassificacao = new unibaveopencode.gui.panel.screens.JPCadastroClassificacao();

        setClosable(true);
        setIconifiable(true);
        setTitle("UnibaveOpenCode");
        getContentPane().add(jPCadastroClassificacao, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public unibaveopencode.gui.panel.screens.JPCadastroClassificacao jPCadastroClassificacao;
    // End of variables declaration//GEN-END:variables
}
