/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package calcolatrice.exceptions;

/**
 *
 * @author emman
 */
public class InvalidArgException extends CalcExceptions{

    /**
     * Creates a new instance of <code>InvalidArgException</code> without detail
     * message.
     */
    

    /**
     * Constructs an instance of <code>InvalidArgException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidArgException(String msg) {
        super(msg);
    }
}
