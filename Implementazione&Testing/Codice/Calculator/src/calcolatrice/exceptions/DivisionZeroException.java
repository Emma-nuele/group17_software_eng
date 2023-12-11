/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package calculator.exceptions;

public class DivisionZeroException extends CalcExceptions{

    /**
     * Creates a new instance of <code>DivisionZeroException</code> without
     * detail message.
     */

    /**
     * Constructs an instance of <code>DivisionZeroException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DivisionZeroException(String msg) {
        super(msg);
    }
}
