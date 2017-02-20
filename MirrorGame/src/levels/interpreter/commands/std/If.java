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
 * Takes arguments condition, then, and (optional) else;
 * Executes then statement and returns its value if cond is true
 * Otherwise executes else statement and returns its value (if else statement
 * is provided, otherwise returns null).
 * 
 * Like all control flow commands, introduces a new layer of lexical scope.
 * 
 * @author Edgar Lin
 */
public class If extends Command{

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) 
    {
        symMap = new CactusStackMapNode(symMap);
        boolean cond = (Boolean) stack.pop();
        Object then = stack.pop();
        Object elze = (args > 2) ? stack.pop() : null;
        Interpreter.interpret(cond ? then : elze, stack, symMap);
    }
    /**
     * Determines whether the argument should be eagerly or lazily evaluated.
     * @param index 1 indexed index of the argument to check.
     * @return true if the argument should be eager; false if lazy.
     */
    @Override
    public boolean isEager(int index)
    {
        return !(index > 1);
    }
    
}
