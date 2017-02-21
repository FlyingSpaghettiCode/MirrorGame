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
 * Multiplies the two arguments together and returns the result.
 * 
 * Integral * Integral -> Integral
 * Integral * Floating -> Floating
 * Floating * Floating -> Floating
 * 
 * @author Edgar Lin
 */
public class Multiply extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        Number a = (Number) stack.pop();
        Number b = (Number) stack.pop();
        if((a instanceof Long || a instanceof Integer) && (b instanceof Long || b instanceof Integer))
            stack.push(a.longValue() * b.longValue());
        else
            stack.push(a.doubleValue() * b.doubleValue());

    }
    
}
