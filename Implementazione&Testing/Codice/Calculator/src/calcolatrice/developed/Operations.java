/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.developed;


import calculator.exceptions.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operations {

    private StackCalc stack;

    public Operations(StackCalc stack) {

        this.stack = stack;

    }

    public void sum() throws InsuffElemStackException, FullStackException {

        if (!stack.leastTwo()) {
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbastanza elementi nello stack");
        }

        Number ultimo = stack.pop();
        Number penultimo = stack.pop();

        Number risultato = new Number(this.round(ultimo.getRe() + penultimo.getRe()), this.round(ultimo.getIm() + penultimo.getIm()));

        stack.push(risultato);

    }

    public void sub() throws InsuffElemStackException, FullStackException {

        if (!stack.leastTwo()) {
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        }

        Number ultimo = stack.pop();
        Number penultimo = stack.pop();

        Number risultato = new Number(this.round(penultimo.getRe() - ultimo.getRe()), this.round(penultimo.getIm() - ultimo.getIm()));

        stack.push(risultato);

    }

    public void multiplication() throws InsuffElemStackException, FullStackException {

        if (!stack.leastTwo()) {
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        }

        Number ultimo = stack.pop();
        Number penultimo = stack.pop();

        double risultatoRe = this.round((penultimo.getRe() * ultimo.getRe()) - (ultimo.getIm() * penultimo.getIm()));
        double risultatoIm = this.round((penultimo.getRe() * ultimo.getIm()) + (ultimo.getRe() * penultimo.getIm()));
        Number risultato = new Number(risultatoRe, risultatoIm);

        stack.push(risultato);

    }

    public void division() throws DivisionZeroException, InsuffElemStackException, FullStackException {

        if (!stack.leastTwo()) {
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        }

        Number ultimo = stack.pop();
        Number penultimo = stack.pop();

        double a = penultimo.getRe();
        double b = penultimo.getIm();
        double c = ultimo.getRe();
        double d = ultimo.getIm();

        if ((c == 0 && d == 0)) {
            throw new DivisionZeroException("\nImpossibile dividere per zero");
        }

        double risultatoRe = this.round(((a * c) + (b * d)) / ((c * c) + (d * d)));
        double risultatoIm = this.round(((b * c) - (a * d)) / ((c * c) + (d * d)));
        Number risultato = new Number(risultatoRe, risultatoIm);
        stack.push(risultato);

    }

    public void sqrt() throws FullStackException, InsuffElemStackException {

        Number val = stack.pop();

        if (val.getIm() == 0) { //controllo se il valore è reale

            if (val.getRe() >= 0) { //se è maggiore di zero eseguo la radice classica

                val.setRe(this.round(Math.sqrt((val.getRe()))));

            } else {

                val.setIm(this.round(Math.sqrt((-1 * val.getRe())))); //se è minore di zero eseguo la radice del valore trasformato in positivo

                val.setRe(0);             //e lo inserisco come valore immaginario e setto la parte reale a 0

            }

        } else {

            double abs = this.round(Math.sqrt(val.getRe() * val.getRe() + val.getIm() * val.getIm()));   //calcolo modulo e fase
            double arg = this.round((2 * Math.PI + Math.atan2(val.getIm(), val.getRe())) % (2 * Math.PI));

            val.setRe(this.round(Math.sqrt(abs) * (Math.cos((arg / 2)))));        //calcolo i valori reali e immaginari e li inserisco nell'oggetto val
            val.setIm(this.round(Math.sqrt(abs) * (Math.sin(arg / 2))));

        }

        stack.push(val);

    }

    public void signReversal() throws InsuffElemStackException, FullStackException {

        Number val = stack.pop();

        if(val.getIm()!= 0)
        val.setIm(val.getIm() * -1);
        if(val.getRe()!= 0)
        val.setRe(val.getRe() * -1);

        stack.push(val);
    }

    
        private static double round(double value) {

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
