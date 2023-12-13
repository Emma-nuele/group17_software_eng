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
import calculator.exceptions.*;
import java.util.ArrayDeque;
import java.util.Deque;


public class StackCalc {
    
    private Deque<Number> stack;
    private final int MAX_SIZE=100;
    
    public StackCalc(){
        stack = new ArrayDeque<>();
    }

    //Rimuovi?
    public Deque<Number> getStack() {
        return stack;
    } 

    public void setStack(Deque s){
        this.stack = s;
    }

    public Number pop () throws InsuffElemStackException{
        if(!leastOne())
            throw new InsuffElemStackException("\nImpossibile eseguire comando, non ci sono elementi nello stack");
        else
            return stack.pollLast();
    }

    public void push(Number n) throws FullStackException{
        if(isFull())
            throw new FullStackException("\nImpossibile aggiungere elemento, lo stack è pieno");
        else
            stack.addLast(n);
    }

    public Number top() throws InsuffElemStackException{
        if(!leastOne())
            throw new InsuffElemStackException("\nNon ci sono elementi nello stack");
        else
            return stack.getLast();
    }

    public boolean leastOne(){
        return stack.size()>0;
    }

    public boolean leastTwo(){
        return stack.size()>1;
    }

    public boolean isFull(){
        return stack.size()== MAX_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder("");
    
        for (Number number : stack) {
         buffer.append(number.toString()).append(" ");
        }
    
        return buffer.toString();
    }

}
