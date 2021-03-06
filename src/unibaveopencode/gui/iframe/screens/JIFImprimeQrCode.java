/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.iframe.screens;

import com.google.zxing.WriterException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import unibaveopencode.gui.iframe.search.JIFConsultaLivro;
import unibaveopencode.gui.panel.components.tables.tablemodel.QrCodeTableModel;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.model.vo.LivroVO;
import unibaveopencode.model.vo.QrCodeVO;
import unibaveopencode.report.GerarRelatorio;
import unibaveopencode.util.Messages;
import unibaveopencode.util.QrCodeUtil;

/**
 *
 * @author Stéfani
 */
public class JIFImprimeQrCode extends javax.swing.JInternalFrame {

    JFPrincipal principal;
    private List<QrCodeVO> qrCodeConsultados;

    /**
     * Creates new form JIFImprimeQrCodeVO
     */
    public JIFImprimeQrCode(JFPrincipal principal) {
        this.principal = principal;
        initComponents();
        initBotoes();
        jPImprimeQrCode.jPTabelaQrCode.jTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int selectedRow = jPImprimeQrCode.jPTabelaQrCode.jTable.getSelectedRow();
                Long numTombo = (Long) jPImprimeQrCode.jPTabelaQrCode.jTable.getValueAt(selectedRow, 0);
                LivroVO livro = new JIFConsultaLivro().getConsultaLivro(LivroVO.FIND_NUM_TOMBO, "numTombo", numTombo);
                jPImprimeQrCode.jtfNum_tombo.setText(String.valueOf(livro.getNumTombo()));
                jPImprimeQrCode.jtfTitulo.setText(livro.getNomTitulo());
                jPImprimeQrCode.jtfUrl.setText(livro.getDesUrl());
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    public void initBotoes() {
        jPImprimeQrCode.jPTabelaQrCode.jbRemoveQrCode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = jPImprimeQrCode.jPTabelaQrCode.jTable.getSelectedRow();
                if (selectedRow == -1) {
                    Messages.getWarningMessage("excluirVazio", "um QR CODE");
                    return;
                }
                List<QrCodeVO> listaQrCodes = new ArrayList<>();
                listaQrCodes.addAll(JIFImprimeQrCode.this.qrCodeConsultados);
                listaQrCodes.remove(selectedRow);
                JIFImprimeQrCode.this.qrCodeConsultados.clear();
                JIFImprimeQrCode.this.qrCodeConsultados.addAll(listaQrCodes);
                jPImprimeQrCode.jPTabelaQrCode.jTable.setModel(new QrCodeTableModel(listaQrCodes));
            }
        });

        jPImprimeQrCode.jbConsultaLivro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                principal.addItem(new JIFConsultaLivro(JIFImprimeQrCode.this));

            }
        });

        jPImprimeQrCode.jtfUrl.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                gerarQrCode();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                gerarQrCode();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                gerarQrCode();
            }
        });

        jPImprimeQrCode.jPbtGerarQrCode.jbImprimir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String relatorio = getClass().getResource("/unibaveopencode/resource/report/ListaDeQrCodes.jasper").getFile().replace("%c3%a9", "é");
                new GerarRelatorio().gerar(relatorio, qrCodeConsultados);
            }
        });

        jPImprimeQrCode.jPbtGerarQrCode.jbFechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
    }

    private void gerarQrCode() {
        try {
            if ("".equals(jPImprimeQrCode.jtfUrl.getText())) {
                return;
            }
            jPImprimeQrCode.jlQrCode.setIcon(new ImageIcon(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())));
        } catch (WriterException ex) {
            Logger.getLogger(JIFImprimeQrCode.class.getName()).log(Level.SEVERE, null, ex);
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

        jPImprimeQrCode = new unibaveopencode.gui.panel.screens.JPImprimeQrCode();

        setClosable(true);
        setIconifiable(true);
        setTitle("UnibaveOpenCode");

        jPImprimeQrCode.setMaximumSize(new java.awt.Dimension(552, 690));
        jPImprimeQrCode.setMinimumSize(new java.awt.Dimension(552, 690));
        jPImprimeQrCode.setName(""); // NOI18N
        jPImprimeQrCode.setPreferredSize(new java.awt.Dimension(552, 690));
        getContentPane().add(jPImprimeQrCode, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public unibaveopencode.gui.panel.screens.JPImprimeQrCode jPImprimeQrCode;
    // End of variables declaration//GEN-END:variables

    public List<QrCodeVO> getQrCodeConsultados() {
        return qrCodeConsultados;
    }

    public void setQrCodeConsultados(List<QrCodeVO> qrCodeConsultados) {
        if (qrCodeConsultados == null) {
            return;
        }
        if (this.qrCodeConsultados == null) {
            this.qrCodeConsultados = qrCodeConsultados;
        } else {
            for (QrCodeVO vo : qrCodeConsultados) {
                for (QrCodeVO vo1 : this.qrCodeConsultados) {
                    if (vo1.getNumTombo().equals(vo.getNumTombo())) {
                        Messages.getErrorMessage("O Nº de tombo \"" + vo.getNumTombo() + "\" já foi adicionado.");
                        return;
                    }
                }
            }
            this.qrCodeConsultados.addAll(qrCodeConsultados);
        }
        jPImprimeQrCode.jPTabelaQrCode.jTable.setModel(new QrCodeTableModel(this.qrCodeConsultados));
    }
}
