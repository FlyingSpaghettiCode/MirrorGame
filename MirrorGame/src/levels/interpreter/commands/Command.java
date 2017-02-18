/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands;

import java.util.Stack;
import levels.interpreter.CactusStackMapNode;

/**
 *
 * @author Edgar Lin
 */
public abstract class Command {
    /**
     * Tells whether the specified argument is to be eagerly 
     * evaluated. 
     * @param index The 1-indexed index of the argument in question. Should be
     * less than or equal arity.
     * @return defaults to true
     */
    public boolean isEager(int index)
    {
        return true;
    }
    /**
     * Evaluates the argument
     * @param stack 
     * @param args number of arguments to take from stack.
     */
    public abstract void eval(Stack stack, CactusStackMapNode symMap, int args);
    
}
