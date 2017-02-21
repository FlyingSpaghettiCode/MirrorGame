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
 * The minus operator.
 * 
 * Turns the argument negative if only one argument.
 * 
 * Subtracts the arguments if there are two arguments.
 * 
 * Keeps Integers/Longs integral and floating points floating point.
 * 
 * @author Edgar Lin
 */
public class Minus extends Command{

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        if(args == 1)
        {
            Number a = (Number) stack.pop();
            if((a instanceof Integer || a instanceof Long))
                stack.push(-a.longValue());
            else
                stack.push(-a.doubleValue());
        }
        else
        {
            Number a = (Number) stack.pop();
            Number b = (Number) stack.pop();
            if((a instanceof Integer || a instanceof Long) && (b instanceof Integer || b instanceof Long))
                stack.push(a.longValue() - b.longValue());
            else
                stack.push(a.doubleValue() - b.doubleValue());
        }
    }
    
}
