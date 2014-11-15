/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unibaveopencode.exceptions;

/**
 *
 * @author Stéfani
 */
public class RequiredFieldException extends Exception{
    
    public RequiredFieldException(String message){
        super(message);
    }
}
