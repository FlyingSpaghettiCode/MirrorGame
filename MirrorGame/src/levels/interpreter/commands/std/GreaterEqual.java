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
 * Calculates whether arg 1 >= arg 2 according to comparable and returns the result.
 * @author Edgar Lin
 */
public class GreaterEqual extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        stack.push(((Comparable) stack.pop()).compareTo(stack.pop()) >= 0);
    }
    
}
