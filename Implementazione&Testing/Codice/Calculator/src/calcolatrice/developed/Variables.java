/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcolatrice.developed;

import calcolatrice.developed.StackCalc;
import calcolatrice.exceptions.*;
import java.util.HashMap;

/**
 *
 * @author Daniele
 */
public class Variables {
    private StackCalc stack;
    private HashMap <Character, Number> map;
    
    public Variables(StackCalc s){
        this.stack=s;
        this.map=new HashMap <>();
    }

    public void stackToVar(Character var) throws InsuffElemStackException{
        map.put(var, stack.pop());
    }

    public void varToStack(Character var) throws FullStackException{
        stack.push(map.get(var));
    }

    public void addToVar(Character var)throws FullStackException,InsuffElemStackException{
        
        Number ultimo = map.get(var);
        Number incremento = stack.pop();
        
        Number risultato = new Number( ultimo.getRe() + incremento.getRe(), ultimo.getIm() + incremento.getIm());
       
        map.put(var, risultato);
    }
 
    public void subToVar(Character var)throws FullStackException,InsuffElemStackException{
        
        Number ultimo = map.get(var);
        Number decremento = stack.pop();
        
        Number risultato = new Number( ultimo.getRe() - decremento.getRe(), ultimo.getIm() - decremento.getIm());
       
        map.put(var, risultato);
        
    }
    
   
}