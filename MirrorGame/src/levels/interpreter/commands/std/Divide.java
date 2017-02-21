/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands.std;

import java.util.Stack;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.commands.Command;

/**
 * Divides the two arguments and returns the result.
 * 
 * Returns long for integer or long division.
 * 
 * Returns double for mixed mode or floating point division.
 * 
 * @author Edgar Lin
 */
public class Divide extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        Number a = (Number) stack.pop();
        Number b = (Number) stack.pop();
        if((a instanceof Long || a instanceof Integer) && (b instanceof Long || b instanceof Integer))
            stack.push(a.longValue() / b.longValue());
        else
            stack.push(a.doubleValue() / b.doubleValue());
    }
    
}
