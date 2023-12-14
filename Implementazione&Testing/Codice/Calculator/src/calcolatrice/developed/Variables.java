/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.developed;

/**
 *
 * @author manu
 */
import calculator.exceptions.*;
import java.util.HashMap;

public class Variables {
    private StackCalc stack;
    private HashMap <Character, Number> map;
    
    public Variables(StackCalc s){
        this.stack=s;
        this.map=new HashMap <>();
    }

    public HashMap getMap(){
        return this.map;
    }
    
    public void stackToVar(Character var) throws InsuffElemStackException, InvalidArgException{
        
        if(var == null)
            throw new InvalidArgException("Seleziona prima una variabile");
        map.put(var, stack.pop());
    }

    public void varToStack(Character var) throws FullStackException, InvalidArgException, UnusedVarException{

        if(var == null)
            throw new InvalidArgException("Seleziona prima una variabile");
        
        Number elem = map.get(var);
        
        if(elem == null)
            throw new UnusedVarException("La variabile " + var + " non contiene valore");
        
        stack.push(elem);
    }

    public void addToVar(Character var)throws FullStackException,InsuffElemStackException, InvalidArgException, UnusedVarException{
        
        if(var == null)
            throw new InvalidArgException("Seleziona prima una variabile");
        
        Number ultimo = map.get(var);
        Number incremento = stack.pop();
        
        if(ultimo == null)
            throw new UnusedVarException("La variabile " + var + " non contiene valore");
        

        Number risultato = new Number( ultimo.getRe() + incremento.getRe(), ultimo.getIm() + incremento.getIm());
       
        map.put(var, risultato);
    }
 
    public void subToVar(Character var)throws FullStackException,InsuffElemStackException, InvalidArgException, UnusedVarException{
        
        if(var == null)
            throw new InvalidArgException("Seleziona prima una variabile");
        
        Number ultimo = map.get(var);
        Number decremento = stack.pop();
        
        
        if(ultimo == null)
            throw new UnusedVarException("La variabile " + var + " non contiene valore");
        
        Number risultato = new Number( ultimo.getRe() - decremento.getRe(), ultimo.getIm() - decremento.getIm());
       
        map.put(var, risultato);
        
    }
    
   
}
