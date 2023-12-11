/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator.developed;

import calculator.exceptions.FullStackException;
import calculator.exceptions.InsuffElemStackException;

public class Commands {
    private StackCalc stack;
    
    public Commands(StackCalc s){
        this.stack=s;
    }

    public void swap()throws InsuffElemStackException,FullStackException{
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        
        Number val1 = stack.pop();
        Number val2 = stack.pop();
        stack.push(val1);
        stack.push(val2);
        
    } 

    public void over() throws InsuffElemStackException,FullStackException{
        
        if(!stack.leastTwo())
            throw new InsuffElemStackException("Impossibile eseguire comando, non ci sono abbasatnza elementi nello stack");
        
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

    public void drop()throws InsuffElemStackException{
            stack.pop();
    }

    public void dup()throws InsuffElemStackException,FullStackException{
        stack.push(stack.top());
        
    }
}
