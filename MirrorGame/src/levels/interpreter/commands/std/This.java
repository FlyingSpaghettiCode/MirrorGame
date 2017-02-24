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
 * Returns a reference to the memory context (lexical bindings) of the current closure. 
 * 
 * @author Edgar Lin
 */
public class This extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        stack.push(symMap);
    }
    
}
