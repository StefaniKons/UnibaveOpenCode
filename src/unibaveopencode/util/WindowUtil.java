/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import unibaveopencode.gui.panel.components.search.JPSearch;
import unibaveopencode.gui.panel.components.tables.tablemodel.TableModel;

/**
 *
 * @author Stéfani
 */
public class WindowUtil {

    public void atualizarTabela(JPSearch<?> search) {
        TableModel<?> tm = (TableModel<?>) search.jPtabelaConsulta.jTable.getModel();
        tm.fireTableDataChanged();
    }

    public void limpar(JInternalFrame jif) {
        for (java.awt.Component comp : jif.getComponents()) {
            if (comp instanceof JRootPane) {
                JPanel jp = (JPanel) ((JRootPane) comp).getContentPane();
                for (Component comp2 : jp.getComponents()) {
                    if (comp2 instanceof JPanel) {
                        for (Component comp3 : ((JPanel) comp2).getComponents()) {
                            if (comp3 instanceof JTextField) {
                                ((JTextField) comp3).setText("");
                            }
                        }
                    }
                }
            }
        }
    }

    public void centralizar(JDesktopPane desktop, JInternalFrame frame) {
        Dimension desktopSize = desktop.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

    public void centralizar(JDesktopPane desktop, JDialog dialog) {
        Dimension desktopSize = desktop.getSize();
        Dimension jDialog = dialog.getSize();
        dialog.setLocation((desktopSize.width - jDialog.width) / 2,
                (desktopSize.height - jDialog.height) / 2);
    }

    public void reset(JFrame frame) {
        for (java.awt.Component comp : frame.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            }
        }

    }
}
