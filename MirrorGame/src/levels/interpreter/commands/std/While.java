/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands.std;

import java.util.Stack;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.Interpreter;
import levels.interpreter.commands.Command;

/**
 * Implements a while loop. Checks whether the first argument is true, then
 * executes the second argument until the first argument becomes untrue. 
 * 
 * Like all control flow statements introduces a new layer of lexical scope. 
 * 
 * @author Edgar Lin
 */
public class While extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        symMap = new CactusStackMapNode(symMap);
        Object cond = stack.pop();
        Object then = stack.pop();
        Interpreter.interpret(cond, stack, symMap);
        while((Boolean) stack.pop())
        {
            Interpreter.interpret(then, new Stack(), symMap);
            Interpreter.interpret(cond, stack, symMap);
        }
        stack.push(null);
    }
    @Override
    public boolean isEager(int index)
    {
        return false;
    }
    
}
