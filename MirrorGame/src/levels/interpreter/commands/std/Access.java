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
 * Accesses the contents of the provided memory context. 
 * 
 * Takes two arguments: a memory context and some code. Runs the code in the 
 * memory context.
 * 
 * This is used to access the memory context provided. For example, if n is a 
 * variable inside memory context a, (. (a) n) would give the reference to n,
 * (. (a) (n)) or ((. (a) n)) would get the value of n, and n can be set to m
 * with (= (. (a) n) (m)).
 * 
 * @author Edgar Lin
 */
public class Access extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        CactusStackMapNode nspace = (CactusStackMapNode) stack.pop();
        Object member = stack.pop();
        Interpreter.interpret(member, stack, nspace);
    }
    
}
