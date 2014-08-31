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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import unibaveopencode.gui.iframe.search.JIFConsultaLivro;
import unibaveopencode.gui.panel.components.tables.internal.JPTabelaQrCode;
import unibaveopencode.gui.panel.components.tables.tablemodel.QrCodeTableModel;
import unibaveopencode.gui.principal.JFPrincipal;
import unibaveopencode.model.vo.LivroVO;
import unibaveopencode.model.vo.QrCodeVO;
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
                Long numTombo  = (Long) jPImprimeQrCode.jPTabelaQrCode.jTable.getValueAt(selectedRow, 0);
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
    }
    

    private void gerarQrCode() {
        try {
            if("".equals(jPImprimeQrCode.jtfUrl.getText())){
                return;
            }
            //TODO teste
            jPImprimeQrCode.jlQrCode.setIcon(new ImageIcon(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())));
//            List<QrCode> listaDeQrCodes = new ArrayList<>();
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"1")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"2")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"3")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"4")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"5")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"6")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"7")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"8")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"9")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            listaDeQrCodes.add(QrCodeVO.builder().nomTitulo(jPImprimeQrCode.jtfTitulo.getText()).numTombo(Long.parseLong(jPImprimeQrCode.jtfNum_tombo.getText()+"10")).qrCode(new QrCodeUtil().gerarQRCode(350, 350, jPImprimeQrCode.jtfUrl.getText())).build());
//            String relatorio = getClass().getResource("/unibaveopencode/resource/report/ListaDeQrCodes.jasper").getFile().replace("%c3%a9", "é");
//            new GerarRelatorio().gerar(relatorio, listaDeQrCodes);
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
        this.qrCodeConsultados = qrCodeConsultados;
        jPImprimeQrCode.jPTabelaQrCode.jTable.setModel(new QrCodeTableModel(qrCodeConsultados));
    }
}
