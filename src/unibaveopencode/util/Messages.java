/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author Stéfani
 */
public class Messages {

    private static String getMessage(String msg) {
        ResourceBundle rb = ResourceBundle.getBundle("unibaveopencode.resource.properties.messages");
        return rb.getString(msg);
    }

    public static void getInfoMessage(String msg, String... parametros) {
        msg = getMessage(msg);
        msg = MessageFormat.format(msg, (Object[]) parametros); 
        JOptionPane.showMessageDialog(null, msg , "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static String tratarMsg(String msg){
        if (msg.startsWith("Unable to build entity manager factory")){
            return "[UOC:001] Erro de comunicação com o banco de dados";
        }
        return msg;
    }
    public static void getErrorMessage(String msg, String... parametros) {
        msg = getMessage(msg);
        msg = MessageFormat.format(msg, (Object[]) parametros); 
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
