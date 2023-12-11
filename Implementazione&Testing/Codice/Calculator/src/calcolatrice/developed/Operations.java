/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator.developed;

import calculator.exceptions.*;

public class Operations{
    
    private StackCalc stack;
    
    public Operations(StackCalc stack){
        
        this.stack = stack;
        
    }
    
    public void sum() throws InsuffElemStackException, FullStackException{
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbastanza elementi nello stack");
        
        
        Number ultimo = stack.pop();
        Number penultimo = stack.pop();
        
        Number risultato = new Number( ultimo.getRe() + penultimo.getRe(), ultimo.getIm() + penultimo.getIm());
       
        stack.push(risultato);
                
    }
    
    public void sub() throws InsuffElemStackException,FullStackException{
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        
        
        Number ultimo = stack.pop();
        Number penultimo = stack.pop();
        
        Number risultato = new Number( penultimo.getRe() - ultimo.getRe(), penultimo.getIm() - ultimo.getIm());
        
        stack.push(risultato);
        
        
    }
    
    public void multiplication() throws InsuffElemStackException, FullStackException{
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        
        
        Number ultimo = stack.pop();
        Number penultimo = stack.pop();
        
        double risultatoRe = (penultimo.getRe() * ultimo.getRe()) - (ultimo.getIm() * penultimo.getIm());
        double risultatoIm = (penultimo.getRe() * ultimo.getIm()) + (ultimo.getRe() * penultimo.getIm());
        Number risultato = new Number( risultatoRe , risultatoIm);
        
        stack.push(risultato);
        
    }
    
    
    public void division() throws DivisionZeroException, InsuffElemStackException, FullStackException{
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        
        
        Number ultimo = stack.pop();
        Number penultimo = stack.pop();
        
        double a = penultimo.getRe();
        double b = penultimo.getIm();
        double c = ultimo.getRe();
        double d = ultimo.getIm();
        
        if((c == 0 && d == 0))
            throw new DivisionZeroException("\nImpossibile dividere per zero");
        
            double risultatoRe = ( (a*c) + (b*d) )/( (c*c) + (d*d) );
            double risultatoIm = ( (b*c) - (a*d) )/( (c*c) + (d*d) );
            Number risultato = new Number( risultatoRe , risultatoIm);
            stack.push(risultato);
       
        
        
    }
    
    
    public void sqrt() throws FullStackException, InsuffElemStackException{
        
        Number val = stack.pop();
        
        if (val.getIm() == 0){ //controllo se il valore è reale
                
            if (val.getRe() >= 0){ //se è maggiore di zero eseguo la radice classica
                
                val.setRe(Math.sqrt((val.getRe())));
                
                    
            }else{
                
                val.setIm(Math.sqrt((-1 * val.getRe()))); //se è minore di zero eseguo la radice del valore trasformato in positivo
                
                val.setRe(0);             //e lo inserisco come valore immaginario e setto la parte reale a 0
                    
            }
            
        }else{
            
            double abs = Math.sqrt(val.getRe() * val.getRe() + val.getIm() * val.getIm());   //calcolo modulo e fase
            double arg = (2 * Math.PI + Math.atan2(val.getIm(), val.getRe())) % (2 * Math.PI);

            val.setRe(Math.sqrt(abs) * (Math.cos((arg / 2))));        //calcolo i valori reali e immaginari e li inserisco nell'oggetto val
            val.setIm(Math.sqrt(abs) * (Math.sin(arg / 2)));
            
            }

        
        stack.push(val);
        
    }
    
    
    public void signReversal ()throws InsuffElemStackException, FullStackException{
        
        
        Number val = stack.pop();
        
        val.setIm(val.getIm()*-1);
        val.setRe(val.getRe()*-1);
        
        stack.push(val);
        
        
    }
    
}
