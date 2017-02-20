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
 * Returns the logical not of the argument. 
 * @author Edgar Lin
 */
public class Not extends Command 
{

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        stack.push(! (Boolean) stack.pop());
    }
    
}
