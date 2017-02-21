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
 * Gives the first argument mod the second argument.
 * 
 * Two longs or integers go to long;
 * Mixed mode and floating point go to double.
 * @author Edgar Lin
 */
public class Mod extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        Number a = (Number) stack.pop();
        Number b = (Number) stack.pop();
        if((a instanceof Long || a instanceof Integer) && (b instanceof Long || b instanceof Integer))
            stack.push(a.longValue() % b.longValue());
        else
            stack.push(a.doubleValue() % b.doubleValue());
    }
    
}
