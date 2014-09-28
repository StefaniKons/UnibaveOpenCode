/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.util.impl;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class VerificaNumerosImpl implements KeyListener {

    private JTextField campoDeTexto;
    private Border bordaOriginal;

    public VerificaNumerosImpl(JTextField campoDeTexto) {
        this.campoDeTexto = campoDeTexto;
        campoDeTexto.addKeyListener(this);
        bordaOriginal = campoDeTexto.getBorder();
    }
    
    public void remove(JTextField campoDeTexto) {
        this.campoDeTexto = campoDeTexto;
        campoDeTexto.removeKeyListener(this);
        bordaOriginal = campoDeTexto.getBorder();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        boolean isDigit = Character.isDigit(ke.getKeyChar());
        if (ke.getKeyCode() == KeyEvent.VK_TAB || ke.getKeyCode() == KeyEvent.VK_ENTER) {
            return;
        }

        if (!isDigit) {
            String texto = campoDeTexto.getText().replaceAll("[^\\d.]", "");
            campoDeTexto.setText(texto);
            Border bordaVermelha = BorderFactory.createLineBorder(Color.red);
            campoDeTexto.setBorder(bordaVermelha);
            campoDeTexto.setToolTipText("Este campo aceita somente números.");
            return;
        }
        campoDeTexto.setBorder(bordaOriginal);
        campoDeTexto.setToolTipText(null);
    }
}
