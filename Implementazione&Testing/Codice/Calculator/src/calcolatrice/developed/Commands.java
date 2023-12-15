/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.developed;

/**
 *
 * @author cater
 */
import calculator.exceptions.FullStackException;
import calculator.exceptions.InsuffElemStackException;

public class Commands {
    private StackCalc stack;
    
    public Commands(StackCalc s){
        this.stack=s;
    }

    public void swap()throws InsuffElemStackException,FullStackException{ //inverte pos ultimi due elem
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Non ci sono abbastanza elementi nello stack");
        
        Number val1 = stack.pop();
        Number val2 = stack.pop();
        stack.push(val1);
        stack.push(val2);
        
    } 

    public void over() throws InsuffElemStackException,FullStackException{ //push copia penultimo elem
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Non ci sono abbastanza elementi nello stack");
        
        Number ultimo = stack.pop();
        Number penultimo = stack.top();
        stack.push(ultimo);
        stack.push(penultimo);
    }

    public void clear()throws InsuffElemStackException{
        Number tmp = null;
            while(stack.leastOne()){
                tmp = (Number) stack.pop();
            }
            
    }

    public void drop()throws InsuffElemStackException{  //rimuove ultimo elem
            stack.pop();
    }

    public void dup()throws InsuffElemStackException,FullStackException{ //copia ultimo elem
        stack.push(stack.top());
        
    }
}

