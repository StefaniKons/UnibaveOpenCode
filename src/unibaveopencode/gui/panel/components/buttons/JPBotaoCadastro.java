/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unibaveopencode.gui.panel.components.buttons;

/**
 *
 * @author Stéfani
 */
public class JPBotaoCadastro extends javax.swing.JPanel {

    /**
     * Creates new form JPbotaoCadastros
     */
    public JPBotaoCadastro() {
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

        jbConsultar = new javax.swing.JButton();
        jbLimpar = new javax.swing.JButton();
        jbFechar = new javax.swing.JButton();
        jbSalvar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbConsultar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unibaveopencode/resource/image/Botao_excluir_icone.png"))); // NOI18N
        jbConsultar.setText("Excluir");
        jbConsultar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbConsultar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jbConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, 40));

        jbLimpar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unibaveopencode/resource/image/Botao_limpar_icone.png"))); // NOI18N
        jbLimpar.setText("Limpar");
        jbLimpar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbLimpar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jbLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, 40));

        jbFechar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unibaveopencode/resource/image/Botao_sair_icone.png"))); // NOI18N
        jbFechar.setText("Fechar");
        jbFechar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbFechar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jbFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 110, 40));

        jbSalvar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unibaveopencode/resource/image/Botao_salvar_icone.png"))); // NOI18N
        jbSalvar.setText("Salvar");
        jbSalvar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jbSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jbConsultar;
    public javax.swing.JButton jbFechar;
    public javax.swing.JButton jbLimpar;
    public javax.swing.JButton jbSalvar;
    // End of variables declaration//GEN-END:variables
}
