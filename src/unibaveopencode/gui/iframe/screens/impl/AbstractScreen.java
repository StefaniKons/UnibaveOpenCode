/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.gui.iframe.screens.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import unibaveopencode.util.WindowUtil;

/**
 *
 * @author Stéfani
 */
public abstract class AbstractScreen extends JInternalFrame {

    protected void limpar() {
        new WindowUtil().limpar(this);
    }

    protected ActionListener getLimparActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                limpar();
            }
        };
    }
}
