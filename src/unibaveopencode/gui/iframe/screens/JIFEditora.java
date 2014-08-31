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
import unibaveopencode.gui.iframe.screens.impl.AbstractScreen;
import unibaveopencode.gui.iframe.search.JIFConsultaEditora;
import unibaveopencode.gui.panel.components.buttons.JPBotaoCadastro;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.util.Messages;
import unibaveopencode.model.vo.EditoraVO;

/**
 *
 * @author Stéfani
 */
public class JIFEditora extends AbstractScreen{
    private JFPrincipal principal;
    
    /**
     * Creates new form JIFEditora
     *
     * @param principal
     */
    
    public JIFEditora(JFPrincipal principal) {
        initComponents();
        this.principal = principal;
        initBotoes();
    }

    private void initBotoes() {
        JPBotaoCadastro botoes = jPCadastroEditora.jPbotaoCadastro;
        botoes.jbSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                EntityManagerFactory emf = null;
                EntityManager em = null;

                try {
                    emf = Persistence.createEntityManagerFactory("uocPU");
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    EditoraVO editora = new EditoraVO();
                    editora.setCodEditora("".equals(jPCadastroEditora.jtfCodigo.getText()) ? null : Integer.parseInt((jPCadastroEditora.jtfCodigo.getText())));
                    editora.setNomEditora(jPCadastroEditora.jtfNome.getText());
                    if (editora.getCodEditora() == null) {
                        em.persist(editora);
                    } else {
                        em.merge(editora);
                    }
                    em.getTransaction().commit();
                    Messages.getInfoMessage("salvarSucesso", "de editora");
                    limpar();
                } catch (Exception e) {
                    if (em != null) {
                        em.getTransaction().rollback();
                    }
                    Messages.getErrorMessage("salvarErro", "de editora", Messages.tratarMsg(e.getMessage()));
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
                    Integer codigo = ("".equals(jPCadastroEditora.jtfCodigo.getText()) ? null : Integer.parseInt(jPCadastroEditora.jtfCodigo.getText()));
                    if (codigo != null) {
                        em.remove(em.getReference(EditoraVO.class, codigo));
                        if (em.getTransaction().isActive()) {
                            em.getTransaction().commit();
                        }
                        Messages.getInfoMessage("excluirSucesso", "de editora");
                    } else {
                        Messages.getInfoMessage("excluirVazio", "uma editora");
                    }
                    limpar();
                } catch (PersistenceException e) {
                    Messages.getErrorMessage(e, "excluirErro", "de editora");
                } catch (Exception e) {
                    if (em != null && em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    Messages.getErrorMessage("excluirErro", "de editora", Messages.tratarMsg(e.getMessage()));
                } finally {
                    if (emf != null) {
                        emf.close();
                    }
                }
            }
        });

        jPCadastroEditora.jbConsultaEditora.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                getPrincipal().addItem(new JIFConsultaEditora(JIFEditora.this));

            }
        });

        botoes.jbLimpar.addActionListener(getLimparActionListener());
        
        botoes.jbFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
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

        jPCadastroEditora = new unibaveopencode.gui.panel.screens.JPCadastroEditora();

        setClosable(true);
        setIconifiable(true);
        setTitle("UnibaveOpenCode");
        getContentPane().add(jPCadastroEditora, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public unibaveopencode.gui.panel.screens.JPCadastroEditora jPCadastroEditora;
    // End of variables declaration//GEN-END:variables
}
