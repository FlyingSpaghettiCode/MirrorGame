/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands;

import levels.interpreter.commands.Settable;
import java.util.Stack;

/**
 * Variable: represents a variable, a command which takes no arguments and
 * returns a settable value.
 * @author Edgar Lin
 */
public class Variable extends Command implements Settable{
    private Object value; 
    public Variable(Object value)
    {
        this.value = value;
    }
    @Override
    public void eval(Stack stack, int args) {
        if(args>0)
            throw new Error("Attempting to access a variable using "+ Integer.toString(args) + " arguments.");
        stack.push(value);
    }
    public void set(Object value)
    {
        this.value = value;
    }
    public String toString()
    {
        return "<variable with value " + value + ">";
    }
    
}
