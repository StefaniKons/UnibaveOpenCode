/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.iframe.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import unibaveopencode.gui.iframe.components.load.JDCarregando;
import unibaveopencode.gui.iframe.screens.impl.AbstractScreen;
import unibaveopencode.gui.iframe.search.JIFConsultaAutor;
import unibaveopencode.gui.iframe.search.JIFConsultaClassificacao;
import unibaveopencode.gui.iframe.search.JIFConsultaEditora;
import unibaveopencode.gui.iframe.search.JIFConsultaLivro;
import unibaveopencode.gui.panel.components.buttons.JPBotaoCadastro;
import unibaveopencode.gui.panel.components.tables.tablemodel.AutorNomeTableModel;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.model.vo.AutorVO;
import unibaveopencode.model.vo.ClassificacaoVO;
import unibaveopencode.model.vo.EditoraVO;
import unibaveopencode.util.Messages;
import unibaveopencode.model.vo.LivroVO;
import unibaveopencode.util.WindowUtil;
import unibaveopencode.util.impl.VerificaNumerosImpl;

/**
 *
 * @author Stéfani
 */
public class JIFLivro extends AbstractScreen {

    private JFPrincipal principal;
    private EditoraVO editoraConsultada;
    private ClassificacaoVO classificacaoConsultada;
    private LinkedHashSet<AutorVO> autoresConsultados;
    private boolean alterar;
    private static final String URL_DO_REPOSITORIO = "http://192.168.1.4/?bookid=%s";

    /**
     * Creates new form JIFLivro
     */
    public JIFLivro(JFPrincipal principal) {
        initComponents();
        this.principal = principal;
        initBotoes();
        initListeners();
    }

    public void initListeners() {
        new VerificaNumerosImpl(jPCadastroLivro.jtfNumTombo);
        new VerificaNumerosImpl((jPCadastroLivro.jtfAno));
        jPCadastroLivro.jtfNumTombo.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
            }

