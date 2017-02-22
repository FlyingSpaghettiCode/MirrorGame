/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels.interpreter.commands.std;

import java.util.Stack;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.Form;
import levels.interpreter.commands.Command;

/**
 * Creates a list containing the arguments. 
 * @author Edgar Lin
 */
public class FList extends Command {

    @Override
    public void eval(Stack stack, CactusStackMapNode symMap, int args) {
        Form list = new Form();
        for(int i = 0; i < args; i++)
            list.add(stack.pop());
        stack.push(list);
    }
    
}
