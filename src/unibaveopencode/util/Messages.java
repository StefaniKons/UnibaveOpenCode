/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Stéfani
 */
public class Messages {

    private static String getMessage(String msg) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("unibaveopencode.resource.properties.messages");
            return rb.getString(msg);
        } catch (MissingResourceException e) {
            return msg;
        }

    }

    public static void getInfoMessage(String msg, String... parametros) {
        msg = getMessage(msg);
        msg = MessageFormat.format(msg, (Object[]) parametros);
        JOptionPane.showMessageDialog(null, msg, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void getWarningMessage(String msg, String... parametros) {
        msg = getMessage(msg);
        msg = MessageFormat.format(msg, (Object[]) parametros);
        JOptionPane.showMessageDialog(null, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    private static String tratarMsg(Throwable e) {
        if (e.getCause().getCause().getCause() instanceof PSQLException) {
            PSQLException erroDoPostgres = (PSQLException) e.getCause().getCause().getCause();
            return tratarMsg(erroDoPostgres.getServerErrorMessage().getDetail());
        }
        return "";
    }

    public static String tratarMsg(String msg) {
        if (msg.startsWith("Unable to build entity manager factory")) {
            return "[UOC:001] Erro de comunicação com o banco de dados. Contate o administrador.";
        }
        if (msg.startsWith("[PersistenceUnit: uocPU] Unable to build Hibernate SessionFactory")) {
            return "[UOC:002] Erro na estrutura do banco de dados. Contate o administrador.";
        }
        if (msg.startsWith("Connection refused")) {
            return "[UOC:004] Erro de comunicação com o banco de dados. Contate o administrador.";
        }
        if (msg.endsWith("is still referenced from table \"livro_autor\".")) {
            return "[UOC:003] Este autor está vinculado ao cadastro de um livro.\n\nPara efetuar a exclusão acesse o cadastro do livro vinculado e remova o autor";
        }
        if (msg.endsWith("is still referenced from table \"livro\".")) {
            return "[UOC:004] Esta classificação está vinculada ao cadastro de um livro.\n\nPara efetuar a exclusão acesse o cadastro do livro vinculado e remova a classificação";
        }

        return msg;
    }

    public static void getErrorMessage(Throwable e, String msg, String... parametros) {
        //O primeiro parametro sempre sera a mensagem de erro tratada {0}
        List<String> listaDeParametros = new ArrayList<>();
        listaDeParametros.add(tratarMsg(e));
        for (String umParametro : parametros) {
            listaDeParametros.add(umParametro);
        }
        getErrorMessage(msg, listaDeParametros.toArray());
    }

    public static void getErrorMessage(String msg, Object... parametros) {
        msg = msg.startsWith("[UOC:") ? msg : getMessage(msg);
        msg = MessageFormat.format(msg, parametros);
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