            @Override
            public void focusLost(FocusEvent fe) {

                if (!jPCadastroLivro.jtfNumTombo.getText().equals("")) {
                    
                    
//                    JDCarregando carregando = new JDCarregando(null, true);
//                    principal.addItem(carregando);
//                    carregando.setVisible(true);
//                    carregando.setVisible(false);
//                    new WindowUtil().centralizar(principal.jDesktopPane1, (JDialog) carregando);
                    jPCadastroLivro.jtfUrl.setText(String.format(URL_DO_REPOSITORIO, jPCadastroLivro.jtfNumTombo.getText()));
                    EntityManagerFactory emf = null;
                    try {
                        emf = Persistence.createEntityManagerFactory("uocPU");
                        EntityManager em = emf.createEntityManager();
                        TypedQuery<LivroVO> query = em.createNamedQuery("LivroVO.findNumTombo", LivroVO.class).setParameter("numTombo", Long.parseLong(jPCadastroLivro.jtfNumTombo.getText()));
                        LivroVO livro = query.getSingleResult();
                        if (livro != null) {
                            jPCadastroLivro.jtfAno.setText(String.valueOf(livro.getDesAno()));
                            jPCadastroLivro.jtfTitulo.setText(livro.getNomTitulo());
                            autoresConsultados = new LinkedHashSet<>();
                            autoresConsultados.addAll(livro.getAutor());
                            jPCadastroLivro.jPtabelaAutor.jTable.setModel(new AutorNomeTableModel(livro.getAutor()));
                            editoraConsultada = livro.getEditora();
                            jPCadastroLivro.jtfEditora.setText(livro.getEditora().getNomEditora());
                            classificacaoConsultada = livro.getClassificacao();
                            jPCadastroLivro.jtfClassificacao.setText(livro.getClassificacao().getDesClassificacao());
                            jPCadastroLivro.jtfUrl.setText(livro.getDesUrl());
                            setAlterar(true);
                        }
                    } catch (Exception e) {
                        Messages.getErrorMessage(Messages.tratarMsg(e.getMessage()));
                    } finally {
                        if (emf != null) {
                            emf.close();
                        }
                    }

//                    carregando.setVisible(false);
//                    carregando.dispose();
                }
            }
        });
    }

    public void initBotoes() {
        jPCadastroLivro.jbConsultaEditora.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JIFConsultaEditora jifConsultaEditora = new JIFConsultaEditora(JIFLivro.this);
                getPrincipal().addItem(jifConsultaEditora);
            }
        });

        jPCadastroLivro.jbConsultaClassificacao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JIFConsultaClassificacao jifConsultaClassificacao = new JIFConsultaClassificacao(JIFLivro.this);
                getPrincipal().addItem(jifConsultaClassificacao);
            }
        });

        jPCadastroLivro.jPtabelaAutor.jbConsultaAutor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JIFConsultaAutor jifConsultaAutor = new JIFConsultaAutor(JIFLivro.this);
                getPrincipal().addItem(jifConsultaAutor);
            }
        });

        jPCadastroLivro.jPtabelaAutor.jbRemoveAutor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = jPCadastroLivro.jPtabelaAutor.jTable.getSelectedRow();
                if (selectedRow == -1) {
                    Messages.getWarningMessage("excluirVazio", "um autor");
                    return;
                }
                List<AutorVO> listaAutores = new ArrayList<>();
                listaAutores.addAll(autoresConsultados);
                listaAutores.remove(selectedRow);
                autoresConsultados.clear();
                autoresConsultados.addAll(listaAutores);
                jPCadastroLivro.jPtabelaAutor.jTable.setModel(new AutorNomeTableModel(listaAutores));
            }
        });

        JPBotaoCadastro botoes = jPCadastroLivro.jPbotaoCadastro;
        botoes.jbSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                EntityManagerFactory emf = null;
                EntityManager em = null;

                try {
                    emf = Persistence.createEntityManagerFactory("uocPU");
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    LivroVO livro = new LivroVO();
                    livro.setNumTombo(Long.parseLong(jPCadastroLivro.jtfNumTombo.getText()));
                    livro.setNomTitulo(jPCadastroLivro.jtfTitulo.getText());
                    livro.setDesAno(Integer.parseInt(jPCadastroLivro.jtfAno.getText()));
                    List<AutorVO> listaAutor = new ArrayList<>();
                    listaAutor.addAll(autoresConsultados);
                    livro.setAutor(listaAutor);
                    livro.setEditora(editoraConsultada);
                    livro.setClassificacao(classificacaoConsultada);
                    livro.setDesUrl(jPCadastroLivro.jtfUrl.getText());
                    if (isAlterar()) {
                        em.merge(livro);
                    } else {
                        em.persist(livro);
                    }
                    em.getTransaction().commit();
                    Messages.getInfoMessage("salvarSucesso", "do livro");
                    limpar();
                } catch (Exception e) {
                    if (em != null) {
                        em.getTransaction().rollback();
                    }
                    e.printStackTrace();
                    Messages.getErrorMessage("salvarErro", "do livro", Messages.tratarMsg(e.getMessage()));
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
                    Long codigo = ("".equals(jPCadastroLivro.jtfNumTombo.getText()) ? null : Long.parseLong(jPCadastroLivro.jtfNumTombo.getText()));
                    if (codigo != null) {
                        em.remove(em.getReference(LivroVO.class, codigo));
                        if (em.getTransaction().isActive()) {
                            em.getTransaction().commit();
                        }
                        Messages.getInfoMessage("excluirSucesso", "do livro");
                    } else {
                        Messages.getInfoMessage("excluirVazio", "um livro");
                    }
                    limpar();
                } catch (PersistenceException e) {
                    Messages.getErrorMessage(e, "excluirErro", "do livro");
                } catch (Exception e) {
                    if (em != null && em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    Messages.getErrorMessage("excluirErro", "do livro", Messages.tratarMsg(e.getMessage()));
                } finally {
                    if (emf != null) {
                        emf.close();
                    }
                }
            }
        });

        jPCadastroLivro.jbConsultaLivro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                getPrincipal().addItem(new JIFConsultaLivro(JIFLivro.this));
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

    @Override
    protected void limpar() {
        super.limpar();
        jPCadastroLivro.jPtabelaAutor.jTable.setModel(new AutorNomeTableModel(new ArrayList<AutorVO>()));
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

        jPCadastroLivro = new unibaveopencode.gui.panel.screens.JPCadastroLivro();

        setClosable(true);
        setIconifiable(true);
        setTitle("UnibaveOpenCode");
        getContentPane().add(jPCadastroLivro, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public unibaveopencode.gui.panel.screens.JPCadastroLivro jPCadastroLivro;
    // End of variables declaration//GEN-END:variables

    public EditoraVO getEditoraConsultada() {
        return editoraConsultada;
    }

    public void setEditoraConsultada(EditoraVO editoraConsultada) {
        if (editoraConsultada == null) {
            return;
        }
        this.editoraConsultada = editoraConsultada;
        jPCadastroLivro.jtfEditora.setText(editoraConsultada.getNomEditora());
    }

    public ClassificacaoVO getClassificacaoConsultada() {
        return classificacaoConsultada;
    }

    public void setClassificacaoConsultada(ClassificacaoVO classificacaoConsultada) {
        if (classificacaoConsultada == null) {
            return;
        }
        this.classificacaoConsultada = classificacaoConsultada;
        jPCadastroLivro.jtfClassificacao.setText(classificacaoConsultada.getDesClassificacao());
    }

    public LinkedHashSet<AutorVO> getAutoresConsultados() {
        return autoresConsultados;
    }

    public void setAutoresConsultados(LinkedHashSet<AutorVO> autoresConsultados) {
        if (autoresConsultados == null) {
            return;
        }
        this.autoresConsultados = autoresConsultados;
        List<AutorVO> listaAutores = new LinkedList<>();
        listaAutores.addAll(autoresConsultados);
        jPCadastroLivro.jPtabelaAutor.jTable.setModel(new AutorNomeTableModel(listaAutores));
    }

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }
}
