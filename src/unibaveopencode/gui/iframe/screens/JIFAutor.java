/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.iframe.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import org.postgresql.util.PSQLException;
import unibaveopencode.gui.iframe.screens.impl.AbstractScreen;
import unibaveopencode.gui.iframe.search.JIFConsultaAutor;
import unibaveopencode.gui.panel.components.buttons.JPBotaoCadastro;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.util.Messages;
import unibaveopencode.model.vo.AutorVO;

/**
 *
 * @author Stéfani
 */
public class JIFAutor extends AbstractScreen {

    private JFPrincipal principal;

    /**
     * Creates new form JIFAutor
     *
     * @param principal
     */
    public JIFAutor(JFPrincipal principal) {
        initComponents();
        this.principal = principal;
        initBotoes();
    }

    private void initBotoes() {
        JPBotaoCadastro botoes = jPCadastroAutor.jPBotaoCadastro;
        botoes.jbSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                EntityManagerFactory emf = null;
                EntityManager em = null;

                try {
                    emf = Persistence.createEntityManagerFactory("uocPU");
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    AutorVO autor = new AutorVO();
                    autor.setCodAutor("".equals(jPCadastroAutor.jtfCodigo.getText()) ? null : Integer.parseInt(jPCadastroAutor.jtfCodigo.getText()));
                    autor.setNomAutor(jPCadastroAutor.jtfNome.getText());
                    if (autor.getCodAutor() == null) {
                        em.persist(autor);
                    } else {
                        em.merge(autor);
                    }
                    em.getTransaction().commit();
                    Messages.getInfoMessage("salvarSucesso", "do autor");
                    limpar();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (em != null) {
                        em.getTransaction().rollback();
                    }
                    Messages.getErrorMessage("salvarErro", "do autor", Messages.tratarMsg(e.getMessage()));
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
                    Integer codigo = ("".equals(jPCadastroAutor.jtfCodigo.getText()) ? null : Integer.parseInt(jPCadastroAutor.jtfCodigo.getText()));
                    if (codigo != null) {
                        em.remove(em.getReference(AutorVO.class, codigo));
                        if (em.getTransaction().isActive()) {
                            em.getTransaction().commit();
                        }
                        Messages.getInfoMessage("excluirSucesso", "do autor");
                    } else {
                        Messages.getInfoMessage("excluirVazio", "um autor");
                    }
                    limpar();
                } catch (PersistenceException e) {
                    Messages.getErrorMessage(e, "excluirErro", "do autor");
                } catch (Exception e) {
                    if (em != null && em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    Messages.getErrorMessage("excluirErro", "do autor", Messages.tratarMsg(e.getMessage()));
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

        jPCadastroAutor.jbConsultaAutor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                getPrincipal().addItem(new JIFConsultaAutor(JIFAutor.this));
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

        jPCadastroAutor = new unibaveopencode.gui.panel.screens.JPCadastroAutor();

        setClosable(true);
        setIconifiable(true);
        setTitle("UnibaveOpenCode");
        getContentPane().add(jPCadastroAutor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public unibaveopencode.gui.panel.screens.JPCadastroAutor jPCadastroAutor;
    // End of variables declaration//GEN-END:variables
}
