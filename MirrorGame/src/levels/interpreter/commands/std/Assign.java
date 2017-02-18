/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands.std;

import levels.interpreter.commands.std.Settable;
import java.util.Stack;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.commands.Command;

/**
 * When executed, assigns the value in the second argument to the settable
 * thing in the first argument. It returns that value to the stack. 
 * @author Edgar Lin
 */
public class Assign extends Command{

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        ((Settable) stack.pop()).set(stack.peek());
    }
    
}
