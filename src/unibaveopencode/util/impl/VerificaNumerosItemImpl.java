/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.util.impl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Stéfani
 */
public class VerificaNumerosItemImpl implements ItemListener {

    private JTextField campoVerificado;
    private String[] nomeDoItem;
    private Border bordaOriginal = null;

    public VerificaNumerosItemImpl(JTextField campoVerificado, JComboBox caixaDeSelecao, String... nomeDoItem) {
        this.campoVerificado = campoVerificado;
        this.nomeDoItem = nomeDoItem;
        caixaDeSelecao.addItemListener(this);
        new VerificaNumerosImpl(campoVerificado);
        bordaOriginal = campoVerificado.getBorder();

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        campoVerificado.setText("");
        if (ItemEvent.SELECTED == ie.getStateChange()) {

            for (String nome : nomeDoItem) {
                if (ie.getItem().equals(nome)) {
                    new VerificaNumerosImpl(campoVerificado);
                }
            }

            return;
        }
        for (KeyListener al : campoVerificado.getKeyListeners()) {
            if (al instanceof VerificaNumerosImpl) {
                campoVerificado.removeKeyListener(al);
            }
        }
        campoVerificado.setBorder(bordaOriginal);
    }
}
