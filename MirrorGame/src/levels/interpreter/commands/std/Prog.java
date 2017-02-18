package levels.interpreter.commands.std;

import java.util.Stack;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.Interpreter;
import levels.interpreter.commands.Command;

/**
 * Executes the given commands in order. Returns the return value of the last
 * one (if it exists).
 * 
 * Also introduces a new layer of lexical scope. 
 * 
 * @author Edgar Lin
 */
public class Prog extends Command{

    /**
     * Evaluates the prog command over the arguments in the stack. 
     * @param stack The args & return stack. 
     * @param symMap The map of lexical bindings to evaluate this command in.
     * @param args Should be > 0
     */
    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        symMap = new CactusStackMapNode(symMap);
        Stack nstack = null;
        for(int i = 0; i < args; i++)
        {
            nstack = new Stack();
            Interpreter.interpret(stack.pop(), nstack, symMap);
        }
        if(nstack != null && !nstack.empty())
            stack.push(nstack.pop());
    }
    
    /**
     * @return always false
     */
    @Override
    public boolean isEager(int index)
    {
        return false;
    }
    
}
